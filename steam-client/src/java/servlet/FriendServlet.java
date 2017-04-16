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
@WebServlet(name = "Friend", urlPatterns = {"/friend"})
public class FriendServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JerseyClient jc = new JerseyClient();
        String current = (String) request.getSession().getAttribute("currentsession");
        String add = (String) request.getParameter("friendid");
        String status;
        try {
            status = request.getParameter("status");
        } catch (Exception e) {
            status = "save";
        }
        JSONObject obj = new JSONObject();
        if (status.equals("save")) {
            obj.put("userid", current);
            obj.put("friendid", add);
            obj.put("status", 0);
            jc.addFriend(obj);
            response.sendRedirect("profile.jsp?uid=" + add);
        } else {
            obj.put("status", 1);
            jc.confirmFriend(current, add, obj);
            response.sendRedirect("profile.jsp?uid=" + current);
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
//        processRequest(request, response);
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
