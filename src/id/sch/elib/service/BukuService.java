/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import id.sch.elib.model.Buku;
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
public class BukuService implements BaseServiceInterface{

    private final Gson gson = new Gson();
    private final GrailsRestClient grc=new GrailsRestClient();
    private final String endpoint = "buku";
    
    @Override
    public Object listAll() {
        String json = grc.get(endpoint);
        Type type = new TypeToken<List<Buku>>() {
        }.getType();
        List<Buku> output = gson.fromJson(json, type);
        return output;
        
    }

    @Override
    public Object get(long id) {
        Buku jo = gson.fromJson(grc.get(endpoint + "/" + id), Buku.class);
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
        Buku cast = (Buku) obj;
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
        Type type = new TypeToken<List<Buku>>() {
        }.getType();
        ArrayList<Buku> list = gson.fromJson(response, type);
        return list;
    }
    
}
