package hawhamburg.pm2_praktikum4;

public class Entry {

    private String Unternehmen;
    private String Strasse;
    private String PLZ;
    private String Ort;
    private String Gueterverkehr;
    private String Personenverkehr;

    public Entry(String Unternehmen, String Strasse,String PLZ,String Ort,String Gueterverkehr,String Personenverkehr ){
        this.Unternehmen=Unternehmen;
        this.Strasse = Strasse;
        this.PLZ = PLZ;
        this.Ort = Ort;
        this.Gueterverkehr=Gueterverkehr;
        this.Personenverkehr=Personenverkehr;
        
    }

    public String getUnternehmen() {
        return Unternehmen;
    }
    public String getStrasse() {
        return Strasse;
    }
    public String getPLZ() {
        return PLZ;
    }
    public String getOrt() {
        return Ort;
    }
    public String getGueterverkehr() {
        return Gueterverkehr;
    }
    public String getPersonenverkehr() {
        return Personenverkehr;
    }

    
    
}
