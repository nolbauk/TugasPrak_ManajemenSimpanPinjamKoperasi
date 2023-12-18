/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS4;

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
 
    public double hitungTotalBayar() {
        return getSaldoPinjaman() + getSaldoPinjaman() * bunga;
    }
}
