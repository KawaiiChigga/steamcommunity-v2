/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import client.JerseyClient;
import java.io.File;
import java.io.IOException;
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
 * @author Daniel
 */
@WebServlet(name = "EditProfileServlet", urlPatterns = {"/edit"})
@MultipartConfig
public class EditProfileServlet extends HttpServlet {
    private static final String SAVE_DIR = "/image/user";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JerseyClient jc = new JerseyClient();
        JSONObject u = (JSONObject) JSONValue.parse(jc.getUser((String) request.getSession().getAttribute("currentsession")));
        
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
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    
                    if (!item.isFormField()) {
                        if (!item.getName().equals("")) {
                            String name = u.get("userid").toString() + "." + FilenameUtils.getExtension(item.getName()); 
                            String root = new File(request.getServletContext().getRealPath("")).getParentFile().getParent();
                            File uploadedFile = new File(root + "/web" + SAVE_DIR + File.separator + name);
                            item.write(uploadedFile);

                            u.put("imageurl", name);
                        }
                    } else {
                        switch (item.getFieldName()) {
                            case "txtProfileName" : u.put("username", item.getString()); break;
                            case "txtSummary" : u.put("description", item.getString()); break;
                            case "txtRealName" : u.put("name", item.getString()); break;
                            case "txtCountry" : u.put("country", item.getString()); break;
                            case "txtProvince" : u.put("province", item.getString()); break;
                            case "txtCity" : u.put("city", item.getString()); break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else {
            u.put("username", request.getParameter("txtProfileName"));
            u.put("description", request.getParameter("txtSummary"));
            u.put("name", request.getParameter("txtRealName"));
            u.put("country", request.getParameter("txtCountry"));
            u.put("province", request.getParameter("txtProvince"));
            u.put("city", request.getParameter("txtCity"));
        }

//        HttpSession session = request.getSession();
//        User ss = (User) session.getAttribute("currentsession");
//        System.out.println(ss.getUserId());
        jc.editUser(u.get("userid").toString(), u);
        request.getSession().setAttribute("currentsession", u.get("userid").toString());
        response.sendRedirect("profile.jsp?uid="+u.get("userid").toString());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
