/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.util;

import id.sch.elib.model.Buku;
import id.sch.elib.model.Denda;
import id.sch.elib.model.DetailPengarang;
import id.sch.elib.model.Peminjaman;
import id.sch.elib.model.Penerbit;
import id.sch.elib.model.Pengarang;
import id.sch.elib.model.Pengguna;
import id.sch.elib.model.RakBuku;
import id.sch.elib.model.Role;
import id.sch.elib.model.SumberBuku;
import id.sch.elib.model.User;
import id.sch.elib.model.UserRole;
import id.sch.elib.service.BukuService;
import id.sch.elib.service.DendaService;
import id.sch.elib.service.DetailPengarangService;
import id.sch.elib.service.PeminjamanService;
import id.sch.elib.service.PenerbitService;
import id.sch.elib.service.PengarangService;
import id.sch.elib.service.PenggunaService;
import id.sch.elib.service.RakBukuService;
import id.sch.elib.service.RoleService;
import id.sch.elib.service.SumberBukuService;
import id.sch.elib.service.UserRoleService;
import id.sch.elib.service.UserService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rizky.aditya
 */
public class DataLibrary {

//    private static String username;
//    private static String role;\
    private User user;
    private SimpleDateFormat simpleDateFormat;

    private static final PenerbitService penerbitService = new PenerbitService();
    private static final RakBukuService rakBukuService = new RakBukuService();
    private static final BukuService bukuService = new BukuService();
    private static final UserService userService = new UserService();
    private static final RoleService roleService = new RoleService();
    private static final UserRoleService userRoleService = new UserRoleService();
    private static final DendaService dendaService = new DendaService();
    private static final DetailPengarangService detailPengarangService = new DetailPengarangService();
    private static final PengarangService pengarangService = new PengarangService();
    private static final SumberBukuService sumberBukuService = new SumberBukuService();
    private static final PenggunaService penggunaService = new PenggunaService();
    private static final PeminjamanService peminjamanService = new PeminjamanService();

    private ArrayList<Penerbit> masterListPenerbit;
    private ArrayList<RakBuku> masterListRakBuku;
    private ArrayList<Buku> masterListBuku;
    private ArrayList<User> masterListUser;
    private ArrayList<Role> masterListRole;
    private ArrayList<UserRole> masterListUserRole;
    private ArrayList<Denda> masterDendaList;
    private ArrayList<DetailPengarang> masterDetailPengarang;
    private ArrayList<Pengarang> masterListPengarang;
    private ArrayList<SumberBuku> masterSumberBukuList;
    private ArrayList<Pengguna> masterPenggunaList;
    private ArrayList<Peminjaman> masterPeminjamanList;

    private static DataLibrary dataLibrary;

    private DataLibrary() {
    }

    public static DataLibrary getInstance() {
        if (dataLibrary == null) {
            dataLibrary = new DataLibrary();
        }
        return dataLibrary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Penerbit> getMasterListPenerbit(boolean init) {
        if (masterListPenerbit == null || init) {
            masterListPenerbit = (ArrayList<Penerbit>) penerbitService.listAll();
        }
        return masterListPenerbit;
    }

    public ArrayList<RakBuku> getMasterListRakBuku(boolean init) {
        if (masterListRakBuku == null || init) {
            masterListRakBuku = (ArrayList<RakBuku>) rakBukuService.listAll();
        }
        return masterListRakBuku;
    }

    public ArrayList<Buku> getMasterListBuku(boolean init) {
        if (masterListBuku == null || init) {
            masterListBuku = (ArrayList<Buku>) bukuService.listAll();
        }
        return masterListBuku;
    }

    public ArrayList<User> getMasterListUser(boolean init) {
        if (masterListUser == null || init) {
            masterListUser = (ArrayList<User>) userService.listAll();
        }
        return masterListUser;
    }

    public ArrayList<User> getMasterListUserDetails(boolean init) {
        if (masterListUser == null || init) {
            masterListUser = (ArrayList<User>) userService.ListDetails();
        }
        return masterListUser;
    }

    public ArrayList<Role> getMasterListRole(boolean init) {
        if (masterListRole == null || init) {
            masterListRole = (ArrayList<Role>) roleService.listAll();
        }
        return masterListRole;
    }

    public ArrayList<UserRole> getMasterListUserRole(boolean init) {
        if (masterListUserRole == null || init) {
            masterListUserRole = (ArrayList<UserRole>) userRoleService.listAll();
        }
        return masterListUserRole;
    }

    public ArrayList<Denda> getMasterDendaList(boolean init) {
        if (masterDendaList == null || init) {
            masterDendaList = (ArrayList<Denda>) dendaService.listAll();
        }
        return masterDendaList;
    }

    public ArrayList<DetailPengarang> getMasterDetailPengarang(boolean init) {
        if (masterDetailPengarang == null || init) {
            masterDetailPengarang = (ArrayList<DetailPengarang>) detailPengarangService.listAll();
        }
        return masterDetailPengarang;
    }

    public void setMasterDetailPengarang(ArrayList<DetailPengarang> masterDetailPengarang) {
        this.masterDetailPengarang = masterDetailPengarang;
    }

    public ArrayList<Pengarang> getMasterListPengarang(boolean init) {
        if (masterListPengarang == null || init) {
            masterListPengarang = (ArrayList<Pengarang>) pengarangService.listAll();
        }
        return masterListPengarang;
    }

    public void setMasterListPengarang(ArrayList<Pengarang> masterListPengarang) {
        this.masterListPengarang = masterListPengarang;
    }

    public ArrayList<SumberBuku> getMasterSumberBukuList(boolean init) {
        if (masterSumberBukuList == null || init) {
            masterSumberBukuList = (ArrayList<SumberBuku>) sumberBukuService.listAll();
        }
        return masterSumberBukuList;
    }

    public void setMasterSumberBukuList(ArrayList<SumberBuku> masterSumberBukuList) {
        this.masterSumberBukuList = masterSumberBukuList;
    }

    public ArrayList<Pengguna> getMasterPenggunaList(boolean init) {
        if (masterPenggunaList == null || init) {
            masterPenggunaList = (ArrayList<Pengguna>) penggunaService.listAll();
        }
        return masterPenggunaList;
    }

    public void setMasterPenggunaList(ArrayList<Pengguna> masterPenggunaList) {
        this.masterPenggunaList = masterPenggunaList;
    }

    public ArrayList<Peminjaman> getMasterPeminjamanList(boolean init) {
        if (masterPeminjamanList == null || init) {
            masterPeminjamanList = (ArrayList<Peminjaman>) peminjamanService.listAll();
        }
        return masterPeminjamanList;
    }

    public void setMasterPeminjamanList(ArrayList<Peminjaman> masterPeminjamanList) {
        this.masterPeminjamanList = masterPeminjamanList;
    }

    public String getSizeScale(int scale) {
        switch (scale) {
            case 1:
                return "KB";
            case 2:
                return "MB";
            case 3:
                return "GB";
            case 5:
                return "TB";
            default:
                return "Bytes";
        }
    }
    
    public String getCurrentTimeByPattern(String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public String getCurrentTimeByPattern(Date date, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

}
