/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS9;

/**
 *
 * @author nol
 */
public class TotalBayar extends Peminjam{
    @Override
    public double hitungTotalBayar(double bunga) {
        return getSaldoPinjaman() * bunga + getSaldoPinjaman();
    }
}
