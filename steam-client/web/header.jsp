<%@page import="org.json.simple.JSONValue"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="client.JerseyClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="header">
    <%
    String uid = (String) session.getAttribute("currentsession");
    %>
    <div class="headerlogo">
        <a href="index.jsp"><img src="image/globalheader_logo.png" alt=""/></a>
    </div>
    <div class="linkbar">
        <a href="index.jsp" class="linktext">DISCUSSIONS</a>
        <a href="about.jsp" class="linktext">ABOUT</a>
        <a href="support.jsp" class="linktext">SUPPORT</a>
        <%
        if(uid!=null){
            if(uid.equals("1")){
               
        %>
        
        <a href="admin.jsp" class="linktext">ADMIN</a>
        
        <%
            }
        }
        %>
    </div>
    <div class="loginbutton">
        <%
            
            if (uid == null) {
                out.println("<a href='login.jsp'>login</a>");
            } else {
                JerseyClient jc = new JerseyClient();
                JSONObject obj = (JSONObject) JSONValue.parse(jc.getUser(uid));
                String username = (String) obj.get("username");
                out.println("<a href='profile.jsp?uid=" + uid + "'>" + username + "</a>");
            }
        %>
        
    </div>
</header>