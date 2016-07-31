/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.view2;

import id.sch.elib.util.DataLibrary;
import id.sch.elib.util.ImagePanel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author rizky.aditya
 */
public class MainMenu extends javax.swing.JFrame {

    private Buku inputBuku;
    private Login loginView;
//    private TableReport tableReport;
//    private ViewPenerbit viewPenerbitFrame;
    private PenggunaView penggunaView;
    private Pengaturan pengaturanView;
//    private ViewPengarang pengarang;
//    private ViewDonasi viewDonasi;
    private Peminjaman viewPeminjaman;
    private ImagePanel imagePanel;
    private Image image;

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        imagePanel = new ImagePanel(new ImageIcon("src/image/background.jpg").getImage());
        imagePanel.setLayout(new GridBagLayout());
        imagePanel.add(bukuBtn);
        imagePanel.add(userBtn);
        imagePanel.add(settingBtn);
        imagePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(imagePanel);
        try {
            File file = new File("src/image/add book.png");
            image = ImageIO.read(file);
            image = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
            bukuBtn.setIcon(new ImageIcon(image));

            file = new File("src/image/add_user.png");
            image = ImageIO.read(file);
            image = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
            userBtn.setIcon(new ImageIcon(image));

            file = new File("src/image/settings.png");
            image = ImageIO.read(file);
            image = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
            settingBtn.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            System.out.println("Cannot set image to button: " + e.getMessage());
        }

        MainMenu.this.setExtendedState(MainMenu.this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        username.setText(DataLibrary.getInstance().getUser().getUsername()
                + " (" + DataLibrary.getInstance().getUser().getUserRole().getRole().getRoleName() + ")");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bukuBtn = new javax.swing.JButton();
        userBtn = new javax.swing.JButton();
        settingBtn = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        username = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        inputPeminjamanMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Library Manager SMA Negeri 1 Baleendah - Alpha Version 2.0");

        bukuBtn.setText("Master Buku");
        bukuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bukuBtnActionPerformed(evt);
            }
        });

        userBtn.setText("Master User");
        userBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userBtnActionPerformed(evt);
            }
        });

        settingBtn.setText("Settings");
        settingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingBtnActionPerformed(evt);
            }
        });

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

        jMenu2.setText("Save Data As..");

        jMenuItem3.setText("PDF (.pdf)");
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Excel File (.xls)");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        fileMenu.add(jMenu2);
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(bukuBtn)
                .addGap(0, 0, 0)
                .addComponent(userBtn)
                .addGap(0, 0, 0)
                .addComponent(settingBtn))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bukuBtn)
                    .addComponent(userBtn)
                    .addComponent(settingBtn)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputPeminjamanMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPeminjamanMenuActionPerformed
        // TODO add your handling code here:
        viewPeminjaman = new Peminjaman();
        viewPeminjaman.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_inputPeminjamanMenuActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "This function is not supported in current version", "FUNCTION NOT SUPPORTED ERROR", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        DataLibrary.getInstance().setUser(null);
        loginView = new Login();
        loginView.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutActionPerformed

    private void inputDataBukuMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDataBukuMenuActionPerformed
        // TODO add your handling code here:
        inputBuku = new Buku();
        inputBuku.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_inputDataBukuMenuActionPerformed

    private void inputDataPenggunaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDataPenggunaMenuActionPerformed
        // TODO add your handling code here:
            penggunaView = new PenggunaView();
            penggunaView.setVisible(true);
            this.dispose();
    }//GEN-LAST:event_inputDataPenggunaMenuActionPerformed

    private void viewPenerbitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPenerbitActionPerformed
        // TODO add your handling code here:
//        if (viewPenerbitFrame == null) {
//            viewPenerbitFrame = new ViewPenerbit();
//            mainPane.add(viewPenerbitFrame);
//            viewPenerbitFrame.setVisible(true);
//        } else if (viewPenerbitFrame.isClosed()) {
//            viewPenerbitFrame = new ViewPenerbit();
//            mainPane.add(viewPenerbitFrame);
//            viewPenerbitFrame.setVisible(true);
//        } else {
//            try {
//                viewPenerbitFrame.setSelected(true);
//                if (viewPenerbitFrame.isIcon()) {
//                    viewPenerbitFrame.setIcon(false);
//                }
//            } catch (PropertyVetoException ex) {
//                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
//            
        JOptionPane.showMessageDialog(null, "This function is not supported in current version", "FUNCTION NOT SUPPORTED ERROR", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_viewPenerbitActionPerformed

    private void viewPengarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPengarangActionPerformed
        // TODO add your handling code here:
//        if (pengarang == null) {
//            pengarang = new ViewPengarang();
//            mainPane.add(pengarang);
//            pengarang.setVisible(true);
//        } else if (pengarang.isClosed()) {
//            pengarang = new ViewPengarang();
//            mainPane.add(pengarang);
//            pengarang.setVisible(true);
//        } else {
//            try {
//                pengarang.setSelected(true);
//                if (pengarang.isIcon()) {
//                    pengarang.setIcon(false);
//                }
//            } catch (PropertyVetoException ex) {
//                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        JOptionPane.showMessageDialog(null, "This function is not supported in current version", "FUNCTION NOT SUPPORTED ERROR", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_viewPengarangActionPerformed

    private void donasiMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donasiMenuActionPerformed
        // TODO add your handling code here:
//        if (viewDonasi == null) {
//            viewDonasi = new ViewDonasi();
//            mainPane.add(viewDonasi);
//            viewDonasi.setVisible(true);
//        } else if (viewDonasi.isClosed()) {
//            viewDonasi = new ViewDonasi();
//            mainPane.add(viewDonasi);
//            viewDonasi.setVisible(true);
//        } else {
//            try {
//                viewDonasi.setSelected(true);
//                if (viewDonasi.isIcon()) {
//                    viewDonasi.setIcon(false);
//                }
//            } catch (PropertyVetoException ex) {
//                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        JOptionPane.showMessageDialog(null, "This function is not supported in current version", "FUNCTION NOT SUPPORTED ERROR", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_donasiMenuActionPerformed

    private void optionMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionMenuActionPerformed
        // TODO add your handling code here:
            pengaturanView = new Pengaturan();
            pengaturanView.setVisible(true);
            this.dispose();
    }//GEN-LAST:event_optionMenuActionPerformed

    private void bukuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bukuBtnActionPerformed
        // TODO add your handling code here:
        inputDataBukuMenuActionPerformed(evt);
    }//GEN-LAST:event_bukuBtnActionPerformed

    private void userBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userBtnActionPerformed
        // TODO add your handling code here:
        inputDataPenggunaMenu.doClick();
    }//GEN-LAST:event_userBtnActionPerformed

    private void settingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingBtnActionPerformed
        // TODO add your handling code here:
        optionMenu.doClick();
    }//GEN-LAST:event_settingBtnActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
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
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainMenu().setVisible(true);
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
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem logout;
    private javax.swing.JMenu masterDataMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem optionMenu;
    private javax.swing.JMenuItem reportAnggotaMenu;
    private javax.swing.JMenuItem reportBukuMenu;
    private javax.swing.JMenuItem reportDendaMenu;
    private javax.swing.JMenu reportMenu;
    private javax.swing.JMenuItem reportPeminjamanMenu;
    private javax.swing.JMenuItem reportPengembalianMenu;
    private javax.swing.JButton settingBtn;
    private javax.swing.JButton userBtn;
    private javax.swing.JMenuItem username;
    private javax.swing.JMenuItem viewPenerbit;
    private javax.swing.JMenuItem viewPengarang;
    // End of variables declaration//GEN-END:variables
}