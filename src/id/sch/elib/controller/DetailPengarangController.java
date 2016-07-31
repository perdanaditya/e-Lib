/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.controller;

import id.sch.elib.model.DetailPengarang;
import id.sch.elib.service.DetailPengarangService;
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
public class DetailPengarangController implements BaseBeanInterface {

    private final DetailPengarangService detailPengarangService = new DetailPengarangService();
    private ArrayList<DetailPengarang> detailPengarangList;
    private DetailPengarang detaiPengarangModel;
    private boolean editMode;
    Timestamp now = new Timestamp(new Date().getTime());

    public ArrayList<DetailPengarang> getDetailPengarangList() {
        return detailPengarangList;
    }

    public void setDetailPengarangList(ArrayList<DetailPengarang> detailPengarangList) {
        this.detailPengarangList = detailPengarangList;
    }

    public DetailPengarang getDetaiPengarangModel() {
        return detaiPengarangModel;
    }

    public void setDetaiPengarangModel(DetailPengarang detaiPengarangModel) {
        this.detaiPengarangModel = detaiPengarangModel;
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
    public ArrayList<DetailPengarang> search(Object completeList, String param, String column) {
        ArrayList<DetailPengarang> temp = new ArrayList<DetailPengarang>();
        ArrayList<DetailPengarang> tempComplete = (ArrayList<DetailPengarang>) completeList;
        for (int i = 0; i < tempComplete.size(); i++) {
            if (column.equalsIgnoreCase("Nama Pengarang")) {
                if (tempComplete.get(i).getPengarang().getNamaPengarang().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Judul Buku")) {
                if (tempComplete.get(i).getBuku().getJudul().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("ISBN")) {
                if (tempComplete.get(i).getBuku().getIsbn().toLowerCase().contains(param.toLowerCase())) {
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
            detaiPengarangModel.setInputTime(now);
            message = (Message) detailPengarangService.save(detaiPengarangModel);
        } else {
            detaiPengarangModel.setInputTime(now);
            message = (Message) detailPengarangService.update(detaiPengarangModel);
            editMode = false;
        }
        return message.getMessage();
    }

    @Override
    public ArrayList<DetailPengarang> fetchData(Object target, boolean init) {
        target = DataLibrary.getInstance().getMasterDetailPengarang(init);
        ArrayList<DetailPengarang> detailPengarangs = (ArrayList<DetailPengarang>) target;
        for (int i = 0; i < detailPengarangs.size(); i++) {
            //hapus data yang tidak aktif dari list
            if (!detailPengarangs.get(i).getActive()) {
                detailPengarangs.remove(detailPengarangs.get(i));
                i--;
            }
        }
        return detailPengarangs;
    }
}
