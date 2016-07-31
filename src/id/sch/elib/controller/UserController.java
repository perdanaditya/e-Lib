/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.controller;

import id.sch.elib.model.User;
import id.sch.elib.service.UserService;
import id.sch.elib.util.BaseBeanInterface;
import id.sch.elib.util.DataLibrary;
import id.sch.elib.util.Message;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author rizky.aditya
 */
public class UserController implements BaseBeanInterface {

    private User user;
    private boolean editMode;
    private UserService userService = new UserService();
    private Timestamp now = new Timestamp(new Date().getTime());

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getNow() {
        //kalo time nya ga nge-reset, coba uncommand ini
        now = new Timestamp(new Date().getTime());
        return now;
    }

    public void setNow(Timestamp now) {
        this.now = now;
    }

    @Override
    public ArrayList<User> fetchData(Object target, boolean init) {
        target = DataLibrary.getInstance().getMasterListUserDetails(init);
        ArrayList<User> userTemp = (ArrayList<User>) target;
        for (int i = 0; i < userTemp.size(); i++) {
            //hapus data yang tidak aktif dari list
            if (!userTemp.get(i).getActive()) {
                System.out.println("inside ");
                userTemp.remove(userTemp.get(i));
                i--;
            }
        }
        return userTemp;
    }

    @Override
    public ArrayList<User> search(Object completeList, String param, String column) {
        ArrayList<User> temp = new ArrayList<User>();
        ArrayList<User> tempComplete = (ArrayList<User>) completeList;
        for (int i = 0; i < tempComplete.size(); i++) {
            if (column.equalsIgnoreCase("No. Induk")) {
                if (tempComplete.get(i).getPengguna().getNoInduk().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Nama")) {
                if (tempComplete.get(i).getPengguna().getNama().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Jabatan")) {
                if (tempComplete.get(i).getPengguna().getJabatan().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Jenis Kelamin")) {
                if (tempComplete.get(i).getPengguna().getJenisKelamin().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("E-mail")) {
                if (tempComplete.get(i).getEmail().toLowerCase().contains(param.toLowerCase())) {
                    temp.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Username")) {
                if (tempComplete.get(i).getUsername().toLowerCase().contains(param.toLowerCase())) {
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
            user.getPengguna().setInputTime(now);
            user.setInputTime(now);
            message = (Message) userService.save(user);
        } else {
            user.getPengguna().setInputTime(now);
            user.setInputTime(now);
            File file = new File("img.jpg");
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("FILE DELETED");
                } else {
                    System.out.println("CAN NOT DELETE SPECIFIED FILE");
                }
            }
            message = (Message) userService.update(user);
            editMode = false;
        }
        return message.getMessage();
    }

    public boolean login(String username, String password) {
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = (User) userService.login(user);
        if (user != null && user.getUserRole().getRole().getId() == 1) {
            DataLibrary.getInstance().setUser(user);
            //sementara di hard-coded, karena yang bisa masuk ke desktop client cuma admin
            //kalo nanti ada user dengan role yang bisa masuk selain admin, bisa ditambahin handlingnya di backend.
//            DataLibrary.getInstance().setRole("Administrator");//#HARDCODED
            user = new User();
            return true;
        } else {
            return false;
        }
    }

}
