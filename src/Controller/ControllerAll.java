/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author fznrm
 */
public class ControllerAll {

    //untuk menyimpan isi semua hadits
    ArrayList<String> isiData = new ArrayList<>();
    //untuk menyimpan inputan
    String inputan;
    //untuk menyimpan kode soundex inputan
    String inputanSoundex;
    //untuk menyimpan kata nama terpilih dari tiap hadits
    ArrayList<String> kataNama = new ArrayList<>();
    //untuk menyimpan kode soundex dari kata nama terpilih
    ArrayList<String> kataNamaSoundex = new ArrayList<>();
    //penampungan sementara
    ArrayList<Double> penampunganNilai = new ArrayList<>();
    ArrayList<String> penampunganHadits = new ArrayList<>();
    ArrayList<String> penampungKataKunci = new ArrayList<>();
    //untuk menampung nilai jumlah kata dari gold standard
    private int jumlahData;
    //untuk mengambil data xls
//    private static final String EXCEL_FILE_LOCATION = "D:\\Kuliah\\Ekstensi IF\\Semester 4\\Tugas Akhir\\cobaExcel.xls";
    private String EXCEL_FILE_LOCATION;
    //nilai precision, recall, akurasi, f1
    double precision;
    double recall;
    double akurasi;
    double f1;
    //nilai TP, FP, FN, TN
    double TP;
    double FP;
    double FN;
    double TN;

    public ArrayList<String> getIsiData() {
        return isiData;
    }

    public void setIsiData(ArrayList<String> isiData) {
        this.isiData = isiData;
    }

    public String getInputan() {
        return inputan;
    }

    public void setInputan(String inputan) {
        this.inputan = inputan;
    }

    public String getInputanSoundex() {
        return inputanSoundex;
    }

    public void setInputanSoundex(String inputanSoundex) {
        this.inputanSoundex = inputanSoundex;
    }

    public ArrayList<String> getKataNama() {
        return kataNama;
    }

    public void setKataNama(ArrayList<String> kataNama) {
        this.kataNama = kataNama;
    }

    public ArrayList<String> getKataNamaSoundex() {
        return kataNamaSoundex;
    }

    public void setKataNamaSoundex(ArrayList<String> kataNamaSoundex) {
        this.kataNamaSoundex = kataNamaSoundex;
    }

    public void setPenampunganHadits(ArrayList<String> penampunganHadits) {
        this.penampunganHadits = penampunganHadits;
    }

    public ArrayList<String> getPenampunganHadits() {
        return penampunganHadits;
    }

    public void setPenampunganNilai(ArrayList<Double> penampunganNilai) {
        this.penampunganNilai = penampunganNilai;
    }

    public ArrayList<Double> getPenampunganNilai() {
        return penampunganNilai;
    }

    public int getJumlahData() {
        return jumlahData;
    }

    public void setJumlahData(int jumlahData) {
        this.jumlahData = jumlahData;
    }

    public ArrayList<String> getPenampungKataKunci() {
        return penampungKataKunci;
    }

    public void setPenampungKataKunci(ArrayList<String> penampungKataKunci) {
        this.penampungKataKunci = penampungKataKunci;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getRecall() {
        return recall;
    }

    public void setRecall(double recall) {
        this.recall = recall;
    }

    public double getAkurasi() {
        return akurasi;
    }

    public void setAkurasi(double akurasi) {
        this.akurasi = akurasi;
    }

    public double getF1() {
        return f1;
    }

    public void setF1(double f1) {
        this.f1 = f1;
    }

    public double getTP() {
        return TP;
    }

    public void setTP(double TP) {
        this.TP = TP;
    }

    public double getFP() {
        return FP;
    }

    public void setFP(double FP) {
        this.FP = FP;
    }

    public double getFN() {
        return FN;
    }

    public void setFN(double FN) {
        this.FN = FN;
    }

    public double getTN() {
        return TN;
    }

    public void setTN(double TN) {
        this.TN = TN;
    }

    //Method dari metode yang dipake
    //------------------------------------------------------------------------------------------------
    //1. Metode Soundex
    public static String soundex(String s) {
        char[] x = s.toUpperCase().toCharArray();
        char hurufPertama = x[0];

        // convert letters to numeric code
        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {

                case 'B':
                case 'F':
                case 'P':
                case 'V':
                    x[i] = '1';
                    break;

                case 'C':
                case 'G':
                case 'J':
                case 'K':
                case 'Q':
                case 'S':
                case 'X':
                case 'Z':
                    x[i] = '2';
                    break;

                case 'D':
                case 'T':
                    x[i] = '3';
                    break;

                case 'L':
                    x[i] = '4';
                    break;

                case 'M':
                case 'N':
                    x[i] = '5';
                    break;

                case 'R':
                    x[i] = '6';
                    break;

                default:
                    x[i] = '0';
                    break;
            }
        }

        // menghapus kode yang sama dan menghapus kode yang bernilai 0 karena huruf vokal
        String hasil = "" + hurufPertama;
        for (int i = 1; i < x.length; i++) {
            if (x[i] != x[i - 1] && x[i] != '0') {
                hasil += x[i];
            }
        }

        // untuk menambahkan kode 0 dihasil sementara kode soundexnya
        hasil = hasil + "0000";
        return hasil.substring(0, 4);
    }

