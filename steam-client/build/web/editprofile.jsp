<%@page import="client.JerseyClient"%>
<%@page import="org.json.simple.JSONValue"%>
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
            JSONObject u = (JSONObject) JSONValue.parse(jc.getUser((String) session.getAttribute("currentsession")));
        %>
        <div class="container">
            <jsp:include page="header.jsp" flush="true" />
            <div class="edit_profile">
                <div class="edit_profile_avatar" 
                     style="background-image: url('image/user/<%=u.get("imageurl").toString()%>'); 
                     background-repeat: no-repeat;
                     background-size: contain">
                </div>
                <div class="edit_profile_name">
                    <h3><%=u.get("username").toString()%></h3>
                </div>
            </div>
            <div class="profile_content">
                <div class="profile_content_left">
                    <h2>Edit Profile</h2> <br/>
                    <form action="edit" method="POST" enctype="multipart/form-data">
                        <table>
                            <tr><td>Profile Name</td>
                                <td><input type="text" name="txtProfileName" value="<%=u.get("username").toString()%>"/></td></tr>
                            <tr><td>Real Name (?)</td>
                                <td><input type="text" name="txtRealName" value="<%=u.get("name").toString()%>"/></td></tr>
                            <tr><td>Country (?)</td>
                                <td><input type="text" name="txtCountry" value="<%=u.get("country").toString()%>"></td></tr>
                            <tr><td>State/Province</td>
                                <td><input type="text" name="txtProvince" value="<%=u.get("province").toString()%>"></td></tr>
                            <tr><td>City</td>
                                <td><input type="text" name="txtCity" value="<%=u.get("city").toString()%>"></td></tr>
                            <tr><td>Avatar</td>
                                <td><input type="file" name="fileAvatar"></td></tr>
                            <tr><td>Summary (?)</td>
                                <td><textarea name="txtSummary" rows="8" cols="60"><%=u.get("description").toString()%></textarea></td></tr>
                        </table>
                        <br/>
                        <input type="submit" value="Save Changes">
                    </form>
                </div>
            </div>
            
            <jsp:include page="footer.jsp" flush="true" />
        </div>
    </body>
</html>
