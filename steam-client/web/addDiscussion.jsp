<%-- 
    Document   : discussion
    Created on : Feb 22, 2017, 6:21:36 PM
    Author     : Sujana
--%>

<%@page import="client.JerseyClient"%>
<%@page import="org.json.simple.JSONValue"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Start Your Discussion</title>
        <jsp:include page="head.jsp" flush="true" />
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true" />
        <%
            JerseyClient jc = new JerseyClient();
            JSONObject u = (JSONObject) JSONValue.parse(jc.getUser((String) session.getAttribute("currentsession")));
            //User u = CtrlAccount.getUser((Integer) session.getAttribute("currentsession"));
            if (u == null) {
                return;
            } else {
                if (!u.get("userid").toString().equals("1")) {
                    return;
                }
            }
        %>
        <div class="content">
            <div style="border: solid 1px #223245; background: #223245;text-align: justify; line-height: 23px; color: white; width: 700px; height:700px; margin-left: 50px;margin-top: 20px ; padding-top: 5px;padding-left: 30px; padding-right: 30px">
                <p style="color:white">Start a new discussion</p><hr/> 
                <form action="adddiscussion" method="POST" enctype="multipart/form-data">
                Game Name:<br/>
                <input style="background-color:rgb(15,25,40); color:white;" type="text" class="form-control" id="usr" name="txtGameName" placeholder="enter game name"/><br/>
                Description:<br/>
                <textarea style="resize:none;background-color:rgb(15,25,40); color:white;" class="form-control" rows="10" id="comment" name="txtDescription" placeholder="say something about this discussion"></textarea><br/>
                ID Moderator:<br/>
                <input style="background-color:rgb(15,25,40); color:white;" type="text" class="form-control" id="usr" name="txtModerator" placeholder="enter moderator id"/><br/>
                Image:<br/>
                <input type="file" style="width:500px;color:black" name="txtImg" /><br/><br/>

                <input type="submit" class="btn btn-default" style="background-color:rgb(27,40,56); margin-top:5px;color:white; font-family: lato; font-size: 16px;" value="Create Discussion"><br/>
                </form>
            </div>
        </div>
        <jsp:include page="footer.jsp" flush="true" />
    </body>
</html>
