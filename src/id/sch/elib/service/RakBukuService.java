/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import id.sch.elib.model.RakBuku;
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
public class RakBukuService implements BaseServiceInterface{

    private final Gson gson = new Gson();
    private final String endpoint = "rakBuku";
    private final GrailsRestClient grc=new GrailsRestClient();
    
    @Override
    public Object listAll() {
        String json = grc.get(endpoint);
        Type type = new TypeToken<List<RakBuku>>() {
        }.getType();
        List<RakBuku> output = gson.fromJson(json, type);
        return output;
    }

    @Override
    public Object get(long id) {
        RakBuku jo = gson.fromJson(grc.get(endpoint + "/" + id), RakBuku.class);
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
        RakBuku cast = (RakBuku) obj;
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
        Type type = new TypeToken<List<RakBuku>>() {
        }.getType();
        ArrayList<RakBuku> list = gson.fromJson(response, type);
        return list;
    }
    
}
