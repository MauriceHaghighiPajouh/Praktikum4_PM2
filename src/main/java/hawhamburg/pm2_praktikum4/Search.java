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
public class Search implements SearchInterface {

    @Override
    public ObservableList<Entry> search(ObservableList<Entry> entries, String keyword, boolean cSens,String category,boolean Gueter,boolean Personen) {
        
        ObservableList<Entry> updatedList = FXCollections.observableArrayList();
        
        for(Entry entry:entries){
            String value = getCatValue(entry,category);
            if(!cSens){
                keyword=keyword.toLowerCase();
                value=value.toLowerCase();
            }
            if(value.contains(keyword)){
                updatedList.add(entry);
            }
        }
        return updatedList;
        
    }
    
    public String getCatValue(Entry entry, String cat){
        
        switch(cat){
            case "Unternehmen":
                return entry.getUnternehmen();
            case "Strasse":
                return entry.getStrasse();
            case "PLZ":
                return entry.getPLZ();
            case "Ort":
                return entry.getOrt();
            case "Gueterverkehr":
                return entry.getGueterverkehr();
            case "Personenverkehr":
                return entry.getPersonenverkehr();
            default:
                return "";
                
            
        }
    }
    
    
  
        
    }
    
    
    
    
    
    
    

