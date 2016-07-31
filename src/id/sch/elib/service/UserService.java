/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import id.sch.elib.model.User;
import id.sch.elib.util.BaseServiceInterface;
import id.sch.elib.util.GrailsRestClient;
import id.sch.elib.util.Message;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rizky.aditya
 */
public class UserService implements BaseServiceInterface {

    private final Gson gson = new Gson();
    private final String endpoint = "user";
    private final GrailsRestClient grc = new GrailsRestClient();

    @Override
    public Object listAll() {
        String json = grc.get(endpoint);
        Type type = new TypeToken<List<User>>() {
        }.getType();
        List<User> output = gson.fromJson(json, type);
        return output;
    }

    public Object ListDetails() {
        String json = grc.get(endpoint + "/userDetails");
        Type type = new TypeToken<List<User>>() {
        }.getType();
        List<User> output = gson.fromJson(json, type);
        return output;
    }

    @Override
    public Object get(long id) {
        User jo = gson.fromJson(grc.get(endpoint + "/" + id), User.class);
        return jo;
    }

    @Override
    public Object save(Object obj) {
        String response = grc.add(endpoint, obj);
        Message jo = gson.fromJson(response, Message.class);
        return jo;
    }

    @Override
    public Object update(Object obj) {
        User cast = (User) obj;
        Message jo = gson.fromJson(grc.put(endpoint + "/" + cast.getId(), cast), Message.class);
        return jo;
    }

    @Override
    public Object delete(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object search(Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String response = grc.add(endpoint + "/search", obj);
        Type type = new TypeToken<List<User>>() {
        }.getType();
        ArrayList<User> list = gson.fromJson(response, type);
        return list;
    }

    public Object login(Object obj) {
        String response = grc.add(endpoint + "/login", obj);
        User jo = gson.fromJson(response, User.class);
        return jo;
    }
}
