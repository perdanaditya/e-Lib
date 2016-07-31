/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.view;

import id.sch.elib.controller.BukuController;
import id.sch.elib.model.Buku;
import id.sch.elib.model.Penerbit;
import id.sch.elib.model.RakBuku;
import id.sch.elib.util.DataLibrary;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
//import java.nio.file.Files;

/**
 *
 * @author RAP
 */
public class InputBuku extends javax.swing.JInternalFrame {

    /**
     * Creates new form InputBukuView
     */
    private BukuController bukuController;
    private DefaultTableModel tableModel;
    private ArrayList<Buku> masterBukuList;
    private ArrayList<Buku> bukuList;
    private ArrayList<String> columnName;
    private boolean editMode;
    private Buku bukuModel;
    private ArrayList<Penerbit> penerbitList;
    private ArrayList<RakBuku> rakBukuList;
    private File selectedFile;

    public InputBuku() {
        initComponents();
        reInitValiables();

        tableList.setAutoCreateRowSorter(true);
        bukuController = new BukuController();
        columnName = new ArrayList<String>();
        columnName.add("ISBN");
        columnName.add("Judul");
        columnName.add("Kategori");
        columnName.add("Penerbit");
        columnName.add("Tahun Terbit");
        columnName.add("Stock");

        //set search column by
        searchColumnCb.removeAllItems();
        for (int i = 0; i < columnName.size(); i++) {
            searchColumnCb.addItem(columnName.get(i));
        }

        //set rak buku
        rakBukuList = DataLibrary.getInstance().getMasterListRakBuku(true);
        rakBuku.removeAllItems();
        for (int i = 0; i < rakBukuList.size(); i++) {
            rakBuku.addItem(rakBukuList.get(i).getNoDdc() + "-" + rakBukuList.get(i).getNamaJenis());
        }

        //set penerbit cmb box
        penerbitCmb.removeAllItems();
        penerbitList = DataLibrary.getInstance().getMasterListPenerbit(false);
        for (int i = 0; i < penerbitList.size(); i++) {
            penerbitCmb.addItem(penerbitList.get(i).getId() + "-" + penerbitList.get(i).getNamaPenerbit());
        }
        //set table value
        masterBukuList = bukuController.fetchData(masterBukuList, false);
        bukuList = masterBukuList;
        setTableValue();
    }

    public void setTableValue() {
        tableModel = new DefaultTableModel(columnName.toArray(), bukuList.size());
        tableList.setModel(tableModel);
        for (int i = 0; i < bukuList.size(); i++) {
            tableList.setValueAt(bukuList.get(i).getIsbn(), i, 0);
            tableList.setValueAt(bukuList.get(i).getJudul(), i, 1);
            tableList.setValueAt(bukuList.get(i).getRakBuku().getNamaJenis(), i, 2);
            tableList.setValueAt(bukuList.get(i).getPenerbit().getNamaPenerbit(), i, 3);
            tableList.setValueAt(bukuList.get(i).getTahunTerbit(), i, 4);
            tableList.setValueAt(bukuList.get(i).getStock(), i, 5);
        }
    }

    public void resetAllFieldInput() {
        isbnTxt.setText("");
        judulTxt.setText("");
        rakBuku.setSelectedIndex(0);
        penerbitCmb.setSelectedIndex(0);
        tahunTerbitTxt.setText("");
        pathLabel.setText("");
        stockSpinner.setValue(0);
        masaPinjamSpinner.setValue(0);
        selectedFile = new File("");
        image.setIcon(new ImageIcon());
        sizeTxt.setText("");
    }

