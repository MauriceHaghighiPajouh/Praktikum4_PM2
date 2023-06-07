/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hawhamburg.pm2_praktikum4;

import javafx.collections.ObservableList;

/**
 *
 * @author momo
 */
public class Search   {
    
    private SearchStrategy strat = new  UnternehmenSearchStrategy();


    public ObservableList<Entry> search(ObservableList<Entry> entries, String keyword, boolean cSens,boolean Gueter,boolean Personen) {
        
        return strat.search(entries, keyword, cSens, Gueter, Personen);
        
        
    }
    
    public void setSearchStrategy(SearchStrategy s ){
        this.strat = s;
    }
    
    
   
  
        
    }
    
    
    
    
    
    
    

