/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.view;

import id.sch.elib.controller.PeminjamanController;
import id.sch.elib.model.Buku;
import id.sch.elib.model.DetailPeminjaman;
import id.sch.elib.model.Peminjaman;
import id.sch.elib.model.User;
import id.sch.elib.util.DataLibrary;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author rizky.aditya
 */
public class ViewPeminjaman extends javax.swing.JInternalFrame {

    private final PeminjamanController peminjamanController;
    private DefaultTableModel tableModel;
    private ArrayList<Peminjaman> masterPeminjamanList;
    private ArrayList<Peminjaman> peminjamanList;
    private ArrayList<DetailPeminjaman> detailPeminjamanList;
    private ArrayList<Buku> bukuList;
    private ArrayList<User> userList;
    private ArrayList<String> columnName;
    private ArrayList<String> columnDetailName;

    private JComboBox cmbKembali;

    private boolean editMode;
    private Peminjaman peminjamanModel;
    private Buku b;
    private User user;

    /**
     * Creates new form ViewPeminjaman
     */
    public ViewPeminjaman() {
        initComponents();
        reInitValiables();
        resetAllFieldInput();
        tableView.setAutoCreateRowSorter(true);
        ViewDetailTbl.setAutoCreateRowSorter(true);
        peminjamanController = new PeminjamanController();
        //set table column for table list peminjaman
        columnName = new ArrayList<String>();
        columnName.add("No.");
        columnName.add("No. Peminjaman");
        columnName.add("No. Induk");
        columnName.add("Nama");
        columnName.add("Jabatan");
        columnName.add("Tanggal Pinjam");
        columnName.add("Status Peminjaman");
        columnName.add("Last Edited");
        searchColCmb.removeAllItems();
        for (int i = 1; i < columnName.size(); i++) {
            String col = columnName.get(i);
            searchColCmb.addItem(col);
        }

        //set table column for table detail peminjaman
        columnDetailName = new ArrayList<String>();
        columnDetailName.add("No.");
        columnDetailName.add("ISBN");
        columnDetailName.add("Judul Buku");
        columnDetailName.add("Kategori");
        columnDetailName.add("Masa Pinjam");
        columnDetailName.add("Status Pinjam");

        userList = DataLibrary.getInstance().getMasterListUserDetails(true);
        bukuList = DataLibrary.getInstance().getMasterListBuku(true);

        masterPeminjamanList = peminjamanController.fetchData(masterPeminjamanList, true);
        peminjamanList = masterPeminjamanList;
        setTableValue(true);
        tableModel = new DefaultTableModel(columnDetailName.toArray(), 0);
        ViewDetailTbl.setModel(tableModel);

        cmbKembali = new JComboBox();
        cmbKembali.addItem("Belum Kembali");
        cmbKembali.addItem("Dikembalikan");
        cmbKembali.addItem("Perpanjang");
    }

    public void reInitValiables() {
        detailPeminjamanList = new ArrayList<DetailPeminjaman>();
        peminjamanModel = new Peminjaman();
        user = new User();
        b = new Buku();
        editMode = false;
        editButton.setSelected(editMode);
        editButton.setText("Edit Record");
    }

