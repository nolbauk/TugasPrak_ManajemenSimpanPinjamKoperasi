/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TUGAS10;

//masukkan semua import di bawah
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nol
 */
public class GUI_DataAnggota extends javax.swing.JFrame {

    /**
     * Creates new form GUI_DataAnggota
     */
    public GUI_DataAnggota() {
        initComponents();
        txttenggat.setText("31 Desember 2023");
        txttenggat.setEnabled(false);
        //masukkan method tanpil() disini
        tampil();
        tampil_ang();
    }

    public void batal() {
        txtnama.setText("");
        txtalamat.setText("");
        txtss.setText("");
        txtsp.setText("");
        btgroupkelamin.clearSelection();
    }
    
    //masukkan conection (public Connection conn;)
    public Connection conn;
    //masukkan attribut (String nim1, nama1, jk1, prodi1, ang1, alamat1;)
    String id, nama, alamat, jk, ss, sp, tenggat;
    //masukkan method itempilih()
    public void itempilih() {
        cmbang.setSelectedItem(id);
        txtnama.setText(nama);
        txtalamat.setText(alamat);
        txtss.setText(ss);
        txtsp.setText(sp);
        txttenggat.setText(tenggat);
        if (jk.equalsIgnoreCase("Laki-laki")) {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }
    }

