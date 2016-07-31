/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.view;

import id.sch.elib.controller.DendaController;
import id.sch.elib.controller.UserRoleController;
import id.sch.elib.model.Denda;
import id.sch.elib.model.Role;
import id.sch.elib.model.User;
import id.sch.elib.model.UserRole;
import id.sch.elib.util.DataLibrary;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rizky.aditya
 */
public class Pengaturan extends javax.swing.JInternalFrame {

    /**
     * Creates new form PengaturanView
     */
    //role tab
    ArrayList<String> userRoleColumnName;
    ArrayList<UserRole> userRoleList;
    ArrayList<UserRole> masterUserRoleList;
    ArrayList<Role> roleList;
    ArrayList<User> userList;
    private UserRole userRole;
    private DefaultTableModel tableModel;
    private UserRoleController userRoleController;
    private boolean editMode;
    //denda tab
    ArrayList<Denda> dendaList;
    ArrayList<Denda> masterDendaList;
    ArrayList<String> dendaColumnName;
    private Denda dendaModel;
    private DefaultTableModel dendaTableModel;
    private DendaController dendaController;
    private boolean dendaEditMode;

    public Pengaturan() {
        initComponents();
        //init role tab
        userRoleController = new UserRoleController();
        userRoleColumnName = new ArrayList<String>();
        reInitValiables();
        userRoleColumnName.add("No.");
        userRoleColumnName.add("Username");
        userRoleColumnName.add("Hak Akses");
        searchColumnCmbRole.removeAllItems();
        for (int i = 1; i < userRoleColumnName.size(); i++) {
            String ur = userRoleColumnName.get(i);
            searchColumnCmbRole.addItem(ur);
        }
        userList = DataLibrary.getInstance().getMasterListUserDetails(true);
        usernameCmb.removeAllItems();
        usernameCmb.addItem("--Username--");
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            usernameCmb.addItem(user.getUsername());
        }
        roleList = DataLibrary.getInstance().getMasterListRole(false);
        roleCmb.removeAllItems();
        roleCmb.addItem("--Role--");
        for (int i = 0; i < roleList.size(); i++) {
            Role role = roleList.get(i);
            roleCmb.addItem(role.getRoleName());
        }

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(userRoleColumnName.toArray());
        roleTable.setModel(tableModel);

