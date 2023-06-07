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
public class Search {

    private SearchStrategy strat = new UnternehmenSearchStrategy();

    public ObservableList<Entry> search(ObservableList<Entry> entries, String keyword, boolean cSens, boolean Gueter, boolean Personen) {
        ObservableList<Entry> filteredEntries = entries;

        if (Gueter) {
            filteredEntries = filterGueter(entries);
        }
        if (Personen) {
            filteredEntries = filterPerso(entries);
        }
        if (Gueter && Personen) {
            filteredEntries = filterGueter(entries);
            filteredEntries = filterPerso(filteredEntries);
        }
        return strat.search(filteredEntries, keyword, cSens, Gueter, Personen);

    }

    public void setSearchStrategy(SearchStrategy s) {
        this.strat = s;
    }

    private ObservableList<Entry> filterGueter(ObservableList<Entry> entries) {
        ObservableList<Entry> filteredList = FXCollections.observableArrayList();

        for (Entry entry : entries) {
            if (entry.getGueterverkehr().equalsIgnoreCase("Ja")) {
                filteredList.add(entry);
            }
        }

        return filteredList;
    }

    private ObservableList<Entry> filterPerso(ObservableList<Entry> entries) {
        ObservableList<Entry> filteredList = FXCollections.observableArrayList();

        for (Entry entry : entries) {
            if (entry.getPersonenverkehr().equalsIgnoreCase("Ja")) {
                filteredList.add(entry);
            }
        }

        return filteredList;
    }

}
