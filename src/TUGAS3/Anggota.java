/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS3;

/**
 *
 * @author nol
 */
public class Anggota extends Koperasi{
    //atribut
    int Umur;
    String Pekerjaan;
    String tglGabung;
    String noTelp;
    String email;
    
    //method
    void setUmur(int Umur) {
        this.Umur = Umur;
    }
    void setPekerjaan(String Pekerjaan) {
        this.Pekerjaan = Pekerjaan;
    }
    void settglGabung(String tglGabung) {
        this.tglGabung = tglGabung;
    }
    void setnoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
    void setEmail(String email) {
        this.email = email;
    }
    int getUmur() {
        return Umur;
    }
    String getPekerjaan() {
        return Pekerjaan;
    }
    String gettglGabung() {
        return tglGabung;
    }
    String getnoTelp() {
        return noTelp;
    }
    String getEmail() {
        return email;
    }
}
