/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS3;

/**
 *
 * @author nol
 */
public class Koperasi {
    //Atribut
    int idAnggota;
    String namaAnggota;
    String Alamat;
    String kelamin;
    double saldoSimpanan;
    double saldoPinjaman;
    String tenggatBayar;

    //Method
    void setIdAnggota(int idAnggota) {
        this.idAnggota = idAnggota;
    }
    void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }
    void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }
    void setkelamin(String kelamin) {
        this.kelamin = kelamin;
    }
    void setSaldoSimpanan(double saldoSimpanan) {
        this.saldoSimpanan = saldoSimpanan;
    }
    void setSaldoPinjaman(double saldoPinjaman) {
        this.saldoPinjaman = saldoPinjaman;
    }
    void simpanUang(double jumlah) {
        saldoSimpanan += jumlah;
    }
    void pinjamUang(double jumlah) {
        saldoPinjaman += jumlah;
    }
    int getIdAnggota() {
        return idAnggota;
    }
    String getNamaAnggota() {
        return namaAnggota;
    }
    String getAlamat() {
        return Alamat;
    }  
    String getkelamin() {
        return kelamin;
    }   
    double getSaldoSimpanan() {
        return saldoSimpanan;
    }
    double getSaldoPinjaman() {
        return saldoPinjaman;
    }
    public Koperasi() {
        this.tenggatBayar = "25 Desember 2023";
    }
}
