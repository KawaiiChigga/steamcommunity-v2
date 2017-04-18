/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
        //Post p = CtrlPost.getPost(Integer.parseInt(request.getParameter("postid")));
        if (request.getParameter("btn").equals("delete")) {
          //  p.setMessage("This post has been deleted!");
        } else {
            //p.setMessage(request.getParameter("txtContent"));
        }
        
        //CtrlPost.updatePost(p);
        
       // response.sendRedirect("profile.jsp?uid=" + p.getUser().getUserId());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
