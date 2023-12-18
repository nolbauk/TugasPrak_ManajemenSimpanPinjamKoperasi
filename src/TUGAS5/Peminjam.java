/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS5;

/**
 *
 * @author nol
 */
public class Peminjam extends Koperasi{
    //atribut
    private double bunga;
    
    //method
    //konstruktor
    public Peminjam() {
        this.bunga = 0.1;
    }
    
    //setter
    public void setBunga(double bunga) {
        this.bunga = bunga;
    }
    
    //getter
    public double getBunga() {
        return bunga;
    }
 
    //method untuk menghitung total bunga setelah di override
    @Override 
    public double hitungTotalBayar() {
        return getSaldoPinjaman() * bunga;
    }
    
    //method untuk menghitung total bayar setelah di override
    @Override
    public double hitungTotalBayar(double bunga) {
        return getSaldoPinjaman() + getSaldoPinjaman() * bunga;
    }
}
