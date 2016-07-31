/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.controller;

import id.sch.elib.model.Denda;
import id.sch.elib.service.DendaService;
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
public class DendaController implements BaseBeanInterface {

    private final DendaService dendaService = new DendaService();
    private ArrayList<Denda> dendaList;
    private Denda dendaModel;
    private boolean editMode;
    Timestamp now = new Timestamp(new Date().getTime());

    public ArrayList<Denda> getDendaList() {
        return dendaList;
    }

    public void setDendaList(ArrayList<Denda> dendaList) {
        this.dendaList = dendaList;
    }

    public Denda getDendaModel() {
        return dendaModel;
    }

    public void setDendaModel(Denda dendaModel) {
        this.dendaModel = dendaModel;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    @Override
    public String save() {
        Message message;
        if (!editMode) {
            dendaModel.setInputTime(now);
            message = (Message) dendaService.save(dendaModel);
        } else {
            dendaModel.setInputTime(now);
            message = (Message) dendaService.update(dendaModel);
            editMode = false;
        }
        return message.getMessage();
    }

    @Override
    public ArrayList<Denda> fetchData(Object target, boolean init) {
        target = DataLibrary.getInstance().getMasterDendaList(init);
        ArrayList<Denda> dendaTemp = (ArrayList<Denda>) target;
        for (int i = 0; i < dendaTemp.size(); i++) {
            //hapus data yang tidak aktif dari list
            if (!dendaTemp.get(i).isActive()) {
                dendaTemp.remove(dendaTemp.get(i));
                i--;
            }
        }
        return dendaTemp;
    }

    @Override
    public ArrayList<Denda> search(Object completeList, String param, String column) {
        ArrayList<Denda> temp = new ArrayList<Denda>();
        ArrayList<Denda> tempComplete = (ArrayList<Denda>) completeList;

        for (int i = 0; i < tempComplete.size(); i++) {
            if (column.equalsIgnoreCase("Tanggal")) {
                if (tempComplete.get(i).getTanggal().toString().toLowerCase().contains(param.toLowerCase())) {
                    dendaList.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Nominal")) {
                if (String.valueOf(tempComplete.get(i).getNominal()).contains(param)) {
                    dendaList.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("User")) {
                if (tempComplete.get(i).getUserInput().toLowerCase().contains(param.toLowerCase())) {
                    dendaList.add(tempComplete.get(i));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Column name or search field cannot be empty", "SEARCH-ERROR", 0);
            }
        }
        return temp;
    }
}
