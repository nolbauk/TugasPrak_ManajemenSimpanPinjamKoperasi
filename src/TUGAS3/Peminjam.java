/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS3;

/**
 *
 * @author nol
 */
public class Peminjam extends Koperasi{
    double bunga;
    public Peminjam() {
        this.bunga = 0.1;
    }
    double hitungTotalBayar() {
        return saldoPinjaman + saldoPinjaman * bunga;
    }
}
