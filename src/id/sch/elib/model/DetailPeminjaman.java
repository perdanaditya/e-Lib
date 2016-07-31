/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author rizky.aditya
 */
public class DetailPeminjaman {
    
    private long id;
    private Peminjaman peminjaman;
    private Buku buku;
    private Integer masaPinjam;
    private Date tanggalPengembalian;
    private BigDecimal denda;
    private boolean perpanjang;
    private Boolean active;
    private String userInput;
    private Timestamp inputTime;
    
    private boolean kembali=false;//buat penanda aja

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public Integer getMasaPinjam() {
        return masaPinjam;
    }

    public void setMasaPinjam(Integer masaPinjam) {
        this.masaPinjam = masaPinjam;
    }

    public Date getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(Date tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }

    public boolean isPerpanjang() {
        return perpanjang;
    }

    public void setPerpanjang(boolean perpanjang) {
        this.perpanjang = perpanjang;
    }
    
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
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

    public boolean isKembali() {
        return kembali;
    }

    public void setKembali(boolean kembali) {
        this.kembali = kembali;
    }
    
}
