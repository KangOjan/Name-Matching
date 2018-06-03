/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author fznrm
 */
public class Controller_DataDicari {

    String[] kumpulanHurufDicari;
    String yangDicari;

    public String[] getKumpulanHurufDicari() {
        return kumpulanHurufDicari;
    }

    public String getYangDicari() {
        return yangDicari;
    }

    public void setYangDicari(String yangDicari) {
        this.yangDicari = yangDicari;
    }

    public void pecahKata() {
        setYangDicari(getYangDicari().toLowerCase());
        kumpulanHurufDicari = getYangDicari().split("");
        for (int i = 0; i < kumpulanHurufDicari.length; i++) {
            System.out.println(kumpulanHurufDicari[i]);
        }
    }
}
