/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS4;

/**
 *
 * @author nol
 */
public class Anggota extends Koperasi{
    //atribut
    private int Umur;
    private String Pekerjaan;
    private String tglGabung;
    private String noTelp;
    private String email;
    
    //method
    //setter
    public void setUmur(int Umur) {
        this.Umur = Umur;
    }
    public void setPekerjaan(String Pekerjaan) {
        this.Pekerjaan = Pekerjaan;
    }
    public void settglGabung(String tglGabung) {
        this.tglGabung = tglGabung;
    }
    public void setnoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    //getter
    public int getUmur() {
        return Umur;
    }
    public String getPekerjaan() {
        return Pekerjaan;
    }
    public String gettglGabung() {
        return tglGabung;
    }
    public String getnoTelp() {
        return noTelp;
    }
    public String getEmail() {
        return email;
    }
}
