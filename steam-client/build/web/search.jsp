<%-- 
    Document   : search
    Created on : Mar 7, 2017, 7:59:00 PM
    Author     : Sujana
--%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONValue"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="client.JerseyClient"%>
<%@page import="java.util.ArrayList"%>
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
                <div class="contenthomeleft">
                <%
                    JerseyClient jc = new JerseyClient();
                    String cari = request.getParameter("Searching");
                    String carithread = request.getParameter("search");
                    String carif = request.getParameter("searchfriend");
                    if (cari != null) {
                        JSONArray dat = (JSONArray) JSONValue.parse(jc.searchDiscussion(cari));
                        out.print("<h3 style=color:#66C0F4>Keyword: " + cari + "</h3>");
                        out.print("<div class=line></div><br>");
                        for (int i = 0; i < dat.size(); i++) {
                            JSONObject temp = (JSONObject) dat.get(i);
                %> 
                            <div class="discussionbar">
                                <div class="discussionbarlogo" style="
                                     background-image: url('image/games/<%=temp.get("imgurl").toString()%>');
                                     background-repeat: no-repeat;
                                     background-size: contain;">
                                </div>
                                     <a href="thread.jsp?id=<%=temp.get("discussionid").toString()%>" class="discussiontitle"><%=temp.get("gamename").toString()%></a>
                                <a href="thread.jsp?id=<%=temp.get("discussionid").toString()%>" class="viewall">VIEW ALL</a>
                            </div>
                <%
                        }
                    } else if (carithread != null) {
                        String index = (String) request.getParameter("discID");
                        JSONArray da = (JSONArray) JSONValue.parse(jc.searchThread(carithread, index));
                        out.print("<h3 style=color:#66C0F4>Keyword: " + carithread + "</h3>");
                        out.print("<div class=line></div><br>");
                        for (int i = 0; i < da.size(); i++) {
                            JSONObject temp = (JSONObject) da.get(i);
                %>
                            <a href="post.jsp?tid=<%=temp.get("threadid").toString()%>&id=<%=index%>">
                                <div class="contentth"><%=temp.get("title").toString()%></div>
                            </a>
                <%
                        }
                    } else if (carif != null) {
                        JSONArray dat = (JSONArray) JSONValue.parse(jc.searchAccount(carif));
                        out.print("<h3 style=color:#66C0F4>Keyword: " + carif + "</h3>");
                        out.print("<div class=line></div><br>");
                        for (int i = 0; i < dat.size(); i++) {
                            JSONObject temp = (JSONObject) dat.get(i);
                %>
                            <div class="headpost" style="margin-bottom: 5px">
                                <div class="profile_post" style="
                                     background-image: url('image/user/<%=temp.get("imageurl").toString()%>');
                                    background-repeat: no-repeat;
                                    background-size: contain;">
                                </div>
                                <div class="profile_namepost">
                                    <a href="profile.jsp?uid=<%=temp.get("userid").toString()%>" class="discussiontitle" style="margin-top: 4px"><%=temp.get("username").toString()%></a>
                                </div>
                            </div>
                <%
                            }
                        }
                %>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp" flush="true" />
    </body>
</html>
