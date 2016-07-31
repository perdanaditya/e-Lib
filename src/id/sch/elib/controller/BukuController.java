/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.controller;

import id.sch.elib.model.Buku;
import id.sch.elib.model.Penerbit;
import id.sch.elib.model.RakBuku;
import id.sch.elib.service.BukuService;
import id.sch.elib.service.PenerbitService;
import id.sch.elib.service.RakBukuService;
import id.sch.elib.util.BaseBeanInterface;
import id.sch.elib.util.DataLibrary;
import id.sch.elib.util.Message;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author rizky.aditya
 */
public class BukuController implements BaseBeanInterface {

    private Buku bukuModel;

    private BukuService bukuService = new BukuService();
    private PenerbitService penerbitService = new PenerbitService();
    private RakBukuService rakBukuService = new RakBukuService();

    private ArrayList<Penerbit> penerbitList;
    private ArrayList<RakBuku> rakBukuList;

    private boolean editMode;

    Timestamp now = new Timestamp(new Date().getTime());

    //GETTER SETTER
    public BukuController() {
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public Buku getBukuModel() {
        return bukuModel;
    }

    public void setBukuModel(Buku bukuModel) {
        this.bukuModel = bukuModel;
    }
    //ENDD OF GETTER SETTER

    @Override
    public ArrayList<Buku> search(Object completeList, String param, String column) {
        ArrayList<Buku> temp = new ArrayList<Buku>();
        ArrayList<Buku> tempComplete = (ArrayList<Buku>) completeList;
        for (int i = 0; i < tempComplete.size(); i++) {
                if(column.equalsIgnoreCase("ISBN")){
                    if (tempComplete.get(i).getIsbn().toLowerCase().contains(param.toLowerCase())) {
                            temp.add(tempComplete.get(i));
                        }
                }else if (column.equalsIgnoreCase("Judul")){
                    if (tempComplete.get(i).getJudul().toLowerCase().contains(param.toLowerCase())) {
                            temp.add(tempComplete.get(i));
                        }
                }else if (column.equalsIgnoreCase("Kategori")){
                    if (tempComplete.get(i).getRakBuku().getNamaJenis().toLowerCase().contains(param.toLowerCase())) {
                            temp.add(tempComplete.get(i));
                        }
                }else if (column.equalsIgnoreCase("Penerbit")){
                    if (tempComplete.get(i).getPenerbit().getNamaPenerbit().toLowerCase().contains(param.toLowerCase())) {
                            temp.add(tempComplete.get(i));
                        }
                }else if (column.equalsIgnoreCase("Tahun Terbit")){
                    if (tempComplete.get(i).getTahunTerbit().toLowerCase().contains(param.toLowerCase())) {
                            temp.add(tempComplete.get(i));
                        }
                }else if (column.equalsIgnoreCase("Strock")){
                    if (tempComplete.get(i).getStock().toString().toLowerCase().contains(param.toLowerCase())) {
                            temp.add(tempComplete.get(i));
                        }
                }else{
                        JOptionPane.showMessageDialog(null, "Column name or search field cannot be empty", "SEARCH-ERROR", 0);
                }
            }
        return temp;
    }

    @Override
    public String save() {
        Message message;
        if (!editMode) {
            bukuModel.setInputTime(now);
            message = (Message) bukuService.save(bukuModel);
        } else {
            bukuModel.setInputTime(now);
            message = (Message) bukuService.update(bukuModel);
            editMode = false;
            File file =new File("img.jpg");
            if(file.exists()){
                if(file.delete()){
                    System.out.println("FILE DELETED");
                }else{
                    System.out.println("CAN NOT DELETE SPECIFIED FILE");
                }
            }
        }
        return message.getMessage();
    }

    @Override
    public ArrayList<Buku> fetchData(Object target, boolean init) {
        target = DataLibrary.getInstance().getMasterListBuku(init);
        ArrayList<Buku> temp = (ArrayList<Buku>) target;
        for (int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).getActive()) {
                temp.remove(temp.get(i));
                i--;
            }
        }
        return temp;
    }

    //CUSTOM METHOD

    public ArrayList<RakBuku> populateRakBukuList(boolean init) {
        rakBukuList = DataLibrary.getInstance().getMasterListRakBuku(init);
        for (int i = 0; i < rakBukuList.size(); i++) {
            if (!rakBukuList.get(i).isActive()) {
                rakBukuList.remove(rakBukuList.get(i));
                i--;
            }
        }
        return rakBukuList;
    }

}