    //masukkan method koneksi()
    public void koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/tugaspraktikumoop?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_DataAnggota.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_DataAnggota.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_DataAnggota.class.getName()).log(Level.SEVERE, null, es);
        }
    }

    //masukkan method tampil()
    public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("ID");
        tabelhead.addColumn("NAMA");
        tabelhead.addColumn("ALAMAT");
        tabelhead.addColumn("JENIS KELAMIN");
        tabelhead.addColumn("SALDO SIMPANAN");
        tabelhead.addColumn("SALDO PINJAMAN");
        tabelhead.addColumn("TENGGAT BAYAR");
        try {
            koneksi();
            String sql = "SELECT * FROM gui_dataanggota";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6),res.getString(7),});
            }
            tabdata.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
    
    //masukka method tampil_mhs() dibawah sini
    public void tampil_ang() {
        try {
            koneksi();
            String sql = "SELECT ID FROM gui_anggota order by ID asc";
            Statement stt = conn.createStatement();
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                Object[] ob = new Object[3];
                ob[0] = res.getString(1);
                cmbang.addItem((String) ob[0]);
            }
            res.close();
            stt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //masukkan method delete()
    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM gui_dataanggota WHERE ID = '" + cmbang.getSelectedItem() + "'";
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus");
            }
        }
        refresh();
    }

    //masukkan method cari()
    public void cari() {
        try {
            try ( Statement statement = conn.createStatement()) {
                String sql = "SELECT * FROM gui_dataanggota WHERE `ID`  LIKE '%" + txtcari.getText() + "%'";
                ResultSet rs = statement.executeQuery(sql); //menampilkan data dari sql query
                if (rs.next()) // .next() = scanner method
                {
                    cmbang.setSelectedItem(rs.getString(1));
                    txtnama.setText(rs.getString(2));
                    txtalamat.setText(rs.getString(3));
                    String jk = rs.getString(4);
                    if (jk.equalsIgnoreCase("Laki-laku")) {
                        male.setSelected(true);
                    } else {
                        female.setSelected(true);
                    }
                    txtss.setText(rs.getString(5));
                    txtsp.setText(rs.getString(6));
                    txttenggat.setText(rs.getString(7));
                } else {
                    JOptionPane.showMessageDialog(null, "Data yang Anda cari tidak ada");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error." + ex);
        }
    }

    //masukkan method update()
    public void update() {
        String ID = (String) cmbang.getSelectedItem();
        String Nama = txtnama.getText();
        String Alamat = txtalamat.getText();
        String jk;
        if (male.isSelected()) {
            jk = "Laki-laki";
        } else {
            jk = "Perempuan";
        }
        String simpan = txtss.getText();
        String pinjam = txtsp.getText();
        String Tenggat = txttenggat.getText();
        String IDlama = id;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE gui_dataanggota SET ID = '" + ID + "'," + "Nama = '" + Nama + "',"
                    + " Alamat = '" + Alamat + "'" + ", `Jenis Kelamin` = '" + jk + "', `Saldo Simpanan` = '" + simpan + "', `Saldo Pinjaman` ='"
                    + pinjam +  "', `Tenggat Bayar` ='" + Tenggat + "' WHERE ID = '" + IDlama + "'");
            statement.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Update Data Anggota Berhasil!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        refresh();
    }

    //masukkan method refresh()
    public void refresh() {
        new GUI_DataAnggota().setVisible(true);
        this.setVisible(false);
    }

    //masukkan method insert()
    public void insert() {
        String ID = (String) cmbang.getSelectedItem();
        String Nama = txtnama.getText();
        String Alamat = txtalamat.getText();
        String jkk;
        if (male.isSelected()) {
            jkk = "Laki-laki";
        } else {
            jkk = "Perempuan";
        }
        String Simpan = txtss.getText();
        String Pinjam = txtsp.getText();
        String Tenggat = txttenggat.getText();
        try {
            koneksi();
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO gui_dataanggota (ID, Nama, Alamat, `Jenis Kelamin`, `Saldo Simpanan`, `Saldo Pinjaman`, `Tenggat Bayar`) " +
                 "VALUES('" + ID + "','" + Nama + "','" + Alamat + "','" + jkk + "','" + Simpan + "','"+ Pinjam + "','" + Tenggat + "')";
            statement.executeUpdate(sql);
            statement.close();
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Anggota!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
            e.printStackTrace();
        }
        refresh();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgroupkelamin = new javax.swing.ButtonGroup();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        txtalamat = new javax.swing.JTextField();
        txtss = new javax.swing.JTextField();
        txtsp = new javax.swing.JTextField();
        txttenggat = new javax.swing.JTextField();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        btsimpan = new javax.swing.JButton();
        btclose = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabdata = new javax.swing.JTable();
        bthapus = new javax.swing.JButton();
        btbatal = new javax.swing.JButton();
        btupdate = new javax.swing.JButton();
        btcari = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        btprevious = new javax.swing.JButton();
        btang = new javax.swing.JButton();
        cmbang = new javax.swing.JComboBox<>();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setText("DATA ANGGOTA KOPERASI");

        jLabel3.setText("Nama");

        jLabel4.setText("Alamat");

        jLabel5.setText("Jenis Kelamin");

        jLabel6.setText("Saldo Simpanan");

        jLabel7.setText("Saldo Pinjaman");

        jLabel8.setText("Tenggat Bayar");

        btgroupkelamin.add(male);
        male.setText("Laki-laki");
        male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleActionPerformed(evt);
            }
        });

        btgroupkelamin.add(female);
        female.setText("Perempuan");
        female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleActionPerformed(evt);
            }
        });

        btsimpan.setText("Simpan");
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
            }
        });

        btclose.setText("Close");
        btclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcloseActionPerformed(evt);
            }
        });

        tabdata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "Alamat", "Jenis Kelamin", "Saldo Simpanan", "Saldo Pinjaman", "Tenggat Bayar"
            }
        ));
        tabdata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabdataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabdata);

        bthapus.setText("Hapus");
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });

        btbatal.setText("Batal");
        btbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbatalActionPerformed(evt);
            }
        });

        btupdate.setText("Update");
        btupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btupdateActionPerformed(evt);
            }
        });

        btcari.setText("Cari");
        btcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcariActionPerformed(evt);
            }
        });

        btprevious.setText("Previous Form");
        btprevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btpreviousActionPerformed(evt);
            }
        });

        btang.setText("ID");
        btang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btangActionPerformed(evt);
            }
        });

        cmbang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ID~" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(btang))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(female)
                    .addComponent(male)
                    .addComponent(txtnama)
                    .addComponent(txtalamat)
                    .addComponent(txtss)
                    .addComponent(txtsp)
                    .addComponent(txttenggat, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(cmbang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btsimpan)
                        .addGap(18, 18, 18)
                        .addComponent(bthapus)
                        .addGap(18, 18, 18)
                        .addComponent(btbatal)
                        .addGap(18, 18, 18)
                        .addComponent(btclose)
                        .addGap(18, 18, 18)
                        .addComponent(btupdate)
                        .addGap(18, 18, 18)
                        .addComponent(btprevious)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btcari))
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(540, 540, 540)
                .addComponent(jLabel1)
                .addContainerGap(616, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btang)
                            .addComponent(cmbang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtalamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(male))
                        .addGap(3, 3, 3)
                        .addComponent(female)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenggat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(btsimpan)
                    .addComponent(btclose)
                    .addComponent(bthapus)
                    .addComponent(btbatal)
                    .addComponent(btupdate)
                    .addComponent(btcari)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btprevious))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maleActionPerformed

    private void femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_femaleActionPerformed

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btsimpanActionPerformed

    private void btcloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btcloseActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_bthapusActionPerformed

    private void btbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatalActionPerformed
        // TODO add your handling code here:
        batal();
    }//GEN-LAST:event_btbatalActionPerformed

    private void btupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btupdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btupdateActionPerformed

    private void btcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcariActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_btcariActionPerformed

    private void tabdataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabdataMouseClicked
        // TODO add your handling code here:
        //masukkan source code onclick pada tabel
        int tabel = tabdata.getSelectedRow();
        id = tabdata.getValueAt(tabel, 0).toString();
        nama = tabdata.getValueAt(tabel, 1).toString();
        alamat = tabdata.getValueAt(tabel, 2).toString();
        jk = tabdata.getValueAt(tabel, 3).toString();
        ss = tabdata.getValueAt(tabel, 4).toString();
        sp = tabdata.getValueAt(tabel, 5).toString();
        tenggat = tabdata.getValueAt(tabel, 6).toString();
        itempilih();
    }//GEN-LAST:event_tabdataMouseClicked

    private void btpreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btpreviousActionPerformed
        // TODO add your handling code here:
        new GUI_Anggota().setVisible(true);
    }//GEN-LAST:event_btpreviousActionPerformed

    private void btangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btangActionPerformed
        // TODO add your handling code here:
        new GUI_Anggota().setVisible(true);
    }//GEN-LAST:event_btangActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_DataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_DataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_DataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_DataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_DataAnggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btang;
    private javax.swing.JButton btbatal;
    private javax.swing.JButton btcari;
    private javax.swing.JButton btclose;
    private javax.swing.ButtonGroup btgroupkelamin;
    private javax.swing.JButton bthapus;
    private javax.swing.JButton btprevious;
    private javax.swing.JButton btsimpan;
    private javax.swing.JButton btupdate;
    private javax.swing.JComboBox<String> cmbang;
    private javax.swing.JRadioButton female;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton male;
    private javax.swing.JTable tabdata;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtsp;
    private javax.swing.JTextField txtss;
    private javax.swing.JTextField txttenggat;
    // End of variables declaration//GEN-END:variables
}
