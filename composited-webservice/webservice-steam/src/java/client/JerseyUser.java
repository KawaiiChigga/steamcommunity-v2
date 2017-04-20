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
public class JerseyUser {

    private WebTarget webTarget;
    private Client client;
//    private static final String BASE_URI = "http://192.168.68.1:8002/";
    private static final String BASE_URI = "http://127.0.0.1:8001/";

    public JerseyUser() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI);
    }
    
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
