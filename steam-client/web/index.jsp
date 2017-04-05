<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONValue"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="client.JerseyClient"%>
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
                <h3 style="color: #66C0F4">Welcome to the Steam Discussions</h3>
                <p><b>Everyone is invited!</b> The Steam discussions are for everyone, new and 
                    advanced user alike! </p>
                <p><b>Searching is key!</b> Before you post a question, use the forum search feature 
                    to determine whether your topic has already been covered. <br/> </p>
                <p><b>Do not start flame wars!</b> If someone has engaged in behavior that is a detriment 
                    to the message board -- spamming, flaming people, etc -- contact one of the forum moderators 
                    or report the post. Flaming the offensive user will only increase the problem. 
                    Harassment is not tolerated. </p>

                <form action="search.jsp" method="get">
                    <input type="text" name="Searching" placeholder="Search discussions" />
                    <input type="submit" value="Search" />
                </form>

                <br/>
                <h3 style="color: #66C0F4">Game Forums</h3>
                <div class="line"></div><br>
                <div class="contenthomeleft">
                    <%
                        JerseyClient jc = new JerseyClient();
                        JSONArray data = (JSONArray) JSONValue.parse(jc.getAllDiscussion());
                        for (int i = 0; i < data.size(); i++) {
                            JSONObject temp = (JSONObject) data.get(i);
                            Long id = (Long) temp.get("discussionid");
                            String gamename = (String) temp.get("gamename");
                            String imgurl = (String) temp.get("imgurl");
                            %>
     
                            <div class="discussionbar">
                                <div class="discussionbarlogo" style="
                                    background-image: url('image/games/<%=imgurl%>');
                                    background-repeat: no-repeat;
                                    background-size: contain;">
                                </div>
                                <a href="thread.jsp?id=<%=id%>" class="discussiontitle"><%=gamename%></a>
                                <a href="thread.jsp?id=<%=id%>" class="viewall">VIEW ALL</a>
                            </div>
                    <%
                        }
                    %>
                </div>
                    <%--
                        User u = CtrlAccount.getUser((Integer) session.getAttribute("currentsession"));
                        if (u != null) {
                            if (u.getUserId() == 1) {
                    %>
                            <div class="contenthomeright">
                                <a href="addDiscussion.jsp"><input type="submit" class="btn btn-default" style="background-color:rgb(27,40,56); margin-top:5px;color:white; float:right; font-family: lato; font-size: 16px;" value="Create New Discussion"></a>
                            </div>
                    <%
                            }
                        }
                    --%>
                
            </div>
            
        </div>
            <jsp:include page="footer.jsp" flush="true" />
    </body>
</html>
