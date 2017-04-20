/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import client.JerseyClient;
import fb.FBConnection;
import fb.FBGraph;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author admin
 */
@WebServlet(name = "FacebookServlet", urlPatterns = {"/fbauth"})
public class FacebookServlet extends HttpServlet {
    
    private String code="";
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        if (code == null || code.equals("")) {
            throw new RuntimeException(
                "ERROR: Didn't get code parameter in callback.");
        }
        FBConnection fbConnection = new FBConnection();
        FBGraph fbGraph = new FBGraph(fbConnection.getAccessToken(code));
        JSONObject fbProfileData = fbGraph.getGraphData(fbGraph.getFBGraph());

        String email = fbProfileData.get("email").toString();
        if (email == null || email.equals("")){
            response.sendRedirect("login.jsp");
        }

        JerseyClient jc = new JerseyClient();
        JSONObject user = (JSONObject) JSONValue.parse(jc.checkByEmail(email));
        if (user == null) {
            String name = fbProfileData.get("name").toString();
            String picture = fbProfileData.get("picture").toString();
            String username = name.replaceAll("\\s","");
            String password = "fb?"+fbProfileData.get("id").toString()+"##";

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            JSONObject obj = new JSONObject();
            obj.put("username", username);
            obj.put("password", password);
            obj.put("email", email);
            obj.put("joindate", dateFormat.format(new Date()));

            jc.createAccount(obj);
            String uid = ((JSONObject) JSONValue.parse(jc.checkByEmail(email))).get("userid").toString();

            URL url = new URL(picture);
            String imageURL = new File(request.getServletContext().getRealPath("")).getParentFile().getParent();
            String filename = uid + ".png";
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(new File(imageURL + "/web/image/user" + File.separator + filename));

            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
            is.close();
            os.close();

            JSONObject u = new JSONObject();
            u.put("name", name);
            u.put("imageurl", filename);
            jc.editUser(uid, u);

            session.setAttribute("currentsession", uid);
            response.sendRedirect("facebookauth.jsp?success=true");
        } else {
            String uid = ((JSONObject) JSONValue.parse(jc.checkByEmail(email))).get("userid").toString();
            session.setAttribute("currentsession", uid);
            response.sendRedirect("profile.jsp?uid=" + uid);
        }
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
