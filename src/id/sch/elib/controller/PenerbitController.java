/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.controller;

import id.sch.elib.model.Penerbit;
import id.sch.elib.service.PenerbitService;
import id.sch.elib.util.BaseBeanInterface;
import id.sch.elib.util.DataLibrary;
import id.sch.elib.util.Message;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author rizky.aditya
 */
public class PenerbitController implements BaseBeanInterface, Serializable {

    private final PenerbitService penerbitService = new PenerbitService();
    private ArrayList<Penerbit> penerbitList;
    private Penerbit penerbitModel;
    private boolean editMode;
    Timestamp now = new Timestamp(new Date().getTime());

    public PenerbitController() {
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public ArrayList<Penerbit> getPenerbitList() {
        return penerbitList;
    }

    public void setPenerbitList(ArrayList<Penerbit> penerbitList) {
        this.penerbitList = penerbitList;
    }

    public Penerbit getPenerbitModel() {
        return penerbitModel;
    }

    public void setPenerbitModel(Penerbit penerbitModel) {
        this.penerbitModel = penerbitModel;
    }

    @Override
    public ArrayList<Penerbit> search(Object completeList, String param, String column) {
        ArrayList<Penerbit> temp = new ArrayList<Penerbit>();
        ArrayList<Penerbit> tempComplete = (ArrayList<Penerbit>) completeList;

        for (int i = 0; i < tempComplete.size(); i++) {
            if (column.equalsIgnoreCase("Nama")) {
                if (tempComplete.get(i).getNamaPenerbit().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Alamat")) {
                if (tempComplete.get(i).getAlamatPenerbit().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Kota")) {
                if (tempComplete.get(i).getKotaPenerbit().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Telpon")) {
                if (tempComplete.get(i).getTelponPenerbit().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Column name or search field cannot be empty", "SEARCH-ERROR", 0);
            }
        }
        return temp;
    }

    @Override
    public String save() {
        Message message;
        if (!editMode) {
            penerbitModel.setInputTime(now);
            message = (Message) penerbitService.save(penerbitModel);
        } else {
            penerbitModel.setInputTime(now);
            message = (Message) penerbitService.update(penerbitModel);
            editMode = false;
        }
        return message.getMessage();
    }

    @Override
    public ArrayList<Penerbit> fetchData(Object target, boolean init) {
        target = DataLibrary.getInstance().getMasterListPenerbit(init);
        ArrayList<Penerbit> penerbitTemp = (ArrayList<Penerbit>) target;
        for (int i = 0; i < penerbitTemp.size(); i++) {
            //hapus data yang tidak aktif dari list
            if (!penerbitTemp.get(i).isActive()) {
                penerbitTemp.remove(penerbitTemp.get(i));
                i--;
            }
        }
        return penerbitTemp;
    }
}
