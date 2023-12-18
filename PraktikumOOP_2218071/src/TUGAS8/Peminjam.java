/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS8;

/**
 *
 * @author nol
 */
public class Peminjam extends Abstract_Bayar implements Interface_Bunga{
    //atribut
    private double bunga;
    
    //method yang di override dari interface
    @Override
    public double Bunga(){
        bunga = 0.1;
        return bunga;
    }
 
    //method untuk menghitung total bunga setelah di override
    @Override
    public double hitungTotalBayar(){
        return 0;
    }
    
    //method untuk menghitung total bayar setelah di override
    @Override
    public double hitungTotalBayar(double bung) {
        return 0;
    }
}
