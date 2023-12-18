/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TUGAS10;

//import
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
public class GUI_Anggota extends javax.swing.JFrame {

    /**
     * Creates new form GUI_Anggota
     */
    public GUI_Anggota() {
        initComponents();
        tampil();
    }
    
    public void batal() {
        txtid.setText("");
        txtnama.setText("");
        txtumur.setText("");
        txtjob.setText("");
        txttgl.setText("");
        txtno.setText("");
        txtemail.setText("");
    }

    //masukkan conection (public Connection conn;)
    public Connection conn;
    //masukkan attribut (String nim1, nama1, jk1, prodi1, ang1, alamat1;)
    String id, nama, job, email, no, umur, tgl;
    //masukkan method itempilih()
    public void itempilih() {
        txtid.setText(id);
        txtnama.setText(nama);
        txtumur.setText(umur);
        txtjob.setText(job);
        txttgl.setText(tgl);
        txtno.setText(no);
        txtemail.setText(email);
    }

    //masukkan method koneksi()
    public void koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/tugaspraktikumoop?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_Anggota.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_Anggota.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_Anggota.class.getName()).log(Level.SEVERE, null, es);
        }
    }

    //masukkan method tampil()
    public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("ID");
        tabelhead.addColumn("NAMA");
        tabelhead.addColumn("UMUR");
        tabelhead.addColumn("PEKERJAAN");
        tabelhead.addColumn("TANGGAL GABUNG");
        tabelhead.addColumn("NO. TELP");
        tabelhead.addColumn("EMAIL");
        try {
            koneksi();
            String sql = "SELECT * FROM gui_anggota";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7),});
            }
            tabdata.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }

    //masukkan method delete()
    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM gui_anggota WHERE ID = '" + txtid.getText() + "'";
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
                String sql = "SELECT * FROM gui_anggota WHERE `ID`  LIKE '%" + txtcari.getText() + "%'";
                ResultSet rs = statement.executeQuery(sql); //menampilkan data dari sql query
                if (rs.next()) // .next() = scanner method
                {
                    txtid.setText(rs.getString(1));
                    txtnama.setText(rs.getString(2));
                    txtumur.setText(rs.getString(3));
                    txtjob.setText(rs.getString(4));
                    txttgl.setText(rs.getString(5));
                    txtno.setText(rs.getString(6));
                    txtemail.setText(rs.getString(7));
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
        String ID = txtid.getText();
        String Nama = txtnama.getText();
        String Umur = txtumur.getText();
        String Pekerjaan = txtjob.getText();
        String Tgl = txttgl.getText();
        String NoTelp = txtno.getText();
        String Email = txtemail.getText();
        String IDlama = id;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE gui_anggota SET ID = '" + ID + "'," + "Nama = '" + Nama + "', "
                    + "Umur= '" + Umur + "'" + ", Pekerjaan = '" + Pekerjaan + "',`Tanggal Gabung` = '" + Tgl + "', `No. Telp` = '" + NoTelp + "', Email = '"
                    + Email + "' WHERE ID = '" + IDlama + "'");
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
        new GUI_Anggota().setVisible(true);
        this.setVisible(false);
    }

    //masukkan method insert()
    public void insert() {
        String ID = txtid.getText();
        String Nama = txtnama.getText();
        String Umur = txtumur.getText();
        String Pekerjaan = txtjob.getText();
        String Tgl = txttgl.getText();
        String NoTelp = txtno.getText();
        String Email = txtemail.getText();
        try {
            koneksi();
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO gui_anggota (ID, Nama, Umur, Pekerjaan, `Tanggal Gabung`, `No. Telp`, Email) " +
                         "VALUES('" + ID + "','" + Nama + "','" + Umur + "','" + Pekerjaan + "','" + Tgl + "','"+ NoTelp + "','" + Email + "')";
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        txtumur = new javax.swing.JTextField();
        txtjob = new javax.swing.JTextField();
        txttgl = new javax.swing.JTextField();
        txtno = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        btsimpan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabdata = new javax.swing.JTable();
        bthapus = new javax.swing.JButton();
        btbatal = new javax.swing.JButton();
        btclose = new javax.swing.JButton();
        btupdate = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        btcari = new javax.swing.JButton();
        btnext = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID ");

        jLabel2.setText("Nama");

        jLabel3.setText("Umur");

        jLabel4.setText("Pekerjaan");

        jLabel5.setText("Tanggal Gabung");

        jLabel6.setText("No. Telp");

        jLabel7.setText("Email");

        txtno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnoActionPerformed(evt);
            }
        });

        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });

        btsimpan.setText("Simpan");
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
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
                "ID", "Nama", "Umur", "Pekerjaan", "Tanggal Gabung", "No. Telp", "Email"
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

        btclose.setText("Close");
        btclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcloseActionPerformed(evt);
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

        btnext.setText("Next Form");
        btnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(79, 79, 79)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(81, 81, 81)
                            .addComponent(txtumur, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(60, 60, 60)
                            .addComponent(txtjob, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(82, 82, 82)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(24, 24, 24)
                            .addComponent(txttgl, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(67, 67, 67)
                            .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btsimpan)
                        .addGap(18, 18, 18)
                        .addComponent(bthapus)
                        .addGap(18, 18, 18)
                        .addComponent(btbatal)
                        .addGap(18, 18, 18)
                        .addComponent(btupdate)
                        .addGap(18, 18, 18)
                        .addComponent(btclose)
                        .addGap(18, 18, 18)
                        .addComponent(btnext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btcari)
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtumur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtjob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txttgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btsimpan)
                    .addComponent(bthapus)
                    .addComponent(btbatal)
                    .addComponent(btclose)
                    .addComponent(btupdate)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcari)
                    .addComponent(btnext))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnoActionPerformed

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
        // TODO add your handling code here:
       insert();
    }//GEN-LAST:event_btsimpanActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_bthapusActionPerformed

    private void btbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatalActionPerformed
        // TODO add your handling code here:
        batal();    
    }//GEN-LAST:event_btbatalActionPerformed

    private void btcloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btcloseActionPerformed

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
        umur = tabdata.getValueAt(tabel, 2).toString();
        job = tabdata.getValueAt(tabel, 3).toString();
        tgl = tabdata.getValueAt(tabel, 4).toString();
        no = tabdata.getValueAt(tabel, 5).toString();
        email = tabdata.getValueAt(tabel, 6).toString();
        itempilih();
    }//GEN-LAST:event_tabdataMouseClicked

    private void btnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnextActionPerformed
        // TODO add your handling code here:
        new GUI_DataAnggota().setVisible(true);
    }//GEN-LAST:event_btnextActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_Anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Anggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbatal;
    private javax.swing.JButton btcari;
    private javax.swing.JButton btclose;
    private javax.swing.JButton bthapus;
    private javax.swing.JButton btnext;
    private javax.swing.JButton btsimpan;
    private javax.swing.JButton btupdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabdata;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtjob;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtno;
    private javax.swing.JTextField txttgl;
    private javax.swing.JTextField txtumur;
    // End of variables declaration//GEN-END:variables
}
