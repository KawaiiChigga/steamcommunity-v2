/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

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
    private static final String BASE_URI = "http://127.0.0.1:8000/";

    public JerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI);
    }

//    public String login(){
//        WebTarget resource = webTarget;
//        resource = resource.path("account").path("login");
//        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
//    }
    public String searchAccount(String uid) {
        WebTarget resource = webTarget;
        resource = resource.path("account").path("search").queryParam("text", uid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String user(String uid) {
        WebTarget resource = webTarget;
        resource = resource.path("account").path(uid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }
    
    public String getUser(String uid) {
        WebTarget resource = webTarget;
        resource = resource.path("account").path(uid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String getAllDiscussion() {
        WebTarget resource = webTarget;
        resource = resource.path("discussion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String discussion() {
        WebTarget resource = webTarget;
        resource = resource.path("discussion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
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

    public String post(int postid) {
        WebTarget resource = webTarget;
        resource = resource.path("post").path("" + postid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String createPost() {
        WebTarget resource = webTarget;
        resource = resource.path("post");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String getAllPost(String threadid) {
        WebTarget resource = webTarget;
        resource = resource.path("post").path("thread").path("" + threadid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String createThread() {
        WebTarget resource = webTarget;
        resource = resource.path("thread");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String getThread(String threadid) {
        WebTarget resource = webTarget;
        resource = resource.path("thread").path("" + threadid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String searchThread(String text, int disid) {
        WebTarget resource = webTarget;
        resource = resource.path("thread").path("search").path(text).path("" + disid);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String getAllThread(String disid, String category) {
        WebTarget resource = webTarget;
        resource = resource.path("thread").path("discussion").path("" + disid).path("" + category);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }


//    public String addFriend() {
//        WebTarget resource = webTarget;
//        resource = resource.path("friend");
//        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
//    }
//
//    public String checkFriendStatus(int uid, int fid) {
//        WebTarget resource = webTarget;
//        resource = resource.path("friend?uid=" + uid + "&fid=" + fid);
//        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
//    }
//
//    public String getFriendByUserId(int uid) {
//        WebTarget resource = webTarget;
//        resource = resource.path("friend").path("user").path("" + uid);
//        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
//    }

    public void close() {
        client.close();
    }
}
