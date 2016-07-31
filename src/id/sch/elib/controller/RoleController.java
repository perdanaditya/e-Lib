/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.controller;

import id.sch.elib.model.Role;
import id.sch.elib.service.RoleService;
import id.sch.elib.util.BaseBeanInterface;
import id.sch.elib.util.DataLibrary;
import id.sch.elib.util.Message;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rizky.aditya
 */
public class RoleController implements BaseBeanInterface {

    private final RoleService roleService = new RoleService();
    private Role roleModel;
    private boolean editMode;
    Timestamp now = new Timestamp(new Date().getTime());

    @Override
    public ArrayList<Role> search(Object completeList, String param, String column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String save() {
        Message message;
        if (!editMode) {
            roleModel.setInputTime(now);
            message = (Message) roleService.save(roleModel);
        } else {
            roleModel.setInputTime(now);
            message = (Message) roleService.update(roleModel);
            editMode = false;
        }
        return message.getMessage();
    }

    @Override
    public ArrayList<Role> fetchData(Object target, boolean init) {
        target = DataLibrary.getInstance().getMasterListRole(init);
        ArrayList<Role> temp = (ArrayList<Role>) target;
        for (int i = 0; i < temp.size(); i++) {
            //hapus data yang tidak aktif dari list
            if (!temp.get(i).isActive()) {
                temp.remove(temp.get(i));
                i--;
            }
        }
        return temp;
    }

    public Role getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(Role roleModel) {
        this.roleModel = roleModel;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

}