    public void setTableValue(boolean isPeminjaman) {
        if (isPeminjaman) {
            tableModel = new DefaultTableModel(columnName.toArray(), peminjamanList.size());
            tableView.setModel(tableModel);
            SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
            for (int i = 0; i < peminjamanList.size(); i++) {
                tableView.setValueAt((i + 1), i, 0);
                tableView.setValueAt(peminjamanList.get(i).getNoPeminjaman(), i, 1);
                tableView.setValueAt(peminjamanList.get(i).getUser().getPengguna().getNoInduk(), i, 2);
                tableView.setValueAt(peminjamanList.get(i).getUser().getPengguna().getNama(), i, 3);
                tableView.setValueAt(peminjamanList.get(i).getUser().getPengguna().getJabatan(), i, 4);
                tableView.setValueAt(format.format(peminjamanList.get(i).getTanggalPinjam()), i, 5);
                tableView.setValueAt(peminjamanList.get(i).isActive() ? "Open" : "Close", i, 6);
                tableView.setValueAt(peminjamanList.get(i).getUserInput(), i, 7);
            }
        } else {
            tableModel = new DefaultTableModel(columnDetailName.toArray(), detailPeminjamanList.size());
            ViewDetailTbl.setModel(tableModel);
            if (editMode) {
                TableColumn optionsColumn = ViewDetailTbl.getColumn("Status Pinjam");
                optionsColumn.setCellEditor(new DefaultCellEditor(cmbKembali));
            }
            for (int i = 0; i < detailPeminjamanList.size(); i++) {
                ViewDetailTbl.setValueAt((i + 1), i, 0);
                ViewDetailTbl.setValueAt(detailPeminjamanList.get(i).getBuku().getIsbn(), i, 1);
                ViewDetailTbl.setValueAt(detailPeminjamanList.get(i).getBuku().getJudul(), i, 2);
                ViewDetailTbl.setValueAt(detailPeminjamanList.get(i).getBuku().getRakBuku().getNamaJenis(), i, 3);
                ViewDetailTbl.setValueAt(detailPeminjamanList.get(i).getBuku().getMasaPinjam() + " hari", i, 4);
                ViewDetailTbl.setValueAt(detailPeminjamanList.get(i).getTanggalPengembalian() == null ? "Belum Kembali" : "Dikembalikan", i, 5);
            }
        }
    }

    public void resetInfoBuku() {
        judulBukuLbl.setText(": ");
        penerbitLbl.setText(": ");
        thnTerbitLbl.setText(": ");
        kategoriLbl.setText(": ");
        stockLbl.setText(": ");
    }

    public void resetInfoPengguna() {
        namaLbl.setText(": ");
        jabatanLbl.setText(": ");
    }

    public void resetAllFieldInput() {
        noIndukTxt.setText("");
        isbnTxt.setText("");
        resetInfoBuku();
        resetInfoPengguna();
        progressLbl.setText("");
        progressNoIndukLbl.setText("");
    }

    private User getUserByNoInduk(String noInduk) {
        for (int i = 0; i < userList.size(); i++) {
            User p = userList.get(i);
            if (p.getPengguna().getNoInduk().equals(noInduk)) {
                return p;
            }
        }
        return null;
    }

    private Peminjaman getPeminjamanById(long id) {
        for (int i = 0; i < peminjamanList.size(); i++) {
            Peminjaman p = peminjamanList.get(i);
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private Buku getBukuByIsbn(String isbn) {
        for (int i = 0; i < bukuList.size(); i++) {
            Buku b = bukuList.get(i);
            if (b.getIsbn().equals(isbn)) {
                return b;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        noIndukTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        namaLbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jabatanLbl = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        isbnTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ViewDetailTbl = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        judulBukuLbl = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        penerbitLbl = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        thnTerbitLbl = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        kategoriLbl = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        stockLbl = new javax.swing.JLabel();
        deleteDetailBtn = new javax.swing.JButton();
        progressLbl = new javax.swing.JLabel();
        perpanjangBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        submitBtn = new javax.swing.JButton();
        progressNoIndukLbl = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        searchBtn = new javax.swing.JButton();
        searchValTxt = new javax.swing.JTextField();
        searchColCmb = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableView = new javax.swing.JTable();
        editButton = new javax.swing.JToggleButton();
        deleteButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Kelola Peminjaman");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data"));

        jLabel1.setText("No. Induk");

        noIndukTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noIndukTxtActionPerformed(evt);
            }
        });

        jLabel2.setText("Nama");

        namaLbl.setText("jLabel3");

        jLabel4.setText("Jabatan");

