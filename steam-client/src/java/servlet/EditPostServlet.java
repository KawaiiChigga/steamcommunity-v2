/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import client.JerseyClient;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


/**
 *
 * @author Daniel
 */
@WebServlet(name = "EditPostServlet", urlPatterns = {"/editpost"})
public class EditPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JerseyClient jc = new JerseyClient();
        JSONObject p = (JSONObject) JSONValue.parse(jc.getPost(request.getParameter("postid")));
        if (request.getParameter("btn").equals("delete")) {
            p.put("message", "This post has been deleted!");
        } else {
            p.put("message", request.getParameter("txtContent"));
        }
        jc.updatePost(p.get("postid").toString(), p);
        
        response.sendRedirect("profile.jsp?uid=" + p.get("userid"));
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
