/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hawhamburg.pm2_praktikum4;

import javafx.collections.ObservableList;

/**
 *
 * @author momo
 */
public interface SearchInterface {
    
   ObservableList<Entry> search(ObservableList<Entry> entries, String keyword,boolean cSens);
    
}