    public void reInitValiables() {
        bukuModel = new Buku();
        selectedFile = new File("");
        editMode = false;
        editButton.setSelected(editMode);
        editButton.setText("Edit Record");
        pathLabel.setText("");
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
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        searchColumnCb = new javax.swing.JComboBox();
        searchValue = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        isbnTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        judulTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rakBuku = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        penerbitCmb = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        tahunTerbitTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        stockSpinner = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        masaPinjamSpinner = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        pathLabel = new javax.swing.JLabel();
        browse = new javax.swing.JButton();
        previewPanel = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        sizeTxt = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Input Data Buku");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Searching Panel"));

        jLabel7.setText("Search by");

        searchColumnCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(179, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchColumnCb, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchValue, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtn))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel7)
                .addComponent(searchColumnCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchBtn))
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

        deleteButton.setText("Delete Record");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton.setText("jToggleButton1");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(editButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("ISBN *");

        jLabel2.setText("Judul *");

        jLabel5.setText("Kategori *");

        rakBuku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Penerbit *");

        penerbitCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Tahun Terbit");

        jLabel8.setText("Stock");

        jLabel10.setText("Masa Pinjam *");

        jLabel6.setText("Cover");

        pathLabel.setText("path");

        browse.setText("Browse");
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });

        previewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Preview"));

        javax.swing.GroupLayout previewPanelLayout = new javax.swing.GroupLayout(previewPanel);
        previewPanel.setLayout(previewPanelLayout);
        previewPanelLayout.setHorizontalGroup(
            previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(previewPanelLayout.createSequentialGroup()
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(sizeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        previewPanelLayout.setVerticalGroup(
            previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
            .addGroup(previewPanelLayout.createSequentialGroup()
                .addComponent(sizeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 186, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 51));
        jLabel9.setText("* Mandatory field");

        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(171, 171, 171)
                .addComponent(browse))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stockSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tahunTerbitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(masaPinjamSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(penerbitCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rakBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(judulTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isbnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(previewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(isbnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(judulTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rakBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(penerbitCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tahunTerbitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(stockSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(masaPinjamSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(pathLabel)
                    .addComponent(browse))
                .addGap(6, 6, 6)
                .addComponent(previewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(submit)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Penerbit findPenerbitById(long id) {
        int index = 0;
        while (penerbitList.size() > index) {
            if (penerbitList.get(index).getId() == id) {
                return penerbitList.get(index);
            }
            index++;
        }
        return null;
    }

    public RakBuku findRakBukuByDdc(String id) {
        int index = 0;
        while (rakBukuList.size() > index) {
            if (rakBukuList.get(index).getNoDdc().equals(id)) {
                return rakBukuList.get(index);
            }
            index++;
        }
        return null;
    }
    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
        String isbn = isbnTxt.getText();
        String judul = judulTxt.getText();
//        String rakStr[] = ((String) rakBuku.getSelectedItem()).split("-");
//        RakBuku rak = findRakBukuByDdc(rakStr[0]);
        RakBuku rak = rakBukuList.get(rakBuku.getSelectedIndex());
//        String penerbitStr[] = ((String) penerbitCmb.getSelectedItem()).split("-");
//        Penerbit penerbit = findPenerbitById(Long.parseLong(penerbitStr[0]));
        Penerbit penerbit = penerbitList.get(penerbitCmb.getSelectedIndex());
        String tahun = tahunTerbitTxt.getText();
        byte[] cover;
        try {
            System.out.println("CONVERTING IMAGE TO BYTE[]");
//            cover = Files.readAllBytes(selectedFile.toPath());
            FileInputStream fis = new FileInputStream(selectedFile);
            cover = new byte[(int) selectedFile.length()];
            fis.read(cover);
            for (int i = 0; i < cover.length; i++) {
	       	System.out.print((char)cover[i]);
            }
            System.out.println("IMAGE CONVERTED");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "CONVERT ERROR: " + e.getMessage(), "IMAGE CONVERT ERROR", JOptionPane.WARNING_MESSAGE);
            System.out.println("CONVERT ERROR: " + e.getMessage());
            cover = new byte[0];
        }
//        byte[] cover = selectedFile.;
        int stock = (Integer) stockSpinner.getValue();
        int masaPinjam = (Integer) masaPinjamSpinner.getValue();
        if (!isbn.equals("") && !judul.equals("") && rak != null && penerbit != null && masaPinjam > 0) {
            bukuModel.setIsbn(isbn);
            bukuModel.setJudul(judul);
            bukuModel.setRakBuku(rak);
            bukuModel.setPenerbit(penerbit);
            bukuModel.setTahunTerbit(tahun);
            bukuModel.setMasaPinjam(masaPinjam);
            bukuModel.setStock(stock);
            bukuModel.setCover(cover);
            bukuModel.setActive(true);
            bukuModel.setUserInput(DataLibrary.getInstance().getUser().getUsername());

            bukuController.setBukuModel(bukuModel);
            bukuController.setEditMode(editMode);
            String message = bukuController.save();
            if (!message.equals("success")) {
                JOptionPane.showMessageDialog(null, (editMode ? "Update" : "Submit") + " data gagal", "Submit Data Error", JOptionPane.ERROR_MESSAGE);
            }
            resetAllFieldInput();
            reInitValiables();

            //refresh table
            masterBukuList = bukuController.fetchData(masterBukuList, true);
            bukuList = masterBukuList;
            setTableValue();
        } else {
            JOptionPane.showMessageDialog(null, "One or more field is empty, Cannot insert data", "INPUT-ERROR", 0);
        }
    }//GEN-LAST:event_submitActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        resetAllFieldInput();
    }//GEN-LAST:event_cancelActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        bukuList = new ArrayList<Buku>();
        String searchCol = searchColumnCb.getSelectedItem().toString();
        String searchParam = searchValue.getText();
        if (!searchParam.equals("")) {
            bukuList = bukuController.search(masterBukuList, searchParam, searchCol);
        } else {
            bukuList = masterBukuList;
        }
        setTableValue();
    }//GEN-LAST:event_searchBtnActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        if (editButton.isSelected()) {
            editButton.setText("Cancel Edit");
            editMode = true;
            bukuModel = bukuList.get(tableList.getSelectedRow());

            isbnTxt.setText(bukuModel.getIsbn());
            judulTxt.setText(bukuModel.getJudul());
            penerbitCmb.setSelectedItem(bukuModel.getPenerbit().getId() + "-" + bukuModel.getPenerbit().getNamaPenerbit());
            rakBuku.setSelectedItem(bukuModel.getRakBuku().getNoDdc() + "-" + bukuModel.getRakBuku().getNamaJenis());
            tahunTerbitTxt.setText(bukuModel.getTahunTerbit());
            stockSpinner.setValue(bukuModel.getStock());
            masaPinjamSpinner.setValue(bukuModel.getMasaPinjam());

            try {
//                selectedFile = fileChooser.getSelectedFile();
                FileOutputStream fo = new FileOutputStream("img.jpg");
                fo.write(bukuModel.getCover());
                fo.close();
                selectedFile = null;
                selectedFile = new File("img.jpg");
                String pathName = selectedFile.getAbsolutePath();
                Image img = ImageIO.read(selectedFile);
                img = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(img);
                image.setIcon(icon);
                //TODO trim pathName
                pathLabel.setText(pathName);
                int i = 0;
                long size = selectedFile.length();
                while (size >= 1024) {
                    size = size / 1024;
                    i++;
                }
                sizeTxt.setText(size + " " + DataLibrary.getInstance().getSizeScale(i));
            } catch (Exception e) {
                System.out.println("EXCEPTION IN IMG LOAD: " + e.getMessage());
            }
        } else {
            editButton.setText("Edit Record");
            editMode = false;
            bukuModel = new Buku();
            if (selectedFile.exists()) {
                selectedFile.delete();
            }
            resetAllFieldInput();
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            // user selects a file
            try {
                selectedFile = fileChooser.getSelectedFile();
                String pathName = selectedFile.getAbsolutePath();
                Image img = ImageIO.read(selectedFile);
                img = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(img);
                image.setIcon(icon);
                //TODO trim pathName
                pathLabel.setText(pathName);
                int i = 0;
                long size = selectedFile.length();
                while (size >= 1024) {
                    size = size / 1024;
                    i++;
                }
                sizeTxt.setText(size + " " + DataLibrary.getInstance().getSizeScale(i));
                //TODO save to db
            } catch (Exception e) {
                System.out.println("EXCEPTION IN IMG LOAD: " + e.getMessage());
            }
        } else {
            image.setIcon(new ImageIcon());
            sizeTxt.setText("");
        }
    }//GEN-LAST:event_browseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browse;
    private javax.swing.JButton cancel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JToggleButton editButton;
    private javax.swing.JLabel image;
    private javax.swing.JTextField isbnTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField judulTxt;
    private javax.swing.JSpinner masaPinjamSpinner;
    private javax.swing.JLabel pathLabel;
    private javax.swing.JComboBox penerbitCmb;
    private javax.swing.JPanel previewPanel;
    private javax.swing.JComboBox rakBuku;
    private javax.swing.JButton searchBtn;
    private javax.swing.JComboBox searchColumnCb;
    private javax.swing.JTextField searchValue;
    private javax.swing.JLabel sizeTxt;
    private javax.swing.JSpinner stockSpinner;
    private javax.swing.JButton submit;
    private javax.swing.JTable tableList;
    private javax.swing.JTextField tahunTerbitTxt;
    // End of variables declaration//GEN-END:variables
}
