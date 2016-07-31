/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.view;

import id.sch.elib.controller.DetailPengarangController;
import id.sch.elib.model.Buku;
import id.sch.elib.model.DetailPengarang;
import id.sch.elib.model.Pengarang;
import id.sch.elib.util.DataLibrary;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rizky.aditya
 */
public class ViewPengarang extends javax.swing.JInternalFrame {

    private final DetailPengarangController detailPengarangController;
    private DefaultTableModel tableModel;
    private ArrayList<DetailPengarang> masterDetailPengarangList;
    private ArrayList<DetailPengarang> detailPengarangList;
    private ArrayList<Buku> bukuList;
    private ArrayList<Pengarang> pengarangList;
    private ArrayList<String> columnName;
    private boolean editMode;
    private DetailPengarang detailPengarang;

    /**
     * Creates new form viewPengarang
     */
    public ViewPengarang() {
        initComponents();
        reInitValiables();
        tableView.setAutoCreateRowSorter(true);
        detailPengarangController = new DetailPengarangController();
        columnName = new ArrayList<String>();
        columnName.add("No.");
        columnName.add("Nama Pengarang");
        columnName.add("Judul Buku");
        columnName.add("ISBN");
        searchColCmb.removeAllItems();
        for (int i = 1; i < columnName.size(); i++) {
            searchColCmb.addItem(columnName.get(i));
        }
        reAddPengarangCmb(false);

        //set ISBN buku to cmb
        bukuList = DataLibrary.getInstance().getMasterListBuku(true);
        bukuCmb.removeAllItems();
        bukuCmb.addItem("--Pilih ISBN--");
        for (int i = 0; i < bukuList.size(); i++) {
            bukuCmb.addItem(bukuList.get(i).getIsbn());
        }
        masterDetailPengarangList = detailPengarangController.fetchData(masterDetailPengarangList, false);
        detailPengarangList = masterDetailPengarangList;
        setTableValue();
    }

    public void reInitValiables() {
        detailPengarang = new DetailPengarang();
        editMode = false;
        editButton.setSelected(editMode);
        editButton.setText("Edit Record");
        bukuCmb.setSelectedIndex(0);
        pengarangCmb.setSelectedIndex(0);
        newPengarang.setSelected(false);
        pengarangTxt.setText("");
        pengarangTxt.setEnabled(false);
        pengarangCmb.setEnabled(true);
    }

    public void setTableValue() {
        tableModel = new DefaultTableModel(columnName.toArray(), detailPengarangList.size());
        tableView.setModel(tableModel);
        for (int i = 0; i < detailPengarangList.size(); i++) {
            tableView.setValueAt((i + 1), i, 0);
            tableView.setValueAt(detailPengarangList.get(i).getPengarang().getNamaPengarang(), i, 1);
            tableView.setValueAt(detailPengarangList.get(i).getBuku().getJudul(), i, 2);
            tableView.setValueAt(detailPengarangList.get(i).getBuku().getIsbn(), i, 3);
        }
    }

    public void resetInfoBuku() {
        judulBukuLbl.setText(": ");
        penerbitLbl.setText(": ");
        thnTerbitLbl.setText(": ");
        kategoriLbl.setText(": ");
    }

    public void resetAllFieldInput() {
        pengarangCmb.setSelectedIndex(0);
        newPengarang.setSelected(false);
        pengarangTxt.setText("");
        bukuCmb.setSelectedIndex(0);
        resetInfoBuku();
    }

    public Buku getBukuByIsbn(String isbn) {
        for (Buku buku : bukuList) {
            if (buku.getIsbn().equals(isbn)) {
                return buku;
            }
        }
        return null;
    }

    public void reAddPengarangCmb(boolean init) {
        pengarangList = DataLibrary.getInstance().getMasterListPengarang(init);
        //set nama pengarang to cmb
        pengarangCmb.removeAllItems();
        pengarangCmb.addItem("--Pilih Pengarang--");
        for (int i = 0; i < pengarangList.size(); i++) {
            pengarangCmb.addItem(pengarangList.get(i).getNamaPengarang());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pengarangCmb = new javax.swing.JComboBox();
        newPengarang = new javax.swing.JCheckBox();
        pengarangTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        bukuCmb = new javax.swing.JComboBox();
        resetBtn = new javax.swing.JButton();
        submitBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        searchBtn = new javax.swing.JButton();
        searchValTxt = new javax.swing.JTextField();
        searchColCmb = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableView = new javax.swing.JTable();
        editButton = new javax.swing.JToggleButton();
        deleteButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        thnTerbitLbl = new javax.swing.JLabel();
        penerbitLbl = new javax.swing.JLabel();
        judulBukuLbl = new javax.swing.JLabel();
        kategoriLbl = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Pengelola Data Pengarang");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data"));

