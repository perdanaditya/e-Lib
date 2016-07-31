/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.controller;

import id.sch.elib.model.DetailPeminjaman;
import id.sch.elib.model.Peminjaman;
import id.sch.elib.service.PeminjamanService;
import id.sch.elib.util.BaseBeanInterface;
import id.sch.elib.util.DataLibrary;
import id.sch.elib.util.Message;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author rizky.aditya
 */
public class PeminjamanController implements BaseBeanInterface {

    private final PeminjamanService peminjamanService = new PeminjamanService();
    private ArrayList<Peminjaman> peminjamanList;
    private Peminjaman peminjamanModel;
    private boolean editMode;
    Timestamp now = new Timestamp(new Date().getTime());
    SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");

    public ArrayList<Peminjaman> getPeminjamanList() {
        return peminjamanList;
    }

    public void setPeminjamanList(ArrayList<Peminjaman> peminjamanList) {
        this.peminjamanList = peminjamanList;
    }

    public Peminjaman getPeminjamanModel() {
        return peminjamanModel;
    }

    public void setPeminjamanModel(Peminjaman peminjamanModel) {
        this.peminjamanModel = peminjamanModel;
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
    public ArrayList<Peminjaman> search(Object completeList, String param, String column) {
        ArrayList<Peminjaman> temp = new ArrayList<Peminjaman>();
        ArrayList<Peminjaman> tempComplete = (ArrayList<Peminjaman>) completeList;
        for (int i = 0; i < tempComplete.size(); i++) {
            if (column.equalsIgnoreCase("No. Peminjaman")) {
                if (tempComplete.get(i).getNoPeminjaman().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("No. Induk")) {
                if (tempComplete.get(i).getUser().getPengguna().getNoInduk().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Nama")) {
                if (tempComplete.get(i).getUser().getPengguna().getNama().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Jabatan")) {
                if (tempComplete.get(i).getUser().getPengguna().getJabatan().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Tanggal Pinjam")) {
                if (format.format(tempComplete.get(i).getTanggalPinjam()).toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Status Peminjaman")) {
                if ((tempComplete.get(i).isActive() ? "Belum Kembali" : "Sudah Kembali").toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Last Edited")) {
                if (tempComplete.get(i).getUserInput().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Column name or search field cannot be empty", "SEARCH-ERROR", 0);
            }
        }
        return temp;
    }

    @Override
    public ArrayList<Peminjaman> fetchData(Object target, boolean init) {
        target = DataLibrary.getInstance().getMasterPeminjamanList(init);
        ArrayList<Peminjaman> temp = (ArrayList<Peminjaman>) target;
        return temp;
    }

    @Override
    public String save() {
        Message message;
        peminjamanModel.setUserInput(DataLibrary.getInstance().getUser().getUsername());
        if (!editMode) {
            message = (Message) peminjamanService.save(peminjamanModel);
        } else {
            //hitung denda masing masing buku
            dendaHandling();

            BigDecimal totalDenda = BigDecimal.ZERO;
            boolean check = true;
            boolean checkKembali = false;
            String detailMessage = "";
            BigDecimal dendaToday = BigDecimal.ZERO;
            for (DetailPeminjaman detailPeminjaman : peminjamanModel.getDetailPeminjaman()) {
                //re-compute total denda
                if (detailPeminjaman.getDenda() != null) {
                    totalDenda.add(detailPeminjaman.getDenda());
                }
                //cek status pengembalian buku
                if (detailPeminjaman.getTanggalPengembalian() == null) {
                    check = false;
                }
                //cek pengembalian hari ini
                if (detailPeminjaman.isKembali()) {
                    if (detailPeminjaman.getDenda() != null) {
                        detailMessage = detailMessage + detailPeminjaman.getBuku().getJudul() + "\nDenda" + detailPeminjaman.getDenda() + "\n\n";
                        dendaToday.add(detailPeminjaman.getDenda());
                    }
                    checkKembali = true;
                }
            }
            peminjamanModel.setTotalDenda(totalDenda);
            //kalo ada pengembalian yang hari ini
            if (checkKembali && (dendaToday != null && dendaToday != BigDecimal.ZERO)) {
                detailMessage = detailMessage + "Denda keseluruhan: " + peminjamanModel.getTotalDenda() + "\n"
                        + "Yang harus dibayar: " + dendaToday;
                JOptionPane.showMessageDialog(null, detailMessage, "Compensation Detail", JOptionPane.WARNING_MESSAGE);
            }
            //kalo udah dikembalikan semua, berarti transaksinya close (active==false)
            if (check) {
                peminjamanModel.setActive(false);
            }
            message = (Message) peminjamanService.update(peminjamanModel);
            editMode = false;
        }
        return message.getMessage();
    }

    private void dendaHandling() {
//        BigDecimal dendaTemp = BigDecimal.ZERO;
        for (DetailPeminjaman detailPeminjaman : peminjamanModel.getDetailPeminjaman()) {
            if (detailPeminjaman.isKembali()) {
                detailPeminjaman.setTanggalPengembalian(getNow());
                Calendar tglPinjam = Calendar.getInstance();
                Calendar thisTime = Calendar.getInstance();
                tglPinjam.setTime(peminjamanModel.getTanggalPinjam());
                thisTime.setTime(now);
                int day = tglPinjam.compareTo(thisTime);
                System.out.println("DIPINJAM " + day + " HARI");
                if (day > detailPeminjaman.getBuku().getMasaPinjam()) {
                    int telat = (day - detailPeminjaman.getBuku().getMasaPinjam());
                    System.out.println("TELAT " + telat + " HARI");
                    BigDecimal denda = new BigDecimal(telat * peminjamanModel.getDenda().getNominal());
                    System.out.println("DENDA: " + denda);
                    if (detailPeminjaman.getDenda() != null) {
//                        dendaTemp = detailPeminjaman.getDenda();
                        detailPeminjaman.setDenda(denda);
                    }
                }
            }
        }
    }
}
