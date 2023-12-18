/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS1;

/**
 *
 * @author nol
 */
public class CLI_DataAnggota {
    public static void main(String[] args) {
        
        // membuat object dengan nama “koperasi” dari class Koperasi
        Koperasi koperasi = new Koperasi();

        // Mengisi data menggunakan method dari class Koperasi dengan menyertakan nama object
        koperasi.setIdAnggota(2218071);
        koperasi.setNamaAnggota("Muhammad Revario Afadisan");
        koperasi.setAlamat("Malang");
        koperasi.setkelamin("Laki-laki");
        koperasi.setSaldoSimpanan(1000000);
        koperasi.setSaldoPinjaman(500000);
        koperasi.setTenggatBayar("31 Desember 2023");

        // Mencetak data koperasi
        System.out.println("\t\tDATA KOPERASI");
        System.out.println("=============================================");
        System.out.println("ID Anggota\t: " + koperasi.getIdAnggota());
        System.out.println("Nama\t\t: " + koperasi.getNamaAnggota());
        System.out.println("Alamat\t\t: " + koperasi.getAlamat());
        System.out.println("Jenis Kelamin\t: " + koperasi.getkelamin());
        System.out.println("Saldo Simpanan\t: " + koperasi.getSaldoSimpanan());
        System.out.println("Saldo Pinjaman\t: " + koperasi.getSaldoPinjaman());
        System.out.println("Tenggat Bayar\t: " + koperasi.getTenggatBayar());
    }
}
