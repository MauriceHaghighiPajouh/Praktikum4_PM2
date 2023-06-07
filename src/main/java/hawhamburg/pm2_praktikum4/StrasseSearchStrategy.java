/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hawhamburg.pm2_praktikum4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author momo
 */
public class StrasseSearchStrategy implements SearchStrategy {

    @Override
    public ObservableList<Entry> search(ObservableList<Entry> entries, String keyword, boolean cSens,boolean Gueter,boolean Personen) {
        ObservableList<Entry> result = FXCollections.observableArrayList();

        for (Entry entry : entries) {
            String value = entry.getStrasse();
            if (!cSens) {
                keyword = keyword.toLowerCase();
                value = value.toLowerCase();
            }
            if (value.contains(keyword)) {
                result.add(entry);
            }
        }

        return result;
    }
}
