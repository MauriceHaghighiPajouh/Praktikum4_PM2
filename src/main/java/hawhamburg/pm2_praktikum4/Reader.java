/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hawhamburg.pm2_praktikum4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author momo
 */
public class Reader {

    private List<Entry> entryList = new ArrayList<>();

    public List<Entry> getEntryList() {
        return entryList;
    }

    public static final String splitSymbol = ";";

    public void read(String file) throws IOException {
        boolean headerLine = true;
        String row;

        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((row = br.readLine()) != null) {
            if (headerLine) {
                headerLine = false;
                continue;
            }
            String[] data = row.split(splitSymbol);
            createEntry(data);

        }
        br.close();

    }

    public void createEntry(String[] data) {

        Entry entry = new Entry(data[0], data[1], data[2], data[3], data[4], data[5]);

        entryList.add(entry);

    }

}
