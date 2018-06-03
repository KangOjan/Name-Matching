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
public class Controller_DataSet {

    private final ArrayList<String> kataTerpilih = new ArrayList<>();
    ArrayList<String> tempKataTerpilih = new ArrayList<>();
    String[] kumpulanHurufDataset = {};

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

    public void ambilData(String alamat) {
        String isiData;
        String[] kumpulanKata;
        File file = new File(alamat);
        File[] list = file.listFiles();
        for (int i = 0; i < list.length; i++) {
            String path = list[i].getPath();
            try {
                isiData = new String(Files.readAllBytes(Paths.get(path)));
                kumpulanKata = isiData.split(" ");
                for (int j = 0; j < kumpulanKata.length; j++) {
                    kumpulanKata[j] = kumpulanKata[j].replaceAll("[^\\w]", "");
                }
                for (int k = 0; k < kumpulanKata.length; k++) {
                    String kataTemp;
                    if (cekKapital(kumpulanKata[k]) == true) {
                        kataTemp = kumpulanKata[k];
                        tempKataTerpilih.add(kataTemp);
                    }
                }
                proses();
                pecahKata();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void proses() {
        String kata;
        for (int i = 0; i < tempKataTerpilih.size(); i++) {
            kata = tempKataTerpilih.get(i).toLowerCase();
            kataTerpilih.add(kata);
        }
    }

    public void pecahKata() {
        for (int i = 0; i < kataTerpilih.size(); i++) {
            kumpulanHurufDataset = getKataTerpilih().get(i).split("");
            for (int j = 0; j < kumpulanHurufDataset.length; j++) {
                System.out.println(kumpulanHurufDataset[j]);
            }
            System.out.print("\n");
        }
    }
}
