<%@page import="org.json.simple.JSONArray"%>
<%@page import="client.JerseyClient"%>
<%@page import="org.json.simple.JSONValue"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp" flush="true" />
    </head>
    <body>
        <%
            JerseyClient jc = new JerseyClient();
            
            JSONObject ses = (JSONObject) JSONValue.parse(jc.getUser((String) session.getAttribute("currentsession")));
            JSONObject req = (JSONObject) JSONValue.parse(jc.getUser((String) request.getParameter("uid")));
        %>
        <div class="container">
            <jsp:include page="header.jsp" flush="true" />
            <div class="profile_header">
                <div class="profile_avatar" 
                     style="background-image: url('image/user/<%=req.get("imageurl").toString()%>'); 
                     background-repeat: no-repeat;
                     background-size: contain">
                </div>
                <div class="profile_summary">
                    <div class="profile_persona">
                        <h3><%=req.get("username").toString()%></h3>
                        <p><b><%=req.get("name").toString()%></b> 
                            <%
                                if (!req.get("city").toString().equals("") || !req.get("province").toString().equals("") || !req.get("country").toString().equals("")) {
                                    out.println("at");
                                    out.println(req.get("city").toString() + " " + req.get("province").toString() + " " + req.get("country").toString());
                                }
                            %> 
                        </p>
                    </div>
                    <div class="profile_desc">
                        <%=req.get("description").toString()%>
                    </div>
                    <div style="clear: both;"></div>
                </div>
                <div class="profile_edit_btn">
                    <%
                        if (ses == null) {
                            
                        } else if (ses.get("username").toString().equals(req.get("username").toString())) {
                    %>
                            <a href="editprofile.jsp"><input type="button" class="btn btn-default" 
                                style="background-color:rgb(27,40,56); width:75%;color:white; float:right; font-family: lato; font-size: 16px;" value="Edit Profile"></a><br/><br/>
                            <a href="logout?logout=yes"><input type="button" class="btn btn-default" 
                                style="background-color:rgb(27,40,56); width:75%; margin-top:5px;color:white; float:right; font-family: lato; font-size: 16px;" value="Log Out"></a><br/><br/>
                            
                    <%  
                        } else{
                            JSONObject check = (JSONObject) JSONValue.parse(jc.checkFriendStatus(ses.get("userid").toString(), req.get("userid").toString()));
                            if (check == null) {
                    %>
                            <a href="friend?friendid=<%=req.get("userid").toString()%>&status=save"><input type="button" class="btn btn-default" 
                                style="background-color:rgb(27,40,56); width:75%; margin-top:5px;color:white; float:right; font-family: lato; font-size: 16px;" value="Add Friend"></a><br/><br/>
                    <%  
                            } 
                        }
                    %>
                </div>
                <div style="clear: both;"></div>
            </div>
            <div class="profile_content">
                <div class="profile_content_left">
                    <h2>Recent Activity</h2>
                    <hr />
                    
                    <%
                        JSONArray activity = (JSONArray) JSONValue.parse(jc.getPostByUserId(req.get("userid").toString()));
                        for (int i = 0; i < activity.size(); i++) {
                            JSONObject th = (JSONObject) JSONValue.parse(jc.getThread(((JSONObject) activity.get(i)).get("threadid").toString()));
                    %>
                            <div class="activity">
                                <div class="activitytitle">
                                    <a href="post.jsp?tid=<%=th.get("threadid").toString()%>&id=<%=th.get("discussionid").toString()%>"><%= th.get("title").toString() %></a>
                                    <p class="viewall" style="margin-top: 2px;"><%=th.get("publishdatetime").toString()%></p>
                                </div>
                                <div class="activitycontent">
                                    <%=((JSONObject) activity.get(i)).get("message").toString() %>
                                </div>
                            </div>
                    <%
                        }
                    %>
                </div>
                <div class="profile_content_right">
                    <form action="search.jsp" method="GET">
                        <input type="text" name="searchfriend" placeholder="Search Friends" />
                        
                        <input type="submit" value="Search" />
                    </form>
                    <%
                        JSONArray list = (JSONArray) JSONValue.parse(jc.getFriendByUserId(req.get("userid").toString(), "0"));
                        out.println("<h4>Friends " + list.size() + "</h4>");
                        if (ses != null) {
                            if (ses.get("username").toString().equals(req.get("username").toString())) {
                                JSONArray listReq = (JSONArray) JSONValue.parse(jc.getRequestedFriendByUserId(ses.get("userid").toString()));
                                for (int i = 0; i < listReq.size(); i++) {
                                    JSONObject a = (JSONObject) JSONValue.parse(jc.getUser(((JSONObject) listReq.get(i)).get("userid").toString()));
                        %>
                                        <a href="profile.jsp?uid=<%=a.get("userid").toString()%>">
                                            <div class="friendlist">
                                                <div class="friendlistdisplay" style="
                                                    background-image: url('image/user/<%=a.get("imageurl").toString()%>'); 
                                                    background-repeat: no-repeat;
                                                    background-size: contain"></div>
                                                <div class="friendlistusername">
                                                    <%=a.get("username").toString()%><br>
                                                        <a href='friend?friendid=<%=a.get("userid").toString()%>&status=update' style='font-size:10px;'>Accept Friend Request</a>
                                                </div>
                                            </div>
                                        </a>
                        <%
                                }
                            }
                        }
                        for (int i = 0; i < list.size(); i++) {
                            JSONObject a = (JSONObject) JSONValue.parse(jc.getUser(((JSONObject) list.get(i)).get("friendid").toString()));
                        %>
                            <a href="profile.jsp?uid=<%=a.get("userid").toString()%>">
                                <div class="friendlist">
                                    <div class="friendlistdisplay" style="
                                        background-image: url('image/user/<%=a.get("imageurl").toString()%>'); 
                                        background-repeat: no-repeat;
                                        background-size: contain"></div>
                                    <div class="friendlistusername">
                                        <%=a.get("username").toString()%><br>
                                    </div>
                                </div>
                            </a>
                    <%
                        }
                    %>
                        
                </div>
                <div style="clear: both;"></div>
            </div>
            
            <jsp:include page="footer.jsp" flush="true" />
        </div>
    </body>
</html>
