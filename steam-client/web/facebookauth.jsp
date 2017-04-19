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

<%
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
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp" flush="true" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="header.jsp" flush="true" />
            <div class="content">
                <h2>Create an Account</h2>
                <div class="accountbox">
                    <h4>Hello, <%=name%>!</h4>
                    <br/>
                    Please insert your Steam account name and new password to be registered in this website
                    <br/>
                    <form action="fbregister" method="POST">
                        <table>
                            <tr><td>Account name<br/>
                                    <input type="text" name="txtAccount" required/></td></tr>
                            <tr><td>Password<br/>
                                    <input type="password" name="txtPassword" required/></td></tr>
                        </table>
                        <br/>
                        <input type="hidden" name="txtEmail" value="<%=email%>"/>
                        <input type="hidden" name="txtName" value="<%=name%>"/>
                        <input type="hidden" name="txtImageURL" value="<%=picture%>"/>
                        <input type="submit" value="Create my account"/>
                    </form>
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

<%
    } else {
        session.setAttribute("currentsession", user.get("userid").toString());
        response.sendRedirect("profile.jsp?uid=" + user.get("userid").toString());
    }
%>
