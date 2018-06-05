/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author fznrm
 */
public class ControllerAll {

    private final ArrayList<String> kataTerpilih = new ArrayList<>();
    ArrayList<String> tempKataTerpilih = new ArrayList<>();
    String[] kumpulanHurufDataset = {};
    ArrayList<String> isiData = new ArrayList<>();
    String[] kumpulanHurufDicari;
    String yangDicari;
    String hasil;

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getHasil() {
        return hasil;
    }

    public String[] getKumpulanHurufDicari() {
        return kumpulanHurufDicari;
    }

    public String getYangDicari() {
        return yangDicari;
    }

    public void setYangDicari(String yangDicari) {
        this.yangDicari = yangDicari;
    }

    public ArrayList<String> getIsiData() {
        return isiData;
    }

    public String[] getKumpulanHurufDataset() {
        return kumpulanHurufDataset;
    }

    public void setKumpulanHurufDataset(String[] kumpulanHurufDataset) {
        this.kumpulanHurufDataset = kumpulanHurufDataset;
    }

    public ArrayList<String> getKataTerpilih() {
        return kataTerpilih;
    }

    public ArrayList<String> getTempKataTerpilih() {
        return tempKataTerpilih;
    }

    private static boolean cekKapital(String str) {
        char ch = str.charAt(0);
        boolean capitalFlag = false;
        if (Character.isUpperCase(ch)) {
            capitalFlag = true;
        }
        return capitalFlag;
    }

    public void pecahKataDicari() {
        setYangDicari(getYangDicari().toLowerCase());
        kumpulanHurufDicari = getYangDicari().split("");
        for (int i = 0; i < kumpulanHurufDicari.length; i++) {
            System.out.println(kumpulanHurufDicari[i]);
        }
    }

    public void proses() {
        String kata;
        for (int i = 0; i < tempKataTerpilih.size(); i++) {
            kata = tempKataTerpilih.get(i).toLowerCase();
            kataTerpilih.add(kata);
        }
    }

    public void pecahKataSet() {
        for (int i = 0; i < kataTerpilih.size(); i++) {
            kumpulanHurufDataset = getKataTerpilih().get(i).split("");
            for (int j = 0; j < kumpulanHurufDataset.length; j++) {
                System.out.println(kumpulanHurufDataset[j]);
            }
            System.out.print("\n");
        }
    }

    public void keluarData(String alamat) {
        File file = new File(alamat);
        File[] list = file.listFiles();
        String data;
        for (int i = 0; i < list.length; i++) {
            try {
                String path = list[i].getPath();
                data = new String(Files.readAllBytes(Paths.get(path)));
                isiData.add(data);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void prosesPotongKata() {
        String[] kumpulanKata;
        String kalimat;
        for (int i = 0; i < isiData.size(); i++) {
            kumpulanKata = getIsiData().get(i).split(" ");
            for (int j = 0; j < kumpulanKata.length; j++) {
                kumpulanKata[j] = kumpulanKata[j].replaceAll("[^\\w]", "");
            }
            for (String kumpulanKata1 : kumpulanKata) {
                String kataTemp;
                if (cekKapital(kumpulanKata1) == true) {
                    kataTemp = kumpulanKata1;
                    tempKataTerpilih.add(kataTemp);
                }
            }
            proses();
            String nama;
            int skor = 0;
            for (int k = 0; k < getKataTerpilih().size(); k++) {
                nama = getKataTerpilih().get(k);
                if (nama.equals(getYangDicari())) {
                    skor++;
                }
            }
            if (skor > 0) {
                System.out.println(isiData.get(i));
                kalimat = isiData.get(i);
                setHasil(kalimat);
            }
        }
    }
}