        jLabel1.setText("Nama Pengarang");

        pengarangCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        newPengarang.setText("Pengarang baru");
        newPengarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPengarangActionPerformed(evt);
            }
        });

        pengarangTxt.setEnabled(false);

        jLabel2.setText("ISBN Buku");

        bukuCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bukuCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bukuCmbActionPerformed(evt);
            }
        });

        resetBtn.setText("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        submitBtn.setText("Submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(submitBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(newPengarang)
                            .addComponent(pengarangCmb, 0, 234, Short.MAX_VALUE)
                            .addComponent(pengarangTxt)
                            .addComponent(bukuCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pengarangCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newPengarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pengarangTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(bukuCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetBtn)
                    .addComponent(submitBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pengarang"));

        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        searchColCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Search By");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchColCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchValTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtn)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBtn)
                    .addComponent(searchValTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchColCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableView.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableView);

        editButton.setText("Edit Record");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
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
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(deleteButton)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Informasi Buku"));

        jLabel4.setText("Judul Buku");

        jLabel5.setText("Tahun Terbit");

        jLabel6.setText("Penerbit");

        jLabel7.setText("Kategori");

        thnTerbitLbl.setText("jLabel8");

        penerbitLbl.setText("jLabel9");

        judulBukuLbl.setText("jLabel10");

        kategoriLbl.setText("jLabel11");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kategoriLbl)
                    .addComponent(judulBukuLbl)
                    .addComponent(penerbitLbl)
                    .addComponent(thnTerbitLbl))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(judulBukuLbl))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(penerbitLbl))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(thnTerbitLbl))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(kategoriLbl))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bukuCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bukuCmbActionPerformed
        // TODO add your handling code here:
        if (bukuCmb.getSelectedIndex() > 0) {
            resetInfoBuku();
            String selectedIsbn = String.valueOf(bukuCmb.getSelectedItem());
            Buku temp = getBukuByIsbn(selectedIsbn);
            if (temp != null) {
                judulBukuLbl.setText(": " + temp.getJudul());
                penerbitLbl.setText(": " + temp.getPenerbit().getNamaPenerbit());
                thnTerbitLbl.setText(": " + temp.getTahunTerbit());
                kategoriLbl.setText(": " + temp.getRakBuku().getNamaJenis());
            } else {
                JOptionPane.showMessageDialog(null, "Selected ISBN has no information", "Empty Information", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            resetInfoBuku();
        }
    }//GEN-LAST:event_bukuCmbActionPerformed

    private void newPengarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPengarangActionPerformed
        // TODO add your handling code here:
        if (newPengarang.isSelected()) {
            pengarangTxt.setEnabled(true);
            pengarangCmb.setEnabled(false);
        } else {
            pengarangCmb.setEnabled(true);
            pengarangTxt.setEnabled(false);
        }
    }//GEN-LAST:event_newPengarangActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        if (editMode) {
            JOptionPane.showMessageDialog(null, "You are in edit mode.\n Please click cancel edit button before reseting field", "Cannot reset field", JOptionPane.WARNING_MESSAGE);
        } else {
            resetAllFieldInput();
        }
    }//GEN-LAST:event_resetBtnActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        if (tableView.getSelectedRow() == -1) {
            editButton.setSelected(false);
            editButton.setText("Edit Record");
            JOptionPane.showMessageDialog(null, "You must select a record before edit", "Row Did Not Selected Yet", JOptionPane.WARNING_MESSAGE);

        } else {
            if (editButton.isSelected()) {
                editButton.setText("Cancel Edit");
                editMode = true;
                detailPengarang = detailPengarangList.get(tableView.getSelectedRow());
                if (detailPengarang != null) {
                    pengarangCmb.setSelectedItem(detailPengarang.getPengarang().getNamaPengarang());
                    bukuCmb.setSelectedItem(detailPengarang.getBuku().getIsbn());
                } else {
                    editButton.setText("Edit Record");
                    reInitValiables();
                    tableView.clearSelection();
                    JOptionPane.showMessageDialog(null, "No information available in selected row", "Information is Missing", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                editButton.setText("Edit Record");
                tableView.clearSelection();
                reInitValiables();
            }
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        detailPengarangList = new ArrayList<DetailPengarang>();
        String searchCol = searchColCmb.getSelectedItem().toString();
        String searchParam = searchValTxt.getText();
        if (!searchParam.equals("")) {
            detailPengarangList = detailPengarangController.search(masterDetailPengarangList, searchParam, searchCol);
        } else {
            detailPengarangList = masterDetailPengarangList;
        }
        setTableValue();
    }//GEN-LAST:event_searchBtnActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        // TODO add your handling code here
        Pengarang pengarang = new Pengarang();
        Buku buku = bukuList.get(bukuCmb.getSelectedIndex() - 1);
        String pengarangStr = "";
        if (newPengarang.isSelected()) {
            pengarangStr = pengarangTxt.getText();
        } else {
            pengarang = pengarangList.get(pengarangCmb.getSelectedIndex() - 1);
        }
        if (buku != null && (pengarang.getNamaPengarang() != null || !pengarangStr.equals(""))) {
            detailPengarang.setBuku(buku);
            if (newPengarang.isSelected()) {
                Pengarang temp = new Pengarang();
                temp.setNamaPengarang(pengarangStr);
                temp.setActive(true);
                temp.setUserInput(DataLibrary.getInstance().getUser().getUsername());
                detailPengarang.setPengarang(temp);
            } else {
                detailPengarang.setPengarang(pengarang);
            }
            detailPengarang.setNewPengarang(newPengarang.isSelected());
            detailPengarang.setActive(true);
            detailPengarang.setUserInput(DataLibrary.getInstance().getUser().getUsername());

            detailPengarangController.setDetaiPengarangModel(detailPengarang);
            detailPengarangController.setEditMode(editMode);
            String message = detailPengarangController.save();
            if (!message.equals("success")) {
                JOptionPane.showMessageDialog(null, (editMode ? "Update" : "Submit") + " data gagal", "Submit Data Error", JOptionPane.ERROR_MESSAGE);
            }

            reAddPengarangCmb(newPengarang.isSelected());
            resetAllFieldInput();
            reInitValiables();

            //refresh table
            masterDetailPengarangList = detailPengarangController.fetchData(masterDetailPengarangList, true);
            detailPengarangList = masterDetailPengarangList;
            setTableValue();
        } else {
            JOptionPane.showMessageDialog(null, "ISBN or Nama Pengarang is empty", "Cannot Insert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_submitBtnActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Are you sure want to delete selected record?", "Delete Record",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            detailPengarang = detailPengarangList.get(tableView.getSelectedRow());
            if (detailPengarang != null) {
                detailPengarang.setActive(false);
                detailPengarang.setUserInput(DataLibrary.getInstance().getUser().getUsername());
                editMode = true;
                detailPengarangController.setDetaiPengarangModel(detailPengarang);
                detailPengarangController.setEditMode(editMode);
                detailPengarangController.save();

                //reset table
                tableView.clearSelection();
                masterDetailPengarangList = detailPengarangController.fetchData(masterDetailPengarangList, true);
                detailPengarangList = masterDetailPengarangList;
                setTableValue();
                resetAllFieldInput();
                reInitValiables();
            }
        } else {
            tableView.clearSelection();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox bukuCmb;
    private javax.swing.JButton deleteButton;
    private javax.swing.JToggleButton editButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel judulBukuLbl;
    private javax.swing.JLabel kategoriLbl;
    private javax.swing.JCheckBox newPengarang;
    private javax.swing.JLabel penerbitLbl;
    private javax.swing.JComboBox pengarangCmb;
    private javax.swing.JTextField pengarangTxt;
    private javax.swing.JButton resetBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JComboBox searchColCmb;
    private javax.swing.JTextField searchValTxt;
    private javax.swing.JButton submitBtn;
    private javax.swing.JTable tableView;
    private javax.swing.JLabel thnTerbitLbl;
    // End of variables declaration//GEN-END:variables
}