        //denda tab
        dendaController = new DendaController();
        dendaColumnName = new ArrayList<String>();
        reInitDendaValiables();
        dendaColumnName.add("No.");
        dendaColumnName.add("Tanggal");
        dendaColumnName.add("Nominal");
        dendaColumnName.add("User");
        searchCol.removeAllItems();
        for (int i = 1; i < dendaColumnName.size(); i++) {
            searchCol.addItem(dendaColumnName.get(i));
        }
        dendaTableModel = new DefaultTableModel();
        dendaTableModel.setColumnIdentifiers(dendaColumnName.toArray());
        dendaTable.setModel(dendaTableModel);
    }

    public void setTableValue() {
        if (masterUserRoleList == null) {
            masterUserRoleList = userRoleController.fetchData(masterUserRoleList, false);
        }
        if (userRoleList == null) {
            userRoleList = masterUserRoleList;
        }
        tableModel = new DefaultTableModel(userRoleColumnName.toArray(), userRoleList.size());
        tableModel.setColumnIdentifiers(userRoleColumnName.toArray());
        roleTable.setModel(tableModel);
        for (int i = 0; i < userRoleList.size(); i++) {
            roleTable.setValueAt((i + 1), i, 0);
            roleTable.setValueAt(userRoleList.get(i).getUser().getUsername(), i, 1);
            roleTable.setValueAt(userRoleList.get(i).getRole().getRoleName(), i, 2);
        }
    }

    public void setTableValueDenda() {
        if (masterDendaList == null) {
            masterDendaList = dendaController.fetchData(masterDendaList, false);
        }
        if (dendaList == null) {
            dendaList = masterDendaList;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        dendaTableModel = new DefaultTableModel(dendaColumnName.toArray(), dendaList.size());
        dendaTableModel.setColumnIdentifiers(dendaColumnName.toArray());
        dendaTable.setModel(dendaTableModel);
        for (int i = 0; i < dendaList.size(); i++) {
            dendaTable.setValueAt((i + 1), i, 0);
            dendaTable.setValueAt(format.format(dendaList.get(i).getTanggal()), i, 1);
            dendaTable.setValueAt(dendaList.get(i).getNominal(), i, 2);
            dendaTable.setValueAt(dendaList.get(i).getUserInput(), i, 3);
        }
    }

    public void resetAllFieldInput() {
        usernameCmb.setSelectedIndex(0);
        roleCmb.setSelectedIndex(0);
        namaLabel.setText("");
        jabatanLabel.setText("");
        tempatLahirLabel.setText("");
        tglLahirLabel.setText("");
        kelaminLabel.setText("");
        usernameLabel.setText("");
        emailLabel.setText("");
        searchColumnCmbRole.setSelectedIndex(0);
        searchValueRole.setText("");
        usernameCmb.setEnabled(true);
        editBtnRole.setSelected(false);
        editBtnRole.setText("Edit");
    }

    public void resetAllFieldInputDenda() {
        tanggalCmb.setSelectedIndex(0);
        bulanCmb.setSelectedIndex(0);
        tahunCmb.setSelectedIndex(0);
        nominalTxt.setText("");
    }

    public void reInitValiables() {
        userRole = new UserRole();
        editMode = false;
        editBtnRole.setSelected(editMode);
        editBtnRole.setText("Edit");
    }

    public void reInitDendaValiables() {
        dendaModel = new Denda();
        dendaEditMode = false;
        editBtn.setSelected(dendaEditMode);
        editBtn.setText("Edit ");
    }

    public User getUserByUsername(String username) {
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public Role getRoleByRoleName(String role) {
        for (int i = 0; i < roleList.size(); i++) {
            Role roleObj = roleList.get(i);
            if (roleObj.getRoleName().equals(role)) {
                return roleObj;
            }
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        usernameCmb = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        roleCmb = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        roleTable = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        searchBtnRole = new javax.swing.JButton();
        searchValueRole = new javax.swing.JTextField();
        searchColumnCmbRole = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        editBtnRole = new javax.swing.JToggleButton();
        deleteBtnRole = new javax.swing.JButton();
        viewRoleTblBtn = new javax.swing.JToggleButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tempatLahirLabel = new javax.swing.JLabel();
        tglLahirLabel = new javax.swing.JLabel();
        kelaminLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        jabatanLabel = new javax.swing.JLabel();
        namaLabel = new javax.swing.JLabel();
        submitRole = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tanggalCmb = new javax.swing.JComboBox();
        bulanCmb = new javax.swing.JComboBox();
        tahunCmb = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        nominalTxt = new javax.swing.JTextField();
        submitBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dendaTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        searchCol = new javax.swing.JComboBox();
        searchVal = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JToggleButton();
        viewDendaTblBtn = new javax.swing.JToggleButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Pengaturan");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Role Assign"));

        jLabel4.setText("Username");

        usernameCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        usernameCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameCmbActionPerformed(evt);
            }
        });

        jLabel5.setText("Hak Akses");

        roleCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(6, 6, 6)
                .addComponent(usernameCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roleCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(usernameCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(roleCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Data List"));

        roleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        roleTable.setEnabled(false);
        jScrollPane2.setViewportView(roleTable);

        searchBtnRole.setText("Search");
        searchBtnRole.setEnabled(false);
        searchBtnRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnRoleActionPerformed(evt);
            }
        });

        searchValueRole.setEnabled(false);

        searchColumnCmbRole.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        searchColumnCmbRole.setEnabled(false);

        jLabel11.setText("Search By");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchColumnCmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchValueRole, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtnRole))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBtnRole)
                    .addComponent(searchValueRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchColumnCmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)))
        );

        editBtnRole.setText("Edit");
        editBtnRole.setEnabled(false);
        editBtnRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnRoleActionPerformed(evt);
            }
        });

        deleteBtnRole.setText("Delete");
        deleteBtnRole.setEnabled(false);
        deleteBtnRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnRoleActionPerformed(evt);
            }
        });

        viewRoleTblBtn.setText("View Table");
        viewRoleTblBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewRoleTblBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editBtnRole, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteBtnRole)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewRoleTblBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editBtnRole)
                    .addComponent(deleteBtnRole)
                    .addComponent(viewRoleTblBtn)))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Biodata Preview"));

        jLabel13.setText("E-mail");

        jLabel12.setText("Username");

        jLabel9.setText("Jenis Kelamin");

        jLabel8.setText("Tempat Lahir");

        jLabel7.setText("Jabatan");

        jLabel6.setText("Nama");

        jLabel10.setText("Tanggal Lahir");

        tempatLahirLabel.setText("jLabel11");

        tglLahirLabel.setText("jLabel11");

        kelaminLabel.setText("jLabel11");

        usernameLabel.setText("jLabel14");

        emailLabel.setText("jLabel15");

        jabatanLabel.setText("jLabel16");

        namaLabel.setText("jLabel17");

        submitRole.setText("Submit");
        submitRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitRoleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailLabel)
                            .addComponent(usernameLabel)
                            .addComponent(kelaminLabel)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namaLabel)
                            .addComponent(jabatanLabel)
                            .addComponent(tglLahirLabel)
                            .addComponent(tempatLahirLabel))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submitRole)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(namaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jabatanLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tempatLahirLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tglLahirLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(kelaminLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(usernameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(emailLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(submitRole))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Hak Akses", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Denda"));

        jLabel1.setText("Tanggal");

        tanggalCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Tanggal --", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        bulanCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Bulan --", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));

        tahunCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Tahun --", "2016", "2015" }));

        jLabel2.setText("Nominal");

        nominalTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nominalTxtKeyTyped(evt);
            }
        });

        submitBtn.setText("Submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        resetBtn.setText("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(tanggalCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bulanCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tahunCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(nominalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(resetBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(submitBtn)
                        .addGap(118, 118, 118)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tanggalCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tahunCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bulanCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nominalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitBtn)
                    .addComponent(resetBtn))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Data List"));

        dendaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        dendaTable.setEnabled(false);
        jScrollPane1.setViewportView(dendaTable);

        jLabel3.setText("Search By");

        searchCol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        searchCol.setEnabled(false);

        searchVal.setEnabled(false);

        searchBtn.setText("Search");
        searchBtn.setEnabled(false);
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchCol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchVal, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtn))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(searchCol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchBtn))
        );

        editBtn.setText("Edit");
        editBtn.setEnabled(false);
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        viewDendaTblBtn.setText("View Table");
        viewDendaTblBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDendaTblBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(viewDendaTblBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editBtn)
                    .addComponent(viewDendaTblBtn)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(204, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Denda Buku", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameCmbActionPerformed
        // TODO add your handling code here:
        if (usernameCmb.getItemCount() > 0) {
            String userStr = String.valueOf(usernameCmb.getSelectedItem());
            if (usernameCmb.getSelectedIndex() != 0) {
                User temp = getUserByUsername(userStr);
                if (temp != null) {
                    if (temp.getPengguna() != null) {
                        namaLabel.setText(temp.getPengguna().getNama());
                        jabatanLabel.setText(temp.getPengguna().getJabatan());
                        tempatLahirLabel.setText(temp.getPengguna().getTempatLahir());
                        tglLahirLabel.setText(temp.getPengguna().getTanggalLahir().toString());
                        kelaminLabel.setText(temp.getPengguna().getJenisKelamin());
                        usernameLabel.setText(temp.getUsername());
                        emailLabel.setText(temp.getEmail());
                    } else {
                        JOptionPane.showMessageDialog(null, "Selected username has no information", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                namaLabel.setText("");
                jabatanLabel.setText("");
                tempatLahirLabel.setText("");
                tglLahirLabel.setText("");
                kelaminLabel.setText("");
                usernameLabel.setText("");
                emailLabel.setText("");
            }
        }
    }//GEN-LAST:event_usernameCmbActionPerformed

    private void viewRoleTblBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewRoleTblBtnActionPerformed
        // TODO add your handling code here:
        if (viewRoleTblBtn.isSelected()) {
            viewRoleTblBtn.setText("Hide Table");
            roleTable.setEnabled(true);
            searchBtnRole.setEnabled(true);
            searchColumnCmbRole.setEnabled(true);
            searchValueRole.setEnabled(true);
            editBtnRole.setEnabled(true);
            deleteBtnRole.setEnabled(true);
            masterUserRoleList = userRoleController.fetchData(masterUserRoleList, false);
            userRoleList = masterUserRoleList;
            setTableValue();
        } else {
            viewRoleTblBtn.setText("View Table");
            roleTable.setEnabled(false);
            searchBtnRole.setEnabled(false);
            searchColumnCmbRole.setEnabled(false);
            searchValueRole.setEnabled(false);
            editBtnRole.setEnabled(false);
            deleteBtnRole.setEnabled(false);
            tableModel = new DefaultTableModel(userRoleColumnName.toArray(), userList.size());
            roleTable.setModel(tableModel);
        }
    }//GEN-LAST:event_viewRoleTblBtnActionPerformed

    private void searchBtnRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnRoleActionPerformed
        // TODO add your handling code here:
        userRoleList = new ArrayList<UserRole>();
        String searchCol = searchColumnCmbRole.getSelectedItem().toString();
        String searchParam = searchValueRole.getText();
        if (!searchParam.equals("")) {
            userRoleList = userRoleController.search(masterUserRoleList, searchParam, searchCol);
        } else {
            userRoleList = masterUserRoleList;
        }
        System.out.println("SIZE MASTER " + masterUserRoleList.size());
        System.out.println("SIZE LIST " + userRoleList.size());
        setTableValue();
    }//GEN-LAST:event_searchBtnRoleActionPerformed

    private void submitRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitRoleActionPerformed
        // TODO add your handling code here:
        if (usernameCmb.getSelectedIndex() != 0 && roleCmb.getSelectedIndex() != 0) {
            User userObj = getUserByUsername(usernameCmb.getSelectedItem().toString());
            Role roleObj = getRoleByRoleName(roleCmb.getSelectedItem().toString());
            if (userObj != null || roleObj != null) {
                userRole.setUser(userObj);
                userRole.setRole(roleObj);
                userRole.setActive(true);
                userRole.setUserInput(DataLibrary.getInstance().getUser().getUsername());

                userRoleController.setUserRole(userRole);
                userRoleController.setEditMode(editMode);
                String message = userRoleController.save();
                if (!message.equals("success")) {
                    JOptionPane.showMessageDialog(null, (editMode ? "Update" : "Submit") + " data gagal", "Submit Data Error", JOptionPane.ERROR_MESSAGE);
                }
                resetAllFieldInput();
                reInitValiables();

                //refresh table
                if (viewRoleTblBtn.isSelected()) {
                    masterUserRoleList = userRoleController.fetchData(masterUserRoleList, true);
                    userRoleList = masterUserRoleList;
                    setTableValue();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username or role not found, Cannot insert data", "INPUT-ERROR", 0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Username and password must be choosen, Cannot insert data", "INPUT-ERROR", 0);
        }
    }//GEN-LAST:event_submitRoleActionPerformed

    private void editBtnRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnRoleActionPerformed
        // TODO add your handling code here:
        if (editBtnRole.isSelected()) {
            editBtnRole.setText("Cancel Edit");
            editMode = true;
            userRole = userRoleList.get(roleTable.getSelectedRow());
            if (userRole != null) {
                usernameCmb.setSelectedItem(userRole.getUser().getUsername());
                usernameCmb.setEnabled(false);
                roleCmb.setSelectedItem(userRole.getRole().getRoleName());
            } else {
                JOptionPane.showMessageDialog(null, "No data info, Cannot edit data", "EDIT-ERROR", 0);
            }
        } else {
            usernameCmb.setEnabled(true);
            editBtnRole.setText("Edit");
            editMode = false;
            userRole = new UserRole();
            resetAllFieldInput();
        }
    }//GEN-LAST:event_editBtnRoleActionPerformed

    private void deleteBtnRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnRoleActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Are you sure want to delete selected record?", "Delete Record",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            userRole = userRoleList.get(roleTable.getSelectedRow());
            userRole.setActive(false);
            userRole.setUserInput(DataLibrary.getInstance().getUser().getUsername());
            editMode = true;
            userRoleController.setUserRole(userRole);
            userRoleController.setEditMode(editMode);
            userRoleController.save();

            //reset table
            roleTable.clearSelection();
            masterUserRoleList = userRoleController.fetchData(masterUserRoleList, true);
            userRoleList = masterUserRoleList;
            setTableValue();
            resetAllFieldInput();
            reInitValiables();
        } else {
            roleTable.clearSelection();
        }
    }//GEN-LAST:event_deleteBtnRoleActionPerformed

    private void viewDendaTblBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDendaTblBtnActionPerformed
        // TODO add your handling code here:
        if (viewDendaTblBtn.isSelected()) {
            viewDendaTblBtn.setText("Hide Table");
            dendaTable.setEnabled(true);
            searchBtn.setEnabled(true);
            searchCol.setEnabled(true);
            searchVal.setEnabled(true);
            editBtn.setEnabled(true);
            masterDendaList = dendaController.fetchData(masterDendaList, false);
            dendaList = masterDendaList;
            setTableValueDenda();
        } else {
            viewDendaTblBtn.setText("View Table");
            dendaTable.setEnabled(false);
            searchBtn.setEnabled(false);
            searchCol.setEnabled(false);
            searchVal.setEnabled(false);
            editBtn.setEnabled(false);
            dendaTableModel = new DefaultTableModel(dendaColumnName.toArray(), dendaList.size());
            dendaTable.setModel(dendaTableModel);
        }
    }//GEN-LAST:event_viewDendaTblBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        dendaList = new ArrayList<Denda>();
        String searchColStr = searchCol.getSelectedItem().toString();
        String searchParam = searchVal.getText();
        if (!searchParam.equals("")) {
            dendaList = dendaController.search(masterDendaList, searchParam, searchColStr);
        } else {
            dendaList = masterDendaList;
        }
        System.out.println("SIZE MASTER " + masterDendaList.size());
        System.out.println("SIZE LIST " + dendaList.size());
        setTableValueDenda();
    }//GEN-LAST:event_searchBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        resetAllFieldInputDenda();
    }//GEN-LAST:event_resetBtnActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        // TODO add your handling code here:
        if (tanggalCmb.getSelectedIndex() != 0
                && bulanCmb.getSelectedIndex() != 0
                && tahunCmb.getSelectedIndex() != 0 && !nominalTxt.getText().equals("")) {

            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(tahunCmb.getSelectedItem().toString()), bulanCmb.getSelectedIndex() - 1, Integer.parseInt(tanggalCmb.getSelectedItem().toString()));
            int nominal = Integer.parseInt(nominalTxt.getText());
            dendaModel.setTanggal(cal.getTime());
            dendaModel.setNominal(nominal);
            dendaModel.setActive(true);
            dendaModel.setUserInput(DataLibrary.getInstance().getUser().getUsername());

            dendaController.setDendaModel(dendaModel);
            dendaController.setEditMode(dendaEditMode);
            String message = dendaController.save();
            if (!message.equals("success")) {
                JOptionPane.showMessageDialog(null, (editMode ? "Update" : "Submit") + " data gagal", "Submit Data Error", JOptionPane.ERROR_MESSAGE);
            }
            resetAllFieldInputDenda();
            reInitDendaValiables();

            //refresh table
            if (viewDendaTblBtn.isSelected()) {
                masterDendaList = dendaController.fetchData(masterDendaList, true);
                dendaList = masterDendaList;
                setTableValueDenda();
            }
        } else {
            JOptionPane.showMessageDialog(null, "One or more field is empty, Cannot insert data", "INPUT-ERROR", 0);
        }
    }//GEN-LAST:event_submitBtnActionPerformed

    private void nominalTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nominalTxtKeyTyped
        // TODO add your handling code here:
        char temp = evt.getKeyChar();
        if (((temp < '0') || (temp > '9')) && (temp != KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_nominalTxtKeyTyped

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        if (editBtn.isSelected()) {
            editBtn.setText("Cancel Edit");
            dendaEditMode = true;
            dendaModel = dendaList.get(dendaTable.getSelectedRow());
            if (dendaModel != null) {
                System.out.println("tgl: " + dendaModel.getTanggal());
                Date temp = dendaModel.getTanggal();
                Calendar dateInCal = Calendar.getInstance();
                dateInCal.setTime(temp);
                tanggalCmb.setSelectedItem(String.valueOf(dateInCal.get(Calendar.DATE)));
                bulanCmb.setSelectedIndex(dateInCal.get(Calendar.MONTH) + 1);
                tahunCmb.setSelectedItem(String.valueOf(dateInCal.get(Calendar.YEAR)));
                tanggalCmb.setEnabled(false);
                bulanCmb.setEnabled(false);
                tahunCmb.setEnabled(false);
                nominalTxt.setText(String.valueOf(dendaModel.getNominal()));
            } else {
                JOptionPane.showMessageDialog(null, "No data info, Cannot edit data", "EDIT-ERROR", 0);
            }
        } else {
            tanggalCmb.setEnabled(true);
            bulanCmb.setEnabled(true);
            tahunCmb.setEnabled(true);
            editBtn.setText("Edit");
            dendaEditMode = false;
            dendaModel = new Denda();
            resetAllFieldInputDenda();
        }
    }//GEN-LAST:event_editBtnActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox bulanCmb;
    private javax.swing.JButton deleteBtnRole;
    private javax.swing.JTable dendaTable;
    private javax.swing.JToggleButton editBtn;
    private javax.swing.JToggleButton editBtnRole;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jabatanLabel;
    private javax.swing.JLabel kelaminLabel;
    private javax.swing.JLabel namaLabel;
    private javax.swing.JTextField nominalTxt;
    private javax.swing.JButton resetBtn;
    private javax.swing.JComboBox roleCmb;
    private javax.swing.JTable roleTable;
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton searchBtnRole;
    private javax.swing.JComboBox searchCol;
    private javax.swing.JComboBox searchColumnCmbRole;
    private javax.swing.JTextField searchVal;
    private javax.swing.JTextField searchValueRole;
    private javax.swing.JButton submitBtn;
    private javax.swing.JButton submitRole;
    private javax.swing.JComboBox tahunCmb;
    private javax.swing.JComboBox tanggalCmb;
    private javax.swing.JLabel tempatLahirLabel;
    private javax.swing.JLabel tglLahirLabel;
    private javax.swing.JComboBox usernameCmb;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JToggleButton viewDendaTblBtn;
    private javax.swing.JToggleButton viewRoleTblBtn;
    // End of variables declaration//GEN-END:variables
}
