/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * REST Web Service
 *
 * @author Daniel
 */
@Path("service")
public class ServiceResource {

    @Context
    private UriInfo context;

    public ServiceResource() {
    }

    // ACCOUNT
    @Path("/account")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createAccount(String s) {
        JerseyUser ju = new JerseyUser();
        JSONObject obj = (JSONObject) JSONValue.parse(s);
        return ju.createAccount(obj);
    }
    
    @Path("/account/{pk}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("pk") String id) {
        JerseyUser ju = new JerseyUser();
        return ju.getUser(id);
    }
    
    @Path("/account/{pk}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editUser(@PathParam("pk") String id, String s) {
        JerseyUser ju = new JerseyUser();
        JSONObject obj = (JSONObject) JSONValue.parse(s);
        return ju.editUser(id, obj);
    }
    
    @Path("/account/email/{email}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String checkByEmail(@PathParam("email") String email) {
        JerseyUser ju = new JerseyUser();
        return ju.checkByEmail(email);
    }
    
    @Path("/account/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(String s) {
        JerseyUser ju = new JerseyUser();
        JSONObject obj = (JSONObject) JSONValue.parse(s);
        return ju.login(obj);
    }
    
    @Path("/account/search")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String searchAccount(@QueryParam("text") String text) {
        JerseyUser ju = new JerseyUser();
        return ju.searchAccount(text);
    }
    
    // FRIENDS
    
    @Path("/friend")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addFriend(String s) {
        JerseyUser ju = new JerseyUser();
        JSONObject obj = (JSONObject) JSONValue.parse(s);
        return ju.addFriend(obj);
    }
    
    @Path("/friend/confirm/{uid}/{fid}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String confirmFriend(@PathParam("uid") String uid, @PathParam("fid") String fid, String s) {
        JerseyUser ju = new JerseyUser();
        JSONObject obj = (JSONObject) JSONValue.parse(s);
        return ju.confirmFriend(uid, fid, obj);
    }
    
    @Path("/friend/check")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String checkFriendStatus(@QueryParam("uid") String uid, @QueryParam("fid") String fid) {
        JerseyUser ju = new JerseyUser();
        return ju.checkFriendStatus(uid, fid);
    }
    
    @Path("/friend/user/{uid}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getFriendByUserId(@PathParam("uid") String uid, @QueryParam("all") String all) {
        JerseyUser ju = new JerseyUser();
        return ju.getFriendByUserId(uid, all);
    }
    
    @Path("/friend/user/req/{uid}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getRequestedFriendByUserId(@PathParam("uid") String uid) {
        JerseyUser ju = new JerseyUser();
        return ju.getRequestedFriendByUserId(uid);
    }
    
    // DISCUSSION
    
    @Path("/discussion")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllDiscussion() {
        JerseyData jd = new JerseyData();
        return jd.getAllDiscussion();
    }
    
    @Path("/discussion")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertDiscussion(String s) {
        JerseyData jd = new JerseyData();
        JSONObject obj = (JSONObject) JSONValue.parse(s);
        return jd.insertDiscussion(obj);
    }
    
    @Path("/discussion/{pk}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllDiscussion(@PathParam("pk") String disid) {
        JerseyData jd = new JerseyData();
        return jd.getDiscussion(disid);
    }
    
    @Path("/discussion/search")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String searchDiscussion(@QueryParam("text") String disid) {
        JerseyData jd = new JerseyData();
        return jd.searchDiscussion(disid);
    }
    
    // POST
    
    @Path("/post")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createPost(String s) {
        JerseyData jd = new JerseyData();
        JSONObject obj = (JSONObject) JSONValue.parse(s);
        return jd.createPost(obj);
    }
    
    @Path("/post/{pk}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getPost(@PathParam("pk") String postid) {
        JerseyData jd = new JerseyData();
        return jd.getPost(postid);
    }
    
    @Path("/post/{pk}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String searchDiscussion(@PathParam("pk") String postid, String s) {
        JerseyData jd = new JerseyData();
        JSONObject obj = (JSONObject) JSONValue.parse(s);
        return jd.updatePost(postid, obj);
    }
    
    @Path("/post/thread/{threadid}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPost(@PathParam("threadid") String threadid) {
        JerseyData jd = new JerseyData();
        return jd.getAllPost(threadid);
    }
    
    @Path("/post/user/{uid}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getPostByUserId(@PathParam("uid") String uid) {
        JerseyData jd = new JerseyData();
        return jd.getPostByUserId(uid);
    }
    
    // THREAD
    
    @Path("/thread")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createThread(String s) {
        JerseyData jd = new JerseyData();
        JSONObject obj = (JSONObject) JSONValue.parse(s);
        return jd.createThread(obj);
    }
    
    @Path("/thread/{threadid}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getThread(@PathParam("threadid") String threadid) {
        JerseyData jd = new JerseyData();
        return jd.getThread(threadid);
    }
    
    @Path("/thread/search/{discussionid}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String searchThread(@PathParam("disid") String disid, @QueryParam("text") String text) {
        JerseyData jd = new JerseyData();
        return jd.searchThread(text, disid);
    }
    
    @Path("/thread/discussion/{discussionid}/{categoryid}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllThread(@PathParam("discussionid") String disid, @PathParam("categoryid") String category) {
        JerseyData jd = new JerseyData();
        return jd.getAllThread(disid, category);
    }
    /**
     * PUT method for updating or creating an instance of ServiceResource
     * @param content representation for the resource
     */
}
