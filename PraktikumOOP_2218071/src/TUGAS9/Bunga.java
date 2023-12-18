/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS9;

/**
 *
 * @author nol
 */
public class Bunga extends Peminjam{
    @Override 
    public double hitungTotalBayar() {
        return getSaldoPinjaman() * Bunga();
    }
}
