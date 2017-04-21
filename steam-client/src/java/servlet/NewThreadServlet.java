/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import client.JerseyClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
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
        JerseyClient jc = new JerseyClient();
        Integer cur = Integer.parseInt((String) request.getSession().getAttribute("currentsession"));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int pinned = 0;
        String judul = request.getParameter("txtTitle");
        try {
            pinned = Integer.parseInt(request.getParameter("rbPinned"));
            if (pinned == 1) {
                judul = "PINNED: " + judul;
            }
        } catch (Exception e) {
        }
        
        String now = dateFormat.format(new Date());
        JSONObject th = new JSONObject();
        th.put("discussionid", Integer.parseInt(request.getParameter("idDiscussion")));
        th.put("userid", cur);
        th.put("title", judul);
        th.put("publishdatetime", now);
        th.put("ispinned", pinned);
        th.put("categorytype", Integer.parseInt(request.getParameter("rbPost")));
        
        th = (JSONObject) JSONValue.parse(jc.createThread(th));
        
        JSONObject p = new JSONObject();
        p.put("threadid", (Integer) Integer.parseInt(th.get("threadid").toString()));
        p.put("userid", cur);
        p.put("message", request.getParameter("txtContent"));
        p.put("postdatetime", now);
        p.put("updatedatetime", now);
        
        jc.createPost(p);
        response.sendRedirect("post.jsp?tid="+th.get("threadid").toString()+"&id="+th.get("discussionid").toString());
        
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
