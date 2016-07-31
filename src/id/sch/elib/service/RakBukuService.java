/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import id.sch.elib.model.RakBuku;
import id.sch.elib.util.BaseServiceInterface;
import id.sch.elib.util.GrailsRestClient;
import java.lang.reflect.Type;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object save(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object update(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object delete(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object search(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
