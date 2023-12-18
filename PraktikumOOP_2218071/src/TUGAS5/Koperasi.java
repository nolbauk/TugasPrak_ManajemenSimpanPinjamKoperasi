/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS5;

/**
 *
 * @author nol
 */
public class Koperasi {
    //Atribut
    private int idAnggota;
    private String namaAnggota;
    private String Alamat;
    private String kelamin;
    private double saldoSimpanan;
    private double saldoPinjaman;
    public String tenggatBayar;

    //Method
    //setter
    public void setIdAnggota(int idAnggota) {
        this.idAnggota = idAnggota;
    }
    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }
    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }
    public void setkelamin(String kelamin) {
        this.kelamin = kelamin;
    }
    public void setSaldoSimpanan(double saldoSimpanan) {
        this.saldoSimpanan = saldoSimpanan;
    }
    public void setSaldoPinjaman(double saldoPinjaman) {
        this.saldoPinjaman = saldoPinjaman;
    }
    public void simpanUang(double jumlah) {
        saldoSimpanan += jumlah;
    }
    public void pinjamUang(double jumlah) {
        saldoPinjaman += jumlah;
    }
    
    //getter
    public int getIdAnggota() {
        return idAnggota;
    }
    public String getNamaAnggota() {
        return namaAnggota;
    }
    public String getAlamat() {
        return Alamat;
    }  
    public String getkelamin() {
        return kelamin;
    }   
    public double getSaldoSimpanan() {
        return saldoSimpanan;
    }
    public double getSaldoPinjaman() {
        return saldoPinjaman;
    }
    
    //konstruktor
    public Koperasi() {
        this.tenggatBayar = "25 Desember 2023";
    }
    
    //Method Overloading yang akan di Overriding
    public double hitungTotalBayar() {
        return getSaldoPinjaman();
    }
    public double hitungTotalBayar(double bunga) {
        return getSaldoPinjaman() * bunga;
    }
}
