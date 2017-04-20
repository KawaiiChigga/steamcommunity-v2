/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.ClientErrorException;
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
public class JerseyData {

    private WebTarget webTarget;
    private Client client;
//    private static final String BASE_URI = "http://192.168.68.1:8001/";
    private static final String BASE_URI = "http://127.0.0.1:8000/";

    public JerseyData() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI);
    }

    public String getAllDiscussion() {
        WebTarget resource = webTarget;
        resource = resource.path("discussion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String insertDiscussion(JSONObject obj) {
        WebTarget resource = webTarget;
        resource = resource.path("discussion/");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(Entity.json(obj.toJSONString()), String.class);
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
    
    public void close() {
        client.close();
    }
    
}
