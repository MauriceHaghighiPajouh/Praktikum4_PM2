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
public class Search   {
    
    private SearchStrategy strat;


    public ObservableList<Entry> search(ObservableList<Entry> entries, String keyword, boolean cSens,String category,boolean Gueter,boolean Personen) {
        
        return strat.search(entries, keyword, cSens, category, Gueter, Personen);
        
        
    }
    
  
        
    }
    
    
    
    
    
    
    

