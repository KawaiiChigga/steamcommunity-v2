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
                <div class="highlight">
                    <div class="highlight_left">
                        <h3>Sign In</h3>
                        <p>To an existing Steam account</p>
                        <form action="login" method="POST">
                            Steam username <br/>
                            <input type="text" name="txtUsername" /> <br/>
                            Password <br/>
                            <input type="password" name="txtPassword" /> <br/>
                            <input type="checkbox" name="checkRemember" /> Remember me on this computer <br/><br/>
                            <input type="submit" name="btnSubmit" value="Sign in"/>
                        </form>
                        <%
                            FBConnection fbConnection = new FBConnection();
                        %>
                        <a href="<%=fbConnection.getFBAuthUrl()%>">Login Via Facebook</a>
                        <a href="#">Forgot your password?</a>
                    </div>
                    <div class="highlight_right">
                        <h3>Create</h3>
                        <p>A new free account</p>
                        <p>It's free to join and easy to use. Continue on to create your Steam account 
                            and get Steam, the leading digital solution for PC, Mac, and Linux games and Software.</p>
                        <a href="join.jsp"><input type="button" value="Join Steam" /></a>
                    </div>
                    <div style="clear: both;"></div>
                </div>
                <div id="" style="text-align: center;">
                    <img src="image/about_steam_preview.png" alt=""/>
                </div>
            </div>
            <jsp:include page="footer.jsp" flush="true" />
        </div>
    </body>
</html>