    //2. Metode Levensthein
    public static int levenshtein(String nama1, String nama2) {
        int n, i = 0;
        int nm1 = nama1.length();
        int nm2 = nama2.length();
        char nama1_1 = 0;
        char nama2_2 = 0;
        // int cost;
        int k[][] = new int[nm1 + 1][nm2 + 1];

        //jml nama1
        for (n = 1; n <= nm1; n++) {
            //posisi hurufnya
            nama1_1 = nama1.charAt(n - 1);
            //jml nama2
            for (i = 1; i <= nm2; i++) {
                //posisi hurufnya
                nama2_2 = nama2.charAt(i - 1);
                if (n == 0 && i == 0) {
                    //awal mula ketika kondisi dimulai dari kotak awal bernilai 0
                    k[n][i] = 0;
                } else if (n == 0) {
                    //utk yg vertikal/insert
                    k[n][i] = k[n][i - 1] + 1;
                } else if (i == 0) {
                    //utk yg horizontal/delete
                    k[n][i] = k[n - 1][i] + 1;
                } else {
                    if (nama1_1 == nama2_2) {
                        //jika karakternya sama maka nilainya akan sesuai dengan posisi sebelumnya jika sama
                        k[n][i] = k[n - 1][i - 1];
                    } else {
                        k[n][i] = Math.min(k[n][i - 1] + 1, Math.min(k[n - 1][i] + 1, k[n - 1][i - 1] + 1));
                    }

                }
            }
        }
        return k[nm1][nm2];
    }
    //------------------------------------------------------------------------------------------------

