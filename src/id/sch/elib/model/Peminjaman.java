/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rizky.aditya
 */
public class Peminjaman implements Serializable{
    
    private long id;
    private String noPeminjaman;
    private User user;
    private Date tanggalPinjam;
    private Denda denda;
    private BigDecimal totalDenda;
    ArrayList<DetailPeminjaman> detailPeminjaman;
    private boolean active;
    private String userInput;
    private Timestamp inputTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoPeminjaman() {
        return noPeminjaman;
    }

    public void setNoPeminjaman(String noPeminjaman) {
        this.noPeminjaman = noPeminjaman;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public Denda getDenda() {
        return denda;
    }

    public void setDenda(Denda denda) {
        this.denda = denda;
    }

    public BigDecimal getTotalDenda() {
        return totalDenda;
    }

    public void setTotalDenda(BigDecimal totalDenda) {
        this.totalDenda = totalDenda;
    }

    public ArrayList<DetailPeminjaman> getDetailPeminjaman() {
        return detailPeminjaman;
    }

    public void setDetailPeminjaman(ArrayList<DetailPeminjaman> detailPeminjaman) {
        this.detailPeminjaman = detailPeminjaman;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public Timestamp getInputTime() {
        return inputTime;
    }

    public void setInputTime(Timestamp inputTime) {
        this.inputTime = inputTime;
    }

}
