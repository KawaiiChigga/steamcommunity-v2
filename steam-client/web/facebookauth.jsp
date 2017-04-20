<%-- 
    Document   : facebookauth
    Created on : Apr 19, 2017, 10:22:10 AM
    Author     : Daniel
--%>

<%@page import="org.json.simple.JSONValue"%>
<%@page import="client.JerseyClient"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="fb.FBGraph"%>
<%@page import="fb.FBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp" flush="true" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="header.jsp" flush="true" />
            <div class="content">
                <%
                    String success = request.getParameter("success");
                    if (success.equals("true")) {
                %>
                    <h2>Success!</h2>
                    <div class="accountbox">
                        <%
                            String ses = (String) session.getAttribute("currentsession");
                            JerseyClient jc = new JerseyClient();
                            JSONObject u = (JSONObject) JSONValue.parse(jc.getUser(ses));
                            String name = u.get("name").toString();
                        %>
                        <h4>Hello, <%=name%>!</h4>
                        <br/>
                        An account has been created for you.
                        <br/>
                        Click <b><a href="profile.jsp?uid=<%=ses%>">here</a></b> to continue
                <%
                    }
                %>
                </div>
                <div class="joinbox">
                    <h3 style="color: #66C0F4">WHY JOIN STEAM?</h3>
                    <ul id="why_list">
                        <li>Buy and download full retail games</li>
                        <li>Join the Steam Community</li>
                        <li>Chat with your friends while gaming</li>
                        <li>Play your games on any supported platform</li>
                        <li>Schedule a game, tournament, or LAN party</li>
                        <li>Receive automatic game updates, and more!</li>
                    </ul>
                    <br />
                    <img src="image/why_join_preview.png" width="265" height="176" border="0" >
                    <br><br>
                    <span id="join_sys_req_title">System Requirements</span>
                    <div id="join_sys_req">
                        Windows XP, Vista, or 7<br>
                        512 MB RAM<br>
                        1 Ghz or faster processor<br><br>
                        Intel Mac, OS X version 10.7 (Lion), or later.<br>
                        Two-button mouse strongly recommended<br><br>
                        1GB HD space (recommended)<br>
                        Internet connection (broadband recommended)
                    </div>
                    <br><br>
                </div>
                <div style="clear: both;"></div>
            </div>
            <jsp:include page="footer.jsp" flush="true" />
        </div>
    </body>
</html>
