/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.controller;

import id.sch.elib.model.UserRole;
import id.sch.elib.service.UserRoleService;
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
public class UserRoleController implements BaseBeanInterface {

    private final UserRoleService userRoleService = new UserRoleService();
    private ArrayList<UserRole> userRoleList;
    private UserRole userRole;
    private boolean editMode;
    Timestamp now = new Timestamp(new Date().getTime());

    @Override
    public ArrayList<UserRole> search(Object completeList, String param, String column) {
        ArrayList<UserRole> temp = new ArrayList<UserRole>();
        ArrayList<UserRole> tempComplete = (ArrayList<UserRole>) completeList;
        for (int i = 0; i < tempComplete.size(); i++) {
            if (column.equalsIgnoreCase("Username")) {
                if (tempComplete.get(i).getUser().getUsername().toLowerCase().contains(param.toLowerCase())) {
                    userRoleList.add(tempComplete.get(i));
                }
            } else if (column.equalsIgnoreCase("Hak Akses")) {
                if (tempComplete.get(i).getRole().getRoleName().toLowerCase().contains(param.toLowerCase())) {
                    userRoleList.add(tempComplete.get(i));
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
        userRole.setInputTime(now);
        if (!editMode) {
            message = (Message) userRoleService.save(userRole);
        } else {
            message = (Message) userRoleService.update(userRole);
            editMode = false;
        }
        return message.getMessage();
    }

    @Override
    public ArrayList<UserRole> fetchData(Object target, boolean init) {
        target = DataLibrary.getInstance().getMasterListUserRole(init);
        ArrayList<UserRole> temp = (ArrayList<UserRole>) target;
        for (int i = 0; i < temp.size(); i++) {
            //hapus data yang tidak aktif dari list
            if (!temp.get(i).isActive()) {
                temp.remove(temp.get(i));
                i--;
            }
        }
        return temp;
    }

    public ArrayList<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(ArrayList<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
}
