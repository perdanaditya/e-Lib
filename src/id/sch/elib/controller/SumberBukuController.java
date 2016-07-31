/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.controller;

import id.sch.elib.model.SumberBuku;
import id.sch.elib.service.SumberBukuService;
import id.sch.elib.util.BaseBeanInterface;
import id.sch.elib.util.DataLibrary;
import id.sch.elib.util.Message;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author rizky.aditya
 */
public class SumberBukuController implements BaseBeanInterface {

    private final SumberBukuService sumberBukuService = new SumberBukuService();
    private ArrayList<SumberBuku> sumberBukuList;
    private SumberBuku sumberBukuModel;
    private boolean editMode;
    Timestamp now = new Timestamp(new Date().getTime());

    public ArrayList<SumberBuku> getSumberBukuList() {
        return sumberBukuList;
    }

    public void setSumberBukuList(ArrayList<SumberBuku> sumberBukuList) {
        this.sumberBukuList = sumberBukuList;
    }

    public SumberBuku getSumberBukuModel() {
        return sumberBukuModel;
    }

    public void setSumberBukuModel(SumberBuku sumberBukuModel) {
        this.sumberBukuModel = sumberBukuModel;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public Timestamp getNow() {
        return now;
    }

    public void setNow(Timestamp now) {
        this.now = now;
    }

    @Override
    public ArrayList<SumberBuku> search(Object completeList, String param, String column) {
        ArrayList<SumberBuku> temp = new ArrayList<SumberBuku>();
        ArrayList<SumberBuku> tempComplete = (ArrayList<SumberBuku>) completeList;
        for (int i = 0; i < tempComplete.size(); i++) {
            if (column.equalsIgnoreCase("No. Induk")) {
                if (tempComplete.get(i).getPengguna().getNoInduk().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Naman Donatur")) {
                if (tempComplete.get(i).getPengguna() != null) {
                    if (tempComplete.get(i).getPengguna().getNama().toLowerCase().contains(param.toLowerCase())) {
                        temp.add(tempComplete.get(i));
                    }
                } else {
                    if (tempComplete.get(i).getNamaPemberi().toLowerCase().contains(param.toLowerCase())) {
                        temp.add(tempComplete.get(i));
                    }
                }
            } else if (column.equalsIgnoreCase("Judul Buku")) {
                if (tempComplete.get(i).getBuku().getJudul().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Jumlah")) {
                if (tempComplete.get(i).getJumlah().toString().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Column name or search field cannot be empty", "SEARCH-ERROR", 0);
            }
        }
        return temp;
    }

    @Override
    public ArrayList<SumberBuku> fetchData(Object target, boolean init) {
        target = DataLibrary.getInstance().getMasterSumberBukuList(init);
        ArrayList<SumberBuku> sumberBukus = (ArrayList<SumberBuku>) target;
        for (int i = 0; i < sumberBukus.size(); i++) {
            //hapus data yang tidak aktif dari list
            if (!sumberBukus.get(i).getActive()) {
                sumberBukus.remove(sumberBukus.get(i));
                i--;
            }
        }
        return sumberBukus;
    }

    @Override
    public String save() {
        sumberBukuModel.setInputTime(now);
        if (!editMode) {
            return ((Message) sumberBukuService.save(sumberBukuModel)).getMessage();
        } else {
            editMode = false;
            return ((Message) sumberBukuService.update(sumberBukuModel)).getMessage();
        }
    }
}