    public void ambilData() {
//        String alamat = "D:\\Kuliah\\Ekstensi IF\\Semester 4\\Tugas Akhir\\Dataset Hadits\\"
//                + "DataSetSementara\\AllDataset\\Percobaan1";
        String alamat = "./Dataset";
        File folder = new File(alamat);
        File[] list = folder.listFiles();
        int data = 1;
        String namaFile;
        FileReader fr = null;
        BufferedReader br;
        for (File list1 : list) {
            try {
//                namaFile = "D:\\Kuliah\\Ekstensi IF\\Semester 4\\Tugas Akhir\\Dataset Hadits\\"
//                        + "DataSetSementara\\AllDataset\\Percobaan1\\" + data + ".txt";
                namaFile = "./Dataset/" + data + ".txt";
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

    public void prosesAll() {
        //memotong kalimat menjadi kata-kata
        //proses ini dijalani secara satuan hadits
        int jumlahKataNama = 0;
        int jumlahSemuaKata = 0;
        for (int i = 0; i < isiData.size(); i++) {
            //motong hadits
            String[] kumpulanKata1;
            kumpulanKata1 = isiData.get(i).split(" ");

            //mengganti tanda baca
            kumpulanKata1 = hapusTandaBaca(kumpulanKata1);
            jumlahSemuaKata = jumlahSemuaKata + kumpulanKata1.length;

            //dicari kata yang kapital awalnya
            cariNama(kumpulanKata1);
            jumlahKataNama = jumlahKataNama + kataNama.size();

            //merubah inputan menjadi kode soundex
            rubahInputan(getInputan());

            //merubah kata nama menjadi kode soundex
            rubahNama();

            //untuk mendapatkan nilai 
            int nilai1, nilai2;
            nilai1 = getInputan().length();
            for (int j = 0; j < kataNamaSoundex.size(); j++) {
                if (!getInputanSoundex().equals(kataNamaSoundex.get(j))) {
//                    System.out.println("Hasil tidak ditemukan");
                } else {
                    nilai2 = kataNama.get(j).length();
                    double pembagi = Math.max(nilai2, nilai1);
                    double pembilang = levenshtein(getInputan(), kataNama.get(j));
                    double nilai = (1.0 - (pembilang / pembagi)) * 100;
                    penampunganNilai.add(nilai);
                    penampunganHadits.add(isiData.get(i));
                    penampungKataKunci.add(kataNama.get(j));
                }
            }
//            System.out.println("Sementara : " + penampunganNilai.size());
//            System.out.println("Sementara : " + penampunganHadits.size());
//            System.out.println("Sementara : " + penampungKataKunci.size());
//            System.out.print("----------\n");
            Arrays.fill(kumpulanKata1, null);
            clearData1();
        }
//        System.out.println("Akhir : " + penampunganNilai.size());
//        System.out.println("Akhir : " + penampunganHadits.size());
//        System.out.println("Akhir : " + penampungKataKunci.size());
//        System.out.print("-----------\n");
        kalimatTerpilih();
        System.out.println("Jumlah kata nama : " + jumlahKataNama);
        System.out.println("Jumlah kata semua : " + jumlahSemuaKata);
        if (penampunganHadits.isEmpty()) {
            System.out.println("Tidak ditemukan");
            System.out.println("Kata kunci : tidak ada");
            int GoldStandard = 0;
            double TruePositif = 0;
            double FalseNegatif = 0;
            double FalsePositif = 0;
            double TrueNegatif = jumlahSemuaKata;
            System.out.println("Kata All : " + jumlahSemuaKata);
            System.out.println("TP : " + TruePositif);
            setTP(TruePositif);
            System.out.println("FN : " + FalseNegatif);
            setFN(FalseNegatif);
            System.out.println("FP : " + FalsePositif);
            setFP(FalsePositif);
            System.out.println("TN : " + TrueNegatif);
            setTN(TrueNegatif);
            setPrecision(0);
            setRecall(0);
            setAkurasi((TruePositif + TrueNegatif) / (TruePositif + TrueNegatif + FalsePositif + FalseNegatif));
            setF1(2 * 0);
        } else {
            sorting();
            cariPrecisionRecallAccuracy(jumlahSemuaKata);
        }
        System.out.println("-------------------------------------------\n");
    }

    public void cariPrecisionRecallAccuracy(int kataAll) {
        //proses cari precision recall akurasi
        String kataKunci = penampungKataKunci.get(0);
        cariGoldStandard(kataKunci);
        System.out.println("Kata kunci : " + kataKunci + ", ada " + getJumlahData());
        int kataLebih = 0;
        if (penampungKataKunci.size() > getJumlahData()) {
            kataLebih = penampungKataKunci.size() - getJumlahData();
        }
        int GoldStandard = getJumlahData();
        double TruePositif;
        double FalseNegatif;
        double FalsePositif;
        double TrueNegatif;
        int jumlahKataKunci = 0;
        for (int i = 0; i < penampungKataKunci.size(); i++) {
            if (penampungKataKunci.get(i).equals(kataKunci)) {
                jumlahKataKunci++;
            }
        }
        if (jumlahKataKunci == GoldStandard) {
            TruePositif = jumlahKataKunci;
            FalseNegatif = 0;
            FalsePositif = 0 + kataLebih;
            TrueNegatif = kataAll - (TruePositif + FalsePositif + FalseNegatif);
            System.out.println("Kata All :" + kataAll);
            System.out.println("TP : " + TruePositif);
            setTP(TruePositif);
            System.out.println("FN : " + FalseNegatif);
            setFN(FalseNegatif);
            System.out.println("FP : " + FalsePositif);
            setFP(FalsePositif);
            System.out.println("TN : " + TrueNegatif);
            setTN(TrueNegatif);
            setPrecision(TruePositif / (TruePositif + FalsePositif));
            setRecall(TruePositif / (TruePositif + FalseNegatif));
            setAkurasi((TruePositif + TrueNegatif) / (TruePositif + TrueNegatif + FalsePositif + FalseNegatif));
            setF1(2 * ((getPrecision() * getRecall()) / (getPrecision() + getRecall())));
        } else if (jumlahKataKunci > GoldStandard) {
            TruePositif = GoldStandard;
            FalseNegatif = 0;
            FalsePositif = (jumlahKataKunci - GoldStandard) + kataLebih;
            TrueNegatif = kataAll - (TruePositif + FalsePositif + FalseNegatif);
            System.out.println("Kata All :" + kataAll);
            System.out.println("TP : " + TruePositif);
            setTP(TruePositif);
            System.out.println("FN : " + FalseNegatif);
            setFN(FalseNegatif);
            System.out.println("FP : " + FalsePositif);
            setFP(FalsePositif);
            System.out.println("TN : " + TrueNegatif);
            setTN(TrueNegatif);
            setPrecision(TruePositif / (TruePositif + FalsePositif));
            setRecall(TruePositif / (TruePositif + FalseNegatif));
            setAkurasi((TruePositif + TrueNegatif) / (TruePositif + TrueNegatif + FalsePositif + FalseNegatif));
            setF1(2 * ((getPrecision() * getRecall()) / (getPrecision() + getRecall())));
        } else if (jumlahKataKunci < GoldStandard) {
            TruePositif = jumlahKataKunci;
            FalseNegatif = GoldStandard - jumlahKataKunci;
            FalsePositif = 0 + kataLebih;
            TrueNegatif = kataAll - (TruePositif + FalsePositif + FalseNegatif);
            System.out.println("Kata All :" + kataAll);
            System.out.println("TP : " + TruePositif);
            setTP(TruePositif);
            System.out.println("FN : " + FalseNegatif);
            setFN(FalseNegatif);
            System.out.println("FP : " + FalsePositif);
            setFP(FalsePositif);
            System.out.println("TN : " + TrueNegatif);
            setTN(TrueNegatif);
            setPrecision(TruePositif / (TruePositif + FalsePositif));
            setRecall(TruePositif / (TruePositif + FalseNegatif));
            setAkurasi((TruePositif + TrueNegatif) / (TruePositif + TrueNegatif + FalsePositif + FalseNegatif));
            setF1(2 * ((getPrecision() * getRecall()) / (getPrecision() + getRecall())));
        }
    }

    public void sorting() {
        for (int i = 0; i < penampunganNilai.size(); i++) {
            for (int j = 0; j < penampunganNilai.size() - 1; j++) {
                if (penampunganNilai.get(j) < penampunganNilai.get(j + 1)) {
                    double temp1 = penampunganNilai.get(j);
                    String temp2 = penampunganHadits.get(j);
                    String temp3 = penampungKataKunci.get(j);

                    penampunganNilai.set(j, penampunganNilai.get(j + 1));
                    penampunganHadits.set(j, penampunganHadits.get(j + 1));
                    penampungKataKunci.set(j, penampungKataKunci.get(j + 1));

                    penampunganNilai.set(j + 1, temp1);
                    penampunganHadits.set(j + 1, temp2);
                    penampungKataKunci.set(j + 1, temp3);
                }
            }
        }
//        for (int i = 0; i < penampunganNilai.size(); i++) {
//            System.out.println(penampunganHadits.get(i)
//                    + " ; Kata kunci : " + penampungKataKunci.get(i)
//                    + ", " + penampunganNilai.get(i));
//        }
    }

    public static String[] hapusTandaBaca(String[] kumpulanKata1) {
        for (int i = 0; i < kumpulanKata1.length; i++) {
            kumpulanKata1[i] = kumpulanKata1[i].replaceAll("[^\\w]", "");
        }
        return kumpulanKata1;
    }

    public String rubahInputan(String inputan) {
        setInputanSoundex(soundex(inputan));
        return inputanSoundex;
    }

    public void cariNama(String[] kumpulanKata1) {
        for (String kumpulanKata11 : kumpulanKata1) {
            if (Character.isUpperCase(kumpulanKata11.charAt(0))) {
                kataNama.add(kumpulanKata11);
            }
        }
    }

    public void clearData1() {
        kataNama.clear();
        kataNamaSoundex.clear();
    }

    public void clearData2() {
        penampunganHadits.clear();
        penampunganNilai.clear();
        penampungKataKunci.clear();
        isiData.clear();
        System.out.println("Hadits : " + penampunganHadits.size()
                + ", Nilai : " + penampunganNilai.size()
                + ", Kata Kunci : " + penampungKataKunci.size());
    }

    public void rubahNama() {
        for (int j = 0; j < kataNama.size(); j++) {
            kataNamaSoundex.add(soundex(kataNama.get(j)));
        }
    }

    public void kalimatTerpilih() {
        System.out.println("Jumlah hadits terpilih : " + penampunganHadits.size());
        System.out.println("Jumlah nilai terpilih : " + penampunganNilai.size());
        System.out.println("Jumlah kata kunci terpilih : " + penampungKataKunci.size());
    }

    public void cariGoldStandard(String nama) {
        Workbook workbook = null;
        try {
//            EXCEL_FILE_LOCATION = "/GoldStandard/cobaExcel.xls";
            workbook = Workbook.getWorkbook(new File("./GoldStandard/cobaExcel.xls"));
            Sheet sheet = workbook.getSheet(0);
            int allData = sheet.getRows();
            for (int i = 1; i < allData; i++) {
                Cell selNama = sheet.getCell(0, i);
                if (selNama.getContents().equals(nama)) {
                    Cell selJumlah = sheet.getCell(101, i);
                    int banyakData = Integer.parseInt(selJumlah.getContents());
                    setJumlahData(banyakData);
                }
            }
        } catch (IOException | BiffException e) {
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }
}
