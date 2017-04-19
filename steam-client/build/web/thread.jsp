<%-- 
    Document   : thread
    Created on : Feb 23, 2017, 6:21:14 PM
    Author     : Sujana
--%>

<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONValue"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="client.JerseyClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="head.jsp" flush="true" />
    </head>
    <body>
        <%
            JerseyClient jc = new JerseyClient();
            JSONObject d = (JSONObject) JSONValue.parse(jc.getDiscussion(request.getParameter("id")));
            String disid = d.get("discussionid").toString();
        %>
        <div class="container">
            <jsp:include page="header.jsp" flush="true" />
            <div class="content">
                <jsp:include page="headerthread.jsp" flush="true" />
                <div class="contentthread">
                    <%  String showThread = "";
                        if (request.getParameter("desc") != null) {
                            showThread = request.getParameter("desc");
                        }
                        if (showThread.equals("true")) {
                    %>
                    <div class="contentthreadleft">
                        <p><%=((String) d.get("description")).replace("\n", "<br/>")%></p>
                    </div>
                    <%
                    } else {
                    %>
                    <div class="contentthreadleft">
                        <%
                            String category = request.getParameter("categoryID");
                            String id = request.getParameter("id");
                            if (category == null) {
                                category = "1";
                            }
                            JSONArray data;
                            if (category.equals("2")) {
                                data = (JSONArray) JSONValue.parse(jc.getAllThread(disid, category));
                            } else {
                                data = (JSONArray) JSONValue.parse(jc.getAllThread(disid, "1"));
                            }
                            for (int i = 0; i < data.size(); i++) {
                                JSONObject temp = (JSONObject) data.get(i);
                                String tid = temp.get("threadid").toString();
                                String title = (String) temp.get("title");
                                String time = (String) temp.get("publishdatetime");
                                out.println("<a href='post.jsp?tid=" + tid + "&id=" + disid + "'><div class=contentth>");
                                out.println(title + "<p class='viewall' style='margin-top:-1px;'>" + time + "</p>");
                                out.println("</div></a>");
                            }
                        %>
                    </div>
                    <%
                        }
                    %>
                    <div class="contentthreadright">
                        <jsp:include page="rightcontent.jsp" flush="true" />
                    </div>
                </div>
            </div>

        </div>
        <jsp:include page="footer.jsp" flush="true" />
    </body>
</html>
