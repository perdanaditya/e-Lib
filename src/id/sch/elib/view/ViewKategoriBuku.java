/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.sch.elib.view;

import id.sch.elib.controller.RakBukuController;
import id.sch.elib.model.RakBuku;
import id.sch.elib.util.DataLibrary;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nitha
 */
public class ViewKategoriBuku extends javax.swing.JInternalFrame {

    private RakBukuController rakBukuController;
    private DefaultTableModel tableModel;
    private ArrayList<RakBuku> masterRakBukuList;
    private ArrayList<RakBuku> rakBukuList;
    private ArrayList<String> columnName;
    private boolean editMode;
    private RakBuku rakBukuModel;
    
    protected static boolean inputBukuMode;
    /**
     * Creates new form ViewKategoriBuku
     */
    public ViewKategoriBuku() {
        initComponents();
        reInitValiables();
        
        tableList.setAutoCreateRowSorter(true);
        rakBukuController = new RakBukuController();
        columnName = new ArrayList<String>();
        columnName.add("Nama Kategori");
        columnName.add("No. DDC");
        
        searchColumnCb.removeAllItems();
        for (int i = 0; i < columnName.size(); i++) {
            searchColumnCb.addItem(columnName.get(i));
        }
        
        masterRakBukuList = rakBukuController.fetchData(masterRakBukuList, false);
        System.out.println("test#1 "+masterRakBukuList.size());
        rakBukuList = masterRakBukuList;
        setTableValue();
        
    }
    
    public void setTableValue() {
        tableModel = new DefaultTableModel(columnName.toArray(), rakBukuList.size());
        tableList.setModel(tableModel);
        for (int i = 0; i < rakBukuList.size(); i++) {
            tableList.setValueAt(rakBukuList.get(i).getNamaJenis(), i, 0);
            tableList.setValueAt(rakBukuList.get(i).getNoDdc(), i, 1);
        }
    }

    public void resetAllFieldInput() {
        namaKategori.setText("");
        noDdc.setText("");
    }
    
    public void reInitValiables() {
        rakBukuModel = new RakBuku();
        editMode = false;
        editButton.setSelected(editMode);
        editButton.setText("Edit Record");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        namaKategori = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        noDdc = new javax.swing.JTextField();
        submit = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();
        editButton = new javax.swing.JToggleButton();
        deleteButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchColumnCb = new javax.swing.JComboBox();
        searchValue = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Panel"));

        jLabel3.setText("Nama Kategori");

        jLabel4.setText("No. DDC");

        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        cancel.setText("Reset");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(submit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancel))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(namaKategori)
                            .addComponent(noDdc, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namaKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(noDdc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(submit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableList.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableList);

        editButton.setText("jToggleButton1");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete Record");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(editButton)))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Searching Panel"));

        jLabel1.setText("Search by");

        searchColumnCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchColumnCb, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchValue, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtn))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(searchColumnCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchBtn))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
        String nama = namaKategori.getText();
        String noDDC = noDdc.getText();
        if (!nama.equals("") && !noDDC.equals("")) {
            rakBukuModel.setNamaJenis(nama);
            rakBukuModel.setNoDdc(noDDC);
            rakBukuModel.setActive(true);
            rakBukuModel.setUserInput(DataLibrary.getInstance().getUser().getUsername());

            rakBukuController.setRakBuku(rakBukuModel);
            rakBukuController.setEditMode(editMode);
            String message = rakBukuController.save();
            if (!message.equals("success")) {
                JOptionPane.showMessageDialog(null, (editMode ? "Update" : "Submit") + " data gagal", "Submit Data Error", JOptionPane.ERROR_MESSAGE);
            }
            resetAllFieldInput();
            reInitValiables();

            //refresh table
            masterRakBukuList = rakBukuController.fetchData(masterRakBukuList, true);
            rakBukuList = masterRakBukuList;
            setTableValue();
            if(inputBukuMode){
                InputBuku inputBuku = InputBuku.getInstance(false);
                inputBuku.setRakBukuItem(false);
                inputBuku.rakBuku.setSelectedIndex(masterRakBukuList.size()-1);
                inputBukuMode = false;
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "One or more field is empty, Cannot insert data", "INPUT-ERROR", 0);
        }
    }//GEN-LAST:event_submitActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        resetAllFieldInput();
    }//GEN-LAST:event_cancelActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        if (editButton.isSelected()) {
            editButton.setText("Cancel Edit");
            editMode = true;
            rakBukuModel = rakBukuList.get(tableList.getSelectedRow());

            namaKategori.setText(rakBukuModel.getNamaJenis());
            noDdc.setText(rakBukuModel.getNoDdc());
        } else {
            editButton.setText("Edit Record");
            editMode = false;
            rakBukuModel = new RakBuku();
            tableList.clearSelection();
            resetAllFieldInput();
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Are you sure want to delete selected record?", "Delete Record",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
        rakBukuModel = rakBukuList.get(tableList.getSelectedRow());
        rakBukuModel.setActive(false);
        rakBukuModel.setUserInput(DataLibrary.getInstance().getUser().getUsername());
        editMode = true;
        rakBukuController.setRakBuku(rakBukuModel);
        rakBukuController.setEditMode(editMode);
        rakBukuController.save();

        //reset table
        tableList.clearSelection();
        rakBukuList = rakBukuController.fetchData(masterRakBukuList, true);
        setTableValue();
        resetAllFieldInput();
        reInitValiables();
        } else {
            tableList.clearSelection();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        rakBukuList = new ArrayList<RakBuku>();
        String searchCol = searchColumnCb.getSelectedItem().toString();
        String searchParam = searchValue.getText();
        System.out.println("param " + searchParam);
        System.out.println("search col " + searchCol);
        if (!searchParam.equals("")) {
            rakBukuList = rakBukuController.search(masterRakBukuList, searchParam, searchCol);
        } else {
            rakBukuList = masterRakBukuList;
        }
        System.out.println("SIZE MASTER " + masterRakBukuList.size());
        System.out.println("SIZE LIST " + rakBukuList.size());
        setTableValue();
    }//GEN-LAST:event_searchBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JToggleButton editButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField namaKategori;
    private javax.swing.JTextField noDdc;
    private javax.swing.JButton searchBtn;
    private javax.swing.JComboBox searchColumnCb;
    private javax.swing.JTextField searchValue;
    private javax.swing.JButton submit;
    private javax.swing.JTable tableList;
    // End of variables declaration//GEN-END:variables
}
