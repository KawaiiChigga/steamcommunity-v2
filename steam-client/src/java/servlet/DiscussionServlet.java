/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import client.JerseyClient;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Sujana
 */
@WebServlet(name = "DiscussionServlet", urlPatterns = {"/adddiscussion"})
@MultipartConfig
public class DiscussionServlet extends HttpServlet {
    private static final String SAVE_DIR = "/image/games";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Discussion d = new Discussion();
        JerseyClient jc = new JerseyClient();
        JSONObject d = new JSONObject();
        String id = null;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            int maxFileSize = 200 * 1024;
            int maxMemSize = 4 * 1024;
        
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(maxMemSize);
            
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(maxFileSize);
            
            try {
                List items = upload.parseRequest(request);
                System.out.println("SIZE : "+ items.size());
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    System.out.println(item.getString());
                    if (!item.isFormField()) {
                        if (!item.getName().equals("")) {
                            String name = d.get("gamename").toString()+ "." + FilenameUtils.getExtension(item.getName()); 
                            String root = new File(request.getServletContext().getRealPath("")).getParentFile().getParent();
                            File uploadedFile = new File(root + "/web" + SAVE_DIR + File.separator + name);
                           item.write(uploadedFile);

                            d.put("imgurl", name);
                        }
                    } else {
                        switch (item.getFieldName()) {
                            case "txtGameName" : d.put("gamename", item.getString()); break;
                            case "txtDescription" : d.put("description", item.getString()); break;
                            case "txtModerator" : id = item.getString(); break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        jc.insertDiscussion(d);
//        Discussion temp = CtrlDiscussion.insertDiscussion(d);
        JSONObject u = (JSONObject) JSONValue.parse(jc.getUser(id));
//        User u = CtrlAccount.getUser(id);
        u.put("discussionid", d.get("discussionid"));
//        u.setDiscussion(temp);
        jc.editUser(u.get("userid").toString(), u);
//        CtrlAccount.edit(u);
        response.sendRedirect("index.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
