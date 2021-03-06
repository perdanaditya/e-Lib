/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.view;

import id.sch.elib.util.DataLibrary;
import java.awt.Component;
import java.awt.Image;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author RAP
 */
public class MainMenu extends javax.swing.JFrame {

    private InputBuku inputBuku;
    private LoginView loginView;
    private TableReport tableReport;
    private ViewPenerbit viewPenerbitFrame;
    private PenggunaView penggunaView;
    private Pengaturan pengaturanView;
    private ViewPengarang pengarang;
    private ViewDonasi viewDonasi;
    private ViewPeminjaman viewPeminjaman;

    private boolean stop;

    public MainMenu() {
        //only for development purpose (bypass login)
//        DataLibrary.getInstance().setUsername("admin");
//        DataLibrary.getInstance().setRole("Administrator");

        initComponents();
        stop = false;
        MainMenu.this.setExtendedState(MainMenu.this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        //set icon to menu button
        logo.setIcon(imageLoad(logo, "src//image//logo_sman1be (1).png"));
        penggunaBtn.setIcon(imageLoad(penggunaBtn, "src//image//add_user.png"));
        bukuBtn.setIcon(imageLoad(bukuBtn, "src//image//openbooklogo.png"));
        peminjamanBtn.setIcon(imageLoad(peminjamanBtn, "src//image//add book.png"));
        profileBtn.setIcon(imageLoad(profileBtn, "src//image//user.png"));
        logoutBtn.setIcon(imageLoad(logoutBtn, "src//image//logout-icon.png"));

        timeRender();
        username.setText(DataLibrary.getInstance().getUser().getUsername() + " (" + DataLibrary.getInstance().getUser().getUserRole().getRole().getRoleName() + ")");
        userLabel.setText(DataLibrary.getInstance().getUser().getUsername() + " (" + DataLibrary.getInstance().getUser().getUserRole().getRole().getRoleName() + ")");

//        viewPeminjaman = new ViewPeminjaman();
//        mainPane.add(viewPeminjaman);
//        viewPeminjaman.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        mainPane = new javax.swing.JDesktopPane();
        menuPanel = new javax.swing.JPanel();
        penggunaBtn = new javax.swing.JButton();
        bukuBtn = new javax.swing.JButton();
        peminjamanBtn = new javax.swing.JButton();
        profileBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        username = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        inputPeminjamanMenu = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        logout = new javax.swing.JMenuItem();
        reportMenu = new javax.swing.JMenu();
        reportPeminjamanMenu = new javax.swing.JMenuItem();
        reportPengembalianMenu = new javax.swing.JMenuItem();
        reportDendaMenu = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        reportAnggotaMenu = new javax.swing.JMenuItem();
        reportBukuMenu = new javax.swing.JMenuItem();
        masterDataMenu = new javax.swing.JMenu();
        inputDataBukuMenu = new javax.swing.JMenuItem();
        inputDataPenggunaMenu = new javax.swing.JMenuItem();
        viewPenerbit = new javax.swing.JMenuItem();
        viewPengarang = new javax.swing.JMenuItem();
        donasiMenu = new javax.swing.JMenuItem();
        optionMenu = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Library Manager SMA Negeri 1 Baleendah - Alpha Version 0.2.1");

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        mainPane.setBackground(new java.awt.Color(0, 204, 204));

        menuPanel.setBackground(new java.awt.Color(153, 255, 153));

        penggunaBtn.setBackground(new java.awt.Color(153, 255, 153));
        penggunaBtn.setText("Pengguna");
        penggunaBtn.setToolTipText("Pengguna");
        penggunaBtn.setBorder(null);
        penggunaBtn.setContentAreaFilled(false);
        penggunaBtn.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        penggunaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penggunaBtnActionPerformed(evt);
            }
        });

        bukuBtn.setBackground(new java.awt.Color(153, 255, 153));
        bukuBtn.setText("Buku");
        bukuBtn.setToolTipText("Buku");
        bukuBtn.setBorder(null);
        bukuBtn.setContentAreaFilled(false);
        bukuBtn.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bukuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bukuBtnActionPerformed(evt);
            }
        });

        peminjamanBtn.setBackground(new java.awt.Color(153, 255, 153));
        peminjamanBtn.setText("Peminjaman");
        peminjamanBtn.setToolTipText("Peminjaman");
        peminjamanBtn.setBorder(null);
        peminjamanBtn.setContentAreaFilled(false);
        peminjamanBtn.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        peminjamanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peminjamanBtnActionPerformed(evt);
            }
        });

        profileBtn.setBackground(new java.awt.Color(153, 255, 153));
        profileBtn.setText("Profile");
        profileBtn.setToolTipText("Profile");
        profileBtn.setBorder(null);
        profileBtn.setContentAreaFilled(false);
        profileBtn.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });

        logoutBtn.setBackground(new java.awt.Color(153, 255, 153));
        logoutBtn.setText("Logout");
        logoutBtn.setToolTipText("Logout");
        logoutBtn.setBorder(null);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 255, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        userLabel.setText("jLabel1");

        timeLabel.setText("jLabel2");

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(penggunaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bukuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(peminjamanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(timeLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(penggunaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bukuBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(peminjamanBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(profileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userLabel)
                .addGap(18, 18, 18)
                .addComponent(timeLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setText("APLIKASI PERPUSTAKAAN SEKOLAH LIB DIGIT");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
            .addComponent(menuPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
        );

        fileMenu.setText("File");

        username.setText("username");
        fileMenu.add(username);
        fileMenu.add(jSeparator1);

        inputPeminjamanMenu.setText("Kelola Peminjaman");
        inputPeminjamanMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPeminjamanMenuActionPerformed(evt);
            }
        });
        fileMenu.add(inputPeminjamanMenu);
        fileMenu.add(jSeparator2);

        logout.setText("Log Out");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        fileMenu.add(logout);

        menuBar.add(fileMenu);

        reportMenu.setText("Report");

        reportPeminjamanMenu.setText("Data Peminjaman");
        reportMenu.add(reportPeminjamanMenu);

        reportPengembalianMenu.setText("Data Pengembalian");
        reportMenu.add(reportPengembalianMenu);

        reportDendaMenu.setText("Data Denda");
        reportMenu.add(reportDendaMenu);
        reportMenu.add(jSeparator3);

        reportAnggotaMenu.setText("Data Anggota");
        reportMenu.add(reportAnggotaMenu);

        reportBukuMenu.setText("Data Buku");
        reportMenu.add(reportBukuMenu);

        menuBar.add(reportMenu);

        masterDataMenu.setText("Master Data");

        inputDataBukuMenu.setText("Data Buku");
        inputDataBukuMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDataBukuMenuActionPerformed(evt);
            }
        });
        masterDataMenu.add(inputDataBukuMenu);

        inputDataPenggunaMenu.setText("Data Pengguna");
        inputDataPenggunaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDataPenggunaMenuActionPerformed(evt);
            }
        });
        masterDataMenu.add(inputDataPenggunaMenu);

        viewPenerbit.setText("Data Penerbit");
        viewPenerbit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPenerbitActionPerformed(evt);
            }
        });
        masterDataMenu.add(viewPenerbit);

        viewPengarang.setText("Pengarang");
        viewPengarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPengarangActionPerformed(evt);
            }
        });
        masterDataMenu.add(viewPengarang);

        donasiMenu.setText("Donasi Buku");
        donasiMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donasiMenuActionPerformed(evt);
            }
        });
        masterDataMenu.add(donasiMenu);

        optionMenu.setText("Pengaturan");
        optionMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionMenuActionPerformed(evt);
            }
        });
        masterDataMenu.add(optionMenu);

        menuBar.add(masterDataMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void timeRender() {
        new Thread() {
            @Override
            public void run() {
                try {
                    while (!stop) {
                        timeLabel.setText(DataLibrary.getInstance().getCurrentTimeByPattern("EEEEE, dd MMMMM yyyy HH:mm"));
                        Thread.sleep(60000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("TIME THREAD ERROR: " + e.getMessage());
                }
            }

        }.start();
    }

    public ImageIcon imageLoad(Component component, String source) {
        File file = new File(source);
        ImageIcon icon = new ImageIcon();
        if (file.exists()) {
            try {
                Image img = ImageIO.read(file);
                img = img.getScaledInstance(component.getWidth(), component.getHeight(), Image.SCALE_SMOOTH);
                icon = new ImageIcon(img);
//                logo.setIcon(icon);
            } catch (Exception e) {
                System.out.println("IMAGE-LOAD-ERROR: " + e.getMessage());
            }
        } else {
            System.out.println("IMAGE-LOAD-ERROR: file in " + file.getAbsolutePath() + " is missing");
        }
        return icon;
    }

    private void inputDataBukuMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDataBukuMenuActionPerformed
        try {
            // TODO add your handling code here:
            if (inputBuku == null) {
                inputBuku = new InputBuku();
                mainPane.add(inputBuku);
                inputBuku.setMaximum(true);
                inputBuku.setVisible(true);
            } else if (inputBuku.isClosed()) {
                inputBuku = new InputBuku();
                mainPane.add(inputBuku);
                inputBuku.setMaximum(true);
                inputBuku.setVisible(true);
            } else {
                try {
                    inputBuku.setSelected(true);
                    if (inputBuku.isIcon()) {
                        inputBuku.setIcon(false);
                    }
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (PropertyVetoException ex) {
            System.out.println("VIEW-BUKU-ERROR: " + ex.getMessage());
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_inputDataBukuMenuActionPerformed

    private void viewPenerbitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPenerbitActionPerformed
        try {
            // TODO add your handling code here:
            if (viewPenerbitFrame == null) {
                viewPenerbitFrame = new ViewPenerbit();
                mainPane.add(viewPenerbitFrame);
                viewPenerbitFrame.setMaximum(true);
                viewPenerbitFrame.setVisible(true);
            } else if (viewPenerbitFrame.isClosed()) {
                viewPenerbitFrame = new ViewPenerbit();
                mainPane.add(viewPenerbitFrame);
                viewPenerbitFrame.setMaximum(true);
                viewPenerbitFrame.setVisible(true);
            } else {
                try {
                    viewPenerbitFrame.setSelected(true);
                    if (viewPenerbitFrame.isIcon()) {
                        viewPenerbitFrame.setIcon(false);
                    }
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (PropertyVetoException ex) {
            System.out.println("INPU-PENERBIT-ERROR: " + ex.getMessage());
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_viewPenerbitActionPerformed

    private void inputDataPenggunaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDataPenggunaMenuActionPerformed
        // TODO add your handling code here:
        if (penggunaView == null) {
            try {
                penggunaView = new PenggunaView();
                mainPane.add(penggunaView);
                penggunaView.setMaximum(true);
                penggunaView.setVisible(true);
            } catch (PropertyVetoException ex) {
                System.out.println("VIEW-PENGGUNA-ERROR: " + ex.getMessage());
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (penggunaView.isClosed()) {
            try {
                penggunaView = new PenggunaView();
                mainPane.add(penggunaView);
                penggunaView.setMaximum(true);
                penggunaView.setVisible(true);
            } catch (PropertyVetoException ex) {
                System.out.println("VIEW-PENGGUNA-ERROR: " + ex.getMessage());
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                penggunaView.setSelected(true);
                if (penggunaView.isIcon()) {
                    penggunaView.setIcon(false);
                }
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_inputDataPenggunaMenuActionPerformed

    private void optionMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionMenuActionPerformed
        // TODO add your handling code here:
        if (pengaturanView == null) {
            try {
                pengaturanView = new Pengaturan();
                mainPane.add(pengaturanView);
                pengaturanView.setMaximum(true);
                pengaturanView.setVisible(true);
            } catch (PropertyVetoException ex) {
                System.out.println("VIEW-PENGATURAN-ERROR: " + ex.getMessage());
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (pengaturanView.isClosed()) {
            try {
                pengaturanView = new Pengaturan();
                mainPane.add(pengaturanView);
                pengaturanView.setMaximum(true);
                pengaturanView.setVisible(true);
            } catch (PropertyVetoException ex) {
                System.out.println("VIEW-PENGATURAN-ERROR: " + ex.getMessage());
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                pengaturanView.setSelected(true);
                if (pengaturanView.isIcon()) {
                    pengaturanView.setIcon(false);
                }
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_optionMenuActionPerformed

    private void viewPengarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPengarangActionPerformed
        // TODO add your handling code here:
        if (pengarang == null) {
            try {
                pengarang = new ViewPengarang();
                mainPane.add(pengarang);
                pengarang.setMaximum(true);
                pengarang.setVisible(true);
            } catch (PropertyVetoException ex) {
                System.out.println("VIEW-PENGARANG-ERROR: " + ex.getMessage());
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (pengarang.isClosed()) {
            try {
                pengarang = new ViewPengarang();
                mainPane.add(pengarang);
                pengarang.setMaximum(true);
                pengarang.setVisible(true);
            } catch (PropertyVetoException ex) {
                System.out.println("VIEW-PENGARANG-ERROR: " + ex.getMessage());
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                pengarang.setSelected(true);
                if (pengarang.isIcon()) {
                    pengarang.setIcon(false);
                }
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_viewPengarangActionPerformed

    private void donasiMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donasiMenuActionPerformed
        // TODO add your handling code here:
        if (viewDonasi == null) {
            try {
                viewDonasi = new ViewDonasi();
                mainPane.add(viewDonasi);
                viewDonasi.setMaximum(true);
                viewDonasi.setVisible(true);
            } catch (PropertyVetoException ex) {
                System.out.println("VIEW-DONASI-ERROR: " + ex.getMessage());
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (viewDonasi.isClosed()) {
            try {
                viewDonasi = new ViewDonasi();
                mainPane.add(viewDonasi);
                viewDonasi.setMaximum(true);
                viewDonasi.setVisible(true);
            } catch (PropertyVetoException ex) {
                System.out.println("VIEW-DONASI-ERROR: " + ex.getMessage());
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                viewDonasi.setSelected(true);
                if (viewDonasi.isIcon()) {
                    viewDonasi.setIcon(false);
                }
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_donasiMenuActionPerformed

    private void inputPeminjamanMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPeminjamanMenuActionPerformed
        // TODO add your handling code here:
        if (viewPeminjaman == null) {
            try {
                viewPeminjaman = new ViewPeminjaman();
                mainPane.add(viewPeminjaman);
                viewPeminjaman.setMaximum(true);
                viewPeminjaman.setVisible(true);
            } catch (PropertyVetoException ex) {
                System.out.println("VIEW-PEMINJAMAN-ERROR: " + ex.getMessage());
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (viewPeminjaman.isClosed()) {
            try {
                viewPeminjaman = new ViewPeminjaman();
                mainPane.add(viewPeminjaman);
                viewPeminjaman.setMaximum(true);
                viewPeminjaman.setVisible(true);
            } catch (PropertyVetoException ex) {
                System.out.println("VIEW-PEMINJAMAN-ERROR: " + ex.getMessage());
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                viewPeminjaman.setSelected(true);
                if (viewPeminjaman.isIcon()) {
                    viewPeminjaman.setIcon(false);
                }
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_inputPeminjamanMenuActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        stop = true;
        DataLibrary.getInstance().setUser(null);
        loginView = new LoginView();
        loginView.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutActionPerformed

    private void penggunaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penggunaBtnActionPerformed
        // TODO add your handling code here:
        inputDataPenggunaMenu.doClick();
    }//GEN-LAST:event_penggunaBtnActionPerformed

    private void bukuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bukuBtnActionPerformed
        // TODO add your handling code here:
        inputDataBukuMenu.doClick();
    }//GEN-LAST:event_bukuBtnActionPerformed

    private void peminjamanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_peminjamanBtnActionPerformed
        // TODO add your handling code here:
        inputPeminjamanMenu.doClick();
    }//GEN-LAST:event_peminjamanBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        logout.doClick();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "This function is not supported by current version", "Not Supported", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_profileBtnActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
    //</editor-fold>

    /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainMenu().setVisible(true);
////                f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bukuBtn;
    private javax.swing.JMenuItem donasiMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem inputDataBukuMenu;
    private javax.swing.JMenuItem inputDataPenggunaMenu;
    private javax.swing.JMenuItem inputPeminjamanMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel logo;
    private javax.swing.JMenuItem logout;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JDesktopPane mainPane;
    private javax.swing.JMenu masterDataMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JMenuItem optionMenu;
    private javax.swing.JButton peminjamanBtn;
    private javax.swing.JButton penggunaBtn;
    private javax.swing.JButton profileBtn;
    private javax.swing.JMenuItem reportAnggotaMenu;
    private javax.swing.JMenuItem reportBukuMenu;
    private javax.swing.JMenuItem reportDendaMenu;
    private javax.swing.JMenu reportMenu;
    private javax.swing.JMenuItem reportPeminjamanMenu;
    private javax.swing.JMenuItem reportPengembalianMenu;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel userLabel;
    private javax.swing.JMenuItem username;
    private javax.swing.JMenuItem viewPenerbit;
    private javax.swing.JMenuItem viewPengarang;
    // End of variables declaration//GEN-END:variables
}
