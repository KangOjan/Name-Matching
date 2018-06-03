/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author fznrm
 */
public class Model_DataSet {

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
}
