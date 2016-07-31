/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.controller;

import id.sch.elib.model.RakBuku;
import id.sch.elib.service.RakBukuService;
import id.sch.elib.util.BaseBeanInterface;
import id.sch.elib.util.DataLibrary;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rizky.aditya
 */
public class RakBukuController implements BaseBeanInterface {

    private final RakBukuService rakBukuService = new RakBukuService();
    private ArrayList<RakBuku> listRakBuku = new ArrayList<RakBuku>();
    private RakBuku rakBuku;
    private boolean editMode;
    Timestamp now = new Timestamp(new Date().getTime());

    //GETTER SETTER
    public List<RakBuku> getListRakBuku() {
        return listRakBuku;
    }

    public void setListRakBuku(ArrayList<RakBuku> listRakBuku) {
        this.listRakBuku = listRakBuku;
    }

    public RakBuku getRakBuku() {
        return rakBuku;
    }

    public void setRakBuku(RakBuku rakBuku) {
        this.rakBuku = rakBuku;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
    //END OF GETTER SETTER

    @Override
    public ArrayList<RakBuku> search(Object completeList, String param, String column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<RakBuku> fetchData(Object target, boolean init) {
        target = DataLibrary.getInstance().getMasterListRakBuku(init);
        ArrayList<RakBuku> temp=(ArrayList<RakBuku>)target;
        for (int i = 0; i < temp.size(); i++) {
            //hapus data yang tidak aktif dari list
            if (!temp.get(i).isActive()) {
                temp.remove(temp.get(i));
                i--;
            }
        }
        return temp;
    }

    @Override
    public String save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
