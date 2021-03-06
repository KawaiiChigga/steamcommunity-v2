/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import org.json.simple.JSONObject;


/**
 * Jersey REST client generated for REST resource:CobaWS [cobaWS]<br>
 * USAGE:
 * <pre>
 *        JerseyClient client = new JerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Daniel
 */
public class JerseyClient {

    private WebTarget webTarget;
    private Client client;
//    private static final String BASE_URI = "http://127.0.0.1:8000/";
//    private static final String BASE_URI = "http://192.168.68.1:8000/";
    private static final String BASE_URI = "http://192.168.68.1:8080/webservice/webresources/service/";

    public JerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI);
    }

    // ACCOUNT
    
    public String login(JSONObject obj){
        WebTarget resource = webTarget;
        resource = resource.path("account").path("login");
        String res = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(Entity.json(obj.toJSONString()), String.class);
        return res;
    }
    
    public String createAccount(JSONObject obj){
        WebTarget resource = webTarget;
        resource = resource.path("account/");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(Entity.json(obj.toJSONString()), String.class);
    }
    
    public String searchAccount(String uid) {
        WebTarget resource = webTarget;
        resource = resource.path("account").path("search").queryParam("text", uid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String editUser(String uid, JSONObject obj) {
        WebTarget resource = webTarget;
        resource = resource.path("account").path(uid + "/");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(Entity.json(obj.toJSONString()), String.class);
    }
    
    public String getUser(String uid) {
        WebTarget resource = webTarget;
        resource = resource.path("account").path(uid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String checkByEmail(String email){
        WebTarget resource = webTarget;
        resource = resource.path("account").path("email").path(email+"/");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }
    
    // DISCUSSION
    
    public String getAllDiscussion() {
        WebTarget resource = webTarget;
        resource = resource.path("discussion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void insertDiscussion(JSONObject obj) {
        WebTarget resource = webTarget;
        resource = resource.path("discussion/");
        resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(Entity.json(obj.toJSONString()));
    }

    public String getDiscussion(String disid) {
        WebTarget resource = webTarget;
        resource = resource.path("discussion").path("" + disid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String searchDiscussion(String disid) {
        WebTarget resource = webTarget;
        resource = resource.path("discussion").path("search").queryParam("text", disid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    // POST
    
    public String createPost(JSONObject obj) {
        WebTarget resource = webTarget;
        resource = resource.path("post/");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(Entity.json(obj.toJSONString()), String.class);
    }
    
    public String getPost(String postid) {
        WebTarget resource = webTarget;
        resource = resource.path("post").path(postid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String updatePost(String postid, JSONObject obj) {
        WebTarget resource = webTarget;
        resource = resource.path("post").path(postid+"/");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(Entity.json(obj.toJSONString()), String.class);
    }

    public String getAllPost(String threadid) {
        WebTarget resource = webTarget;
        resource = resource.path("post").path("thread").path("" + threadid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }
    
    public String getPostByUserId (String uid) {
        WebTarget resource = webTarget;
        resource = resource.path("post").path("user").path(uid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    // THREAD
    
    public String createThread(JSONObject obj) {
        WebTarget resource = webTarget;
        resource = resource.path("thread/");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(Entity.json(obj.toJSONString()), String.class);
    }

    public String getThread(String threadid) {
        WebTarget resource = webTarget;
        resource = resource.path("thread").path("" + threadid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String searchThread(String text, String disid) {
        WebTarget resource = webTarget;
        resource = resource.path("thread").path("search").path(disid).queryParam("text", text);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String getAllThread(String disid, String category) {
        WebTarget resource = webTarget;
        resource = resource.path("thread").path("discussion").path("" + disid).path("" + category);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }
    
    // FRIEND

    public String checkFriendStatus(String uid, String fid) {
        WebTarget resource = webTarget;
        resource = resource.path("friend").path("check").queryParam("uid", uid).queryParam("fid", fid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }
    
    public String getFriendByUserId(String uid, String all) {
        WebTarget resource = webTarget;
        resource = resource.path("friend").path("user").path(uid).queryParam("all", all);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }
    
    public String getRequestedFriendByUserId(String uid){
        WebTarget resource = webTarget;
        resource = resource.path("friend").path("user").path("req").path(uid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }
    
    public String addFriend(JSONObject obj) {
        WebTarget resource = webTarget;
        resource = resource.path("friend/");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(Entity.json(obj.toJSONString()), String.class);
    }
    
    public String confirmFriend(String uid, String fid, JSONObject obj){
        WebTarget resource = webTarget;
        resource = resource.path("friend").path("confirm").path(uid).path(fid+"/");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(Entity.json(obj.toJSONString()), String.class);
    }

    public void close() {
        client.close();
    }
}