        jabatanLbl.setText("jLabel5");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("List Buku"));

        jLabel6.setText("ISBN");

        isbnTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isbnTxtActionPerformed(evt);
            }
        });
        isbnTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                isbnTxtKeyTyped(evt);
            }
        });

        ViewDetailTbl.setModel(new javax.swing.table.DefaultTableModel(
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
        ViewDetailTbl.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(ViewDetailTbl);
        ViewDetailTbl.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel5.setText("Judul Buku");

        judulBukuLbl.setText("jLabel10");

        jLabel7.setText("Penerbit");

        penerbitLbl.setText("jLabel9");

        jLabel8.setText("Tahun Terbit");

        thnTerbitLbl.setText("jLabel8");

        jLabel9.setText("Kategori");

        kategoriLbl.setText("jLabel11");

        jLabel10.setText("Stock");

        stockLbl.setText("jLabel10");

        deleteDetailBtn.setText("Delete Record");
        deleteDetailBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteDetailBtnActionPerformed(evt);
            }
        });

        progressLbl.setText("jLabel11");

        perpanjangBtn.setText("Extend Duration");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(isbnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel5))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(judulBukuLbl)
                                        .addComponent(penerbitLbl)
                                        .addComponent(thnTerbitLbl))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(stockLbl)
                                        .addComponent(kategoriLbl)))
                                .addComponent(progressLbl))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(perpanjangBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteDetailBtn)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(isbnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(progressLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(judulBukuLbl))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(penerbitLbl)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(kategoriLbl))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stockLbl)
                            .addComponent(jLabel10))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(thnTerbitLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteDetailBtn)
                    .addComponent(perpanjangBtn)))
        );

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

        progressNoIndukLbl.setText("jLabel11");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jabatanLbl)
                                    .addComponent(namaLbl))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(noIndukTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(progressNoIndukLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(submitBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetBtn)))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(noIndukTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(progressNoIndukLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(namaLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jabatanLbl))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetBtn)
                    .addComponent(submitBtn)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Donasi"));

        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        searchColCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Search By");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchColCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchValTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtn)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
        jScrollPane2.setViewportView(tableView);

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(deleteButton)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        peminjamanList = new ArrayList<Peminjaman>();
        String searchCol = searchColCmb.getSelectedItem().toString();
        String searchParam = searchValTxt.getText();
        if (!searchParam.equals("")) {
            peminjamanList = peminjamanController.search(masterPeminjamanList, searchParam, searchCol);
        } else {
            peminjamanList = masterPeminjamanList;
        }
        setTableValue(true);
    }//GEN-LAST:event_searchBtnActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        if (tableView.getSelectedRow() == -1) {
            editButton.setSelected(false);
            editButton.setText("Edit Record");
            JOptionPane.showMessageDialog(null, "You must select a record before edit", "Row Did Not Selected Yet", JOptionPane.WARNING_MESSAGE);

        } else {
            peminjamanModel = peminjamanList.get(tableView.getSelectedRow());
            if (editButton.isSelected() && peminjamanModel.isActive()) {
                editButton.setText("Cancel Edit");
                editMode = true;
                if (peminjamanModel != null) {
                    detailPeminjamanList = peminjamanModel.getDetailPeminjaman();
                    setTableValue(false);
                    user = peminjamanModel.getUser();
                    noIndukTxt.setText(user.getPengguna().getNoInduk());
                    namaLbl.setText(": " + user.getPengguna().getNama());
                    jabatanLbl.setText(": " + user.getPengguna().getJabatan());
                } else {
                    editButton.setText("Edit Record");
                    reInitValiables();
                    tableView.clearSelection();
                    JOptionPane.showMessageDialog(null, "No information available in selected row", "Information is Missing", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                if (!peminjamanModel.isActive()) {
                    JOptionPane.showMessageDialog(null, "Rent status is closed\nCannot edit this data anymore", "Rent Closed", JOptionPane.ERROR_MESSAGE);
                }
                editButton.setText("Edit Record");
                tableView.clearSelection();
                reInitValiables();
                resetAllFieldInput();
                setTableValue(false);
            }
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Are you sure want to delete selected record?", "Delete Record",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            peminjamanModel = peminjamanList.get(tableView.getSelectedRow());
            if (peminjamanModel != null) {
                peminjamanModel.setActive(false);
                peminjamanModel.setUserInput(DataLibrary.getInstance().getUser().getUsername());
                editMode = true;
                for (int i = 0; i < peminjamanModel.getDetailPeminjaman().size(); i++) {
                    peminjamanModel.getDetailPeminjaman().get(i).setActive(false);
                }
                peminjamanController.setPeminjamanModel(peminjamanModel);
                peminjamanController.setEditMode(editMode);
                String message = peminjamanController.save();
                if (!message.equals("success")) {
                    JOptionPane.showMessageDialog(null, "Delete data gagal", "Delete Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    //reset table
                    tableView.clearSelection();
                    masterPeminjamanList = peminjamanController.fetchData(masterPeminjamanList, true);
                    peminjamanList = masterPeminjamanList;
                    setTableValue(true);
                    resetAllFieldInput();
                    reInitValiables();
                }
            }
        } else {
            tableView.clearSelection();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void isbnTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_isbnTxtKeyTyped
        // TODO add your handling code here:
        String isbnStr;
        if (evt.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            isbnStr = isbnTxt.getText();
        } else {
            isbnStr = isbnTxt.getText() + evt.getKeyChar();
            isbnStr = isbnStr.trim();
        }
        progressLbl.setText("Searching data");
        b = new Buku();
        b = getBukuByIsbn(isbnStr);
        if (b != null) {
            judulBukuLbl.setText(": " + b.getJudul());
            penerbitLbl.setText(": " + b.getPenerbit().getNamaPenerbit());
            thnTerbitLbl.setText(": " + b.getTahunTerbit());
            kategoriLbl.setText(": " + b.getRakBuku().getNamaJenis());
            stockLbl.setText(": " + b.getStock());
            progressLbl.setText("Data has been found");
        } else {
            progressLbl.setText("Data not found");
            resetInfoBuku();
        }
    }//GEN-LAST:event_isbnTxtKeyTyped

    private void noIndukTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noIndukTxtActionPerformed
        // TODO add your handling code here:
        String noIndukStr = noIndukTxt.getText();
        System.out.println("NO INDUK: " + noIndukStr);
        progressNoIndukLbl.setText("Searching data...");
        user = new User();
        user = getUserByNoInduk(noIndukStr);
        if (user != null) {
            namaLbl.setText(": " + user.getPengguna().getNama());
            jabatanLbl.setText(": " + user.getPengguna().getJabatan() + (user.getPengguna().getJabatan().equalsIgnoreCase("siswa") ? " kelas " + user.getPengguna().getKelas() : ""));
//            peminjamanModel.setUser(User);
            isbnTxt.requestFocus();
            progressNoIndukLbl.setText("Data has been found");
        } else {
            JOptionPane.showMessageDialog(null, "Data pengguna dengan No. Induk tersebut tidak ditemukan", "DATA NOT FOUND", 0);
            resetInfoPengguna();
            progressNoIndukLbl.setText("");
        }
    }//GEN-LAST:event_noIndukTxtActionPerformed

    private void isbnTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isbnTxtActionPerformed
        // TODO add your handling code here:
        if (b != null) {
            boolean check = true;
            for (int i = 0; i < detailPeminjamanList.size(); i++) {
                DetailPeminjaman dp = detailPeminjamanList.get(i);
                if (dp.getBuku().getId() == b.getId()) {
                    check = false;
                }
            }
            if (check) {
                progressLbl.setText("");
                DetailPeminjaman detail = new DetailPeminjaman();
                detail.setBuku(b);
                detailPeminjamanList.add(detail);
                setTableValue(false);
                resetInfoBuku();
                isbnTxt.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Data buku dengan ISBN tersebut telah ditambahkan sebelumnya.\nTidak dapat menambahkan data yang sama", "DATA REDUNDANT", 0);
                resetInfoBuku();
                isbnTxt.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data buku dengan ISBN tersebut tidak ditemukan", "DATA NOT FOUND", 0);
            resetInfoBuku();
        }
        isbnTxt.requestFocus();
    }//GEN-LAST:event_isbnTxtActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        if (editMode) {
            JOptionPane.showMessageDialog(null, "Tidak dapat melakukan reset data dalam mode edit\nBatalkan proses edit data terlebih dahulu", "Cannot reset field", JOptionPane.WARNING_MESSAGE);
        } else {
            resetAllFieldInput();
            reInitValiables();
            setTableValue(false);
        }

    }//GEN-LAST:event_resetBtnActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        // TODO add your handling code here:
        if (user != null && !detailPeminjamanList.isEmpty()) {
            boolean check = true;
            if (editMode) {
                //validasi data
                for (int i = 0; i < detailPeminjamanList.size(); i++) {
                    DetailPeminjaman dp = detailPeminjamanList.get(i);
                    String val = (String) ViewDetailTbl.getValueAt(i, 5);
                    //kalo data pengembalian ga valid, coba di trace kondisi ini
                    if (val.equals("Belum Kembali") && dp.getTanggalPengembalian() != null) {
                        check = false;
                    }
                    if (val.equals("Perpanjang")) {
                        if (dp.isPerpanjang()) {
                            check = false;
                            JOptionPane.showMessageDialog(null, "Buku dengan judul " + dp.getBuku().getJudul() + " telah melakukan perpanjangan sebelumnya\n"
                                    + "Tidat dapat memperpanjang buku lebih dari satu kali", "Rent Detail Error", JOptionPane.WARNING_MESSAGE);
                            ViewDetailTbl.setValueAt(detailPeminjamanList.get(i).getTanggalPengembalian() == null ? "Belum Kembali" : "Dikembalikan", i, 5);
                        }else if(dp.getTanggalPengembalian()!=null){
                            check = false;
                            JOptionPane.showMessageDialog(null, "Buku dengan judul " + dp.getBuku().getJudul() + " telah dikembalikan\n"
                                    + "Tidat dapat memperpanjang buku yang sudah dikembalikan", "Rent Detail Error", JOptionPane.WARNING_MESSAGE);
                            ViewDetailTbl.setValueAt(detailPeminjamanList.get(i).getTanggalPengembalian() == null ? "Belum Kembali" : "Dikembalikan", i, 5);
                        }
                    }
                }
            }
            if (check) {
                if (editMode) {
                    for (int i = 0; i < detailPeminjamanList.size(); i++) {
                        DetailPeminjaman dp = detailPeminjamanList.get(i);
                        String val = (String) ViewDetailTbl.getValueAt(i, 5);
                        if (val.equals("Dikembalikan") && dp.getTanggalPengembalian() == null) {
                            detailPeminjamanList.get(i).setKembali(true);
                        }
                        if (val.equals("Perpanjang") && !dp.isPerpanjang()) {
                            int masa = detailPeminjamanList.get(i).getMasaPinjam() * 2;
                            detailPeminjamanList.get(i).setMasaPinjam(masa);
                            detailPeminjamanList.get(i).setPerpanjang(true);
                        }
                    }
                }
                peminjamanModel.setDetailPeminjaman(detailPeminjamanList);
                peminjamanModel.setUser(user);

                peminjamanController.setPeminjamanModel(peminjamanModel);
                peminjamanController.setEditMode(editMode);
                String message = peminjamanController.save();
                if (message.equals("failed")) {
                    JOptionPane.showMessageDialog(null, (editMode ? "Update" : "Submit") + " data gagal", "Submit Data Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    resetAllFieldInput();
                    reInitValiables();
                    masterPeminjamanList = peminjamanController.fetchData(masterPeminjamanList, true);
                    peminjamanList = masterPeminjamanList;
                    setTableValue(true);
                    setTableValue(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data detail peminjaman tidak valid\nHarap lakukan pemeriksaan data kembali", "Rent Detail Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data buku atau data pengguna masih kosong\nSubmit data gagal", "Submit Data Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_submitBtnActionPerformed

    private void deleteDetailBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDetailBtnActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Are you sure want to delete selected record?", "Delete Record",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            detailPeminjamanList.remove(ViewDetailTbl.getSelectedRow());
            //reset table
            ViewDetailTbl.clearSelection();
            setTableValue(false);
        }
    }//GEN-LAST:event_deleteDetailBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ViewDetailTbl;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deleteDetailBtn;
    private javax.swing.JToggleButton editButton;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jabatanLbl;
    private javax.swing.JLabel judulBukuLbl;
    private javax.swing.JLabel kategoriLbl;
    private javax.swing.JLabel namaLbl;
    private javax.swing.JTextField noIndukTxt;
    private javax.swing.JLabel penerbitLbl;
    private javax.swing.JButton perpanjangBtn;
    private javax.swing.JLabel progressLbl;
    private javax.swing.JLabel progressNoIndukLbl;
    private javax.swing.JButton resetBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JComboBox searchColCmb;
    private javax.swing.JTextField searchValTxt;
    private javax.swing.JLabel stockLbl;
    private javax.swing.JButton submitBtn;
    private javax.swing.JTable tableView;
    private javax.swing.JLabel thnTerbitLbl;
    // End of variables declaration//GEN-END:variables
}
