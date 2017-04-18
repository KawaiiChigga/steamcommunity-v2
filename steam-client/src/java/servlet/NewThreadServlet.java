/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel
 */
@WebServlet(name = "NewThreadServlet", urlPatterns = {"/newthread"})
public class NewThreadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        User cur = CtrlAccount.getUser((Integer) request.getSession().getAttribute("currentsession"));
        byte pinned = 0;
        String judul = request.getParameter("txtTitle");
        try {
            pinned = (byte) Integer.parseInt(request.getParameter("rbPinned"));
            if (pinned == (byte) 1) {
                judul = "PINNED: " + judul;
            }
        } catch (Exception e) {
            
        }
  //      Thread u = new Thread(
 //               CtrlDiscussion.getDisc(Integer.parseInt(request.getParameter("idDiscussion"))), 
     //           cur, 
//                judul, 
  //              new Date(), 
   //             pinned, 
    //            (byte) Integer.parseInt(request.getParameter("rbPost"))
     //   );
        
   //     u = CtrlThread.insertThread(u);
        
      //  Post p = new Post(
      //          u, 
        //        cur, 
        //        request.getParameter("txtContent"), 
          //      new Date(), 
            //    new Date()
        //);
        
    //    CtrlPost.insertPost(p);
    //    response.sendRedirect("post.jsp?tid=" + u.getThreadId() + "&id="+ u.getDiscussion().getDiscussionId());
//        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
//        rd.forward(request, response);
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
