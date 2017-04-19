<%@page import="org.json.simple.JSONValue"%>
<%@page import="client.JerseyClient"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp" flush="true" />
    </head>
    <body>
        <%
            JerseyClient jc = new JerseyClient();
            JSONObject cur = null;
            String disc;
            JSONObject now = (JSONObject) JSONValue.parse(jc.getUser((String) session.getAttribute("currentsession")));
            boolean moderator = false;
            if (request.getParameter("postid") != null && request.getParameter("id") != null) {
                cur = (JSONObject) JSONValue.parse(jc.getPost(request.getParameter("postid")));
                disc = request.getParameter("id");
                if (now.get("discussionid") != null) {
                    if (now.get("discussionid").toString().equals(disc)) {
                        moderator = true;
                    }
                }
                if (!cur.get("userid").toString().equals(now.get("userid").toString()) && !moderator) {
                    return;
                }
            } else {
                return;
            }
            
        %>
        <div class="container">
            <jsp:include page="header.jsp" flush="true" />
            <div class="content">
                <div class="contentthreadleft" style="background: #223245;text-align: justify; line-height: 23px; margin-top: 20px ; padding-top: 5px;padding-left: 30px; padding-right: 30px">
                <p style="color:white">Edit Post</p><hr/> 
                <form action="editpost" method="POST">
                    Content:<br/>
                    <textarea style="resize:none;background-color:rgb(15,25,40); color:white;" class="form-control" rows="10" id="comment" name="txtContent"><%=cur.get("message").toString().replace("\n", "<br />") %></textarea>
                    <br/>
                    <input type="hidden" name="postid" value="<%=cur.get("postid").toString()%>" />
                    
                    <button type="submit" name="btn" class="btn btn-default" style="background-color:rgb(100,0,0);width:25%; color:white; float:left; font-family: lato; font-size: 16px;" value="delete">Delete Post</button>
                    <button type="submit" name="btn" class="btn btn-default" style="background-color:rgb(0,100,0);width:25%; color:white; float:right; font-family: lato; font-size: 16px;" value="edit">Edit Post</button>
                </form>
                    
                </div>
                <div style="clear:both;"></div>
            </div>
            <jsp:include page="footer.jsp" flush="true" />
        </div>
    </body>
</html>
