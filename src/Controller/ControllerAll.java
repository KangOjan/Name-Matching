/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fznrm
 */
public class ControllerAll {

    ArrayList<String> isiData = new ArrayList<>();
    ArrayList<String> kataAwal = new ArrayList<>();
    ArrayList<String> kalimatTerpilih = new ArrayList<>();
    String inputan;

    public void setInputan(String inputan) {
        this.inputan = inputan;
    }

    public String getInputan() {
        return inputan;
    }

    public void setKataAwal(ArrayList<String> kataAwal) {
        this.kataAwal = kataAwal;
    }

    public ArrayList<String> getKataAwal() {
        return kataAwal;
    }

    public void setIsiData(ArrayList<String> isiData) {
        this.isiData = isiData;
    }

    public ArrayList<String> getIsiData() {
        return isiData;
    }

    public void setKalimatTerpilih(ArrayList<String> kalimatTerpilih) {
        this.kalimatTerpilih = kalimatTerpilih;
    }

    public ArrayList<String> getKalimatTerpilih() {
        return kalimatTerpilih;
    }

    public void ambilData() {
        String alamat = "D:\\Kuliah\\Ekstensi IF\\Semester 4\\Tugas Akhir\\Dataset Hadits\\"
                + "DataSetSementara\\AllDataset\\Percobaan1";
        File folder = new File(alamat);
        File[] list = folder.listFiles();
        int data = 1;
        String namaFile;
        FileReader fr = null;
        BufferedReader br;
        for (File list1 : list) {
            try {
                namaFile = "D:\\Kuliah\\Ekstensi IF\\Semester 4\\Tugas Akhir\\Dataset Hadits\\"
                        + "DataSetSementara\\AllDataset\\Percobaan1\\" + data + ".txt";
                fr = new FileReader(namaFile);
                br = new BufferedReader(fr);
                String isi;
                while ((isi = br.readLine()) != null) {
                    isiData.add(isi);
                }
                data++;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControllerAll.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(ControllerAll.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(ControllerAll.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public void prepocessing() {
        //memotong kalimat menjadi kata-kata
        for (int j = 0; j < isiData.size(); j++) {
            String[] kumpKata;
            kumpKata = isiData.get(j).split(" ");

            //mengganti tanda baca
            for (int k = 0; k < kumpKata.length; k++) {
                kumpKata[k] = kumpKata[k].replaceAll("[^\\w]", "");
            }

            //mencari kata berhuruf kapital
            int point = 0;
            for (int l = 0; l < kumpKata.length; l++) {
                if (Character.isUpperCase(kumpKata[l].charAt(0))) {
                    //disini diperiksa, jika ada nama yang sama atau serupa dengan inputan
                    if (kumpKata[l].equalsIgnoreCase(getInputan())) {
                        point++;
                    }
                    kataAwal.add(kumpKata[l]);
                }
            }
            if (point >= 1) {
                kalimatTerpilih.add(isiData.get(j));
            }
            Arrays.fill(kumpKata, null);
        }
    }
//    private final ArrayList<String> kataTerpilih = new ArrayList<>();
//    ArrayList<String> tempKataTerpilih = new ArrayList<>();
//    String[] kumpulanHurufDataset = {};
//    ArrayList<String> isiData = new ArrayList<>();
//    String[] kumpulanHurufDicari;
//    String yangDicari;
//    String hasil;
//
//    public void setHasil(String hasil) {
//        this.hasil = hasil;
//    }
//
//    public String getHasil() {
//        return hasil;
//    }
//
//    public String[] getKumpulanHurufDicari() {
//        return kumpulanHurufDicari;
//    }
//
//    public String getYangDicari() {
//        return yangDicari;
//    }
//
//    public void setYangDicari(String yangDicari) {
//        this.yangDicari = yangDicari;
//    }
//
//    public ArrayList<String> getIsiData() {
//        return isiData;
//    }
//
//    public String[] getKumpulanHurufDataset() {
//        return kumpulanHurufDataset;
//    }
//
//    public void setKumpulanHurufDataset(String[] kumpulanHurufDataset) {
//        this.kumpulanHurufDataset = kumpulanHurufDataset;
//    }
//
//    public ArrayList<String> getKataTerpilih() {
//        return kataTerpilih;
//    }
//
//    public ArrayList<String> getTempKataTerpilih() {
//        return tempKataTerpilih;
//    }
//
//    private static boolean cekKapital(String str) {
//        char ch = str.charAt(0);
//        boolean capitalFlag = false;
//        if (Character.isUpperCase(ch)) {
//            capitalFlag = true;
//        }
//        return capitalFlag;
//    }
//
//    public void pecahKataDicari() {
//        setYangDicari(getYangDicari().toLowerCase());
//        kumpulanHurufDicari = getYangDicari().split("");
//        for (int i = 0; i < kumpulanHurufDicari.length; i++) {
//            System.out.println(kumpulanHurufDicari[i]);
//        }
//    }
//
//    public void proses() {
//        String kata;
//        for (int i = 0; i < tempKataTerpilih.size(); i++) {
//            kata = tempKataTerpilih.get(i).toLowerCase();
//            kataTerpilih.add(kata);
//        }
//    }
//
//    public void pecahKataSet() {
//        for (int i = 0; i < kataTerpilih.size(); i++) {
//            kumpulanHurufDataset = getKataTerpilih().get(i).split("");
//            for (int j = 0; j < kumpulanHurufDataset.length; j++) {
//                System.out.println(kumpulanHurufDataset[j]);
//            }
//            System.out.print("\n");
//        }
//    }
//    
//    public void pecahKataCari(){
//        for (int i = 0; i <getYangDicari().length() ; i++) {
//            kumpulanHurufDicari = getYangDicari().split("");
//        }
//        for (String string : kumpulanHurufDicari) {
//            System.out.println(string);
//        }
//    }
//
//    public void keluarData(String alamat) {
//        File file = new File(alamat);
//        File[] list = file.listFiles();
//        String data;
//        for (int i = 0; i < list.length; i++) {
//            try {
//                String path = list[i].getPath();
//                data = new String(Files.readAllBytes(Paths.get(path)));
//                isiData.add(data);
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
//
//    public void prosesPotongKata() {
//        String[] kumpulanKata;
//        String kalimat;
//        for (int i = 0; i < isiData.size(); i++) {
//            kumpulanKata = getIsiData().get(i).split(" ");
//            for (int j = 0; j < kumpulanKata.length; j++) {
//                kumpulanKata[j] = kumpulanKata[j].replaceAll("[^\\w]", "");
//            }
//            for (String kumpulanKata1 : kumpulanKata) {
//                String kataTemp;
//                if (cekKapital(kumpulanKata1) == true) {
//                    kataTemp = kumpulanKata1;
//                    tempKataTerpilih.add(kataTemp);
//                }
//            }
//            proses();
//            String nama;
//            int skor = 0;
//            for (int k = 0; k < getKataTerpilih().size(); k++) {
//                nama = getKataTerpilih().get(k);
//                if (nama.equals(getYangDicari())) {
//                    skor++;
//                }
//            }
//            if (skor > 0) {
//                System.out.println(isiData.get(i));
//                kalimat = isiData.get(i);
//                setHasil(kalimat);
//            }
//        }
//    }
}
