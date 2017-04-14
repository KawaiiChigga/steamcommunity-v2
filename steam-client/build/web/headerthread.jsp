<%-- 
    Document   : headerthread
    Created on : Feb 23, 2017, 6:34:59 PM
    Author     : Sujana
--%>

<%@page import="org.json.simple.JSONValue"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="client.JerseyClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="headerthread">
    <%
         JerseyClient jc = new JerseyClient();
            JSONObject d = (JSONObject) JSONValue.parse(jc.getDiscussion(request.getParameter("id")));
            String disid = d.get("discussionid").toString();
            String gamename = (String) d.get("gamename");
    %>
    <div class="linkbarthread">
        <h3><%=gamename%></h3> 
        <a href="thread.jsp?id=<%=disid%>" class="linktext">Discussions</a>
        <a href="thread.jsp?id=<%=disid%>&desc=true" class="linktext">Description</a>
        <hr/>
    </div>
</header>