package hawhamburg.pm2_praktikum4;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private String file = "evu_brd_gekuerzt.csv";
    private boolean caseSensitive;
    private boolean gueter;
    private boolean perso;

    @Override
    public void start(Stage stage) {
           
        // erzeugen von GUI elementen
        Label label = new Label("Suchen nach: ");
        BorderPane borderpane = new BorderPane();
        CheckBox checkbox = new CheckBox("Groß- / Kleinschreibung beachten");
        label.setFont(new Font("Arial", 18));
        //checkboxes die nachher obige booleans kontrollieren
        CheckBox cbGueter = new CheckBox("Gueterverkehr anzeigen");
        CheckBox cbPerso = new CheckBox("Personenverkehr anzeigen");
        //horizontale box fuer unser control panel
        HBox hbox = new HBox();
        //auswahlbox fuer kategorie
        ComboBox<String> combobox = new ComboBox<>();
        //combobox werden strings hinzugefuegt
        combobox.getItems().addAll("Unternehmen", "Strasse", "PLZ", "Ort");
        //vorauswahl von combobox
        combobox.setValue("Unternehmen");
        
        TextField textfield = new TextField();
        //search ist unsere klasse mit dem strategy pattern
        Search search = new Search();
        //diese checkbox setzt den boolean fuer case sensitive auf true o false
        checkbox.setOnAction(e
                -> {
            caseSensitive = checkbox.isSelected();
        }
        );
        
    
        //alle elemente vom control panel werden der hbox hinzugefuegt
        hbox.getChildren().addAll(label, combobox, textfield, checkbox, cbGueter, cbPerso);

        // UNTERNEHMEN;STRASSE;PLZ;ORT;GUETERVERKEHR;PERSONENVERKEHR
        // super schlecht geloest. wollte loop implementieren, aber zu faul
        TableView<Entry> tableView = new TableView<>();
        TableColumn<Entry, String> colUnternehmen = new TableColumn<>("Unternehmen");
        TableColumn<Entry, String> colStrasse = new TableColumn<>("Strasse");
        TableColumn<Entry, String> colPlz = new TableColumn<>("PLZ");
        TableColumn<Entry, String> colOrt = new TableColumn<>("Ort");
        TableColumn<Entry, String> colGueterV = new TableColumn<>("Gueterverkehr");
        TableColumn<Entry, String> colPersonenV = new TableColumn<>("Personenverkehr");

        // ??
        colUnternehmen.setCellValueFactory(new PropertyValueFactory<>("Unternehmen"));
        colStrasse.setCellValueFactory(new PropertyValueFactory<>("Strasse"));
        colPlz.setCellValueFactory(new PropertyValueFactory<>("PLZ"));
        colOrt.setCellValueFactory(new PropertyValueFactory<>("Ort"));
        colGueterV.setCellValueFactory(new PropertyValueFactory<>("Gueterverkehr"));
        colPersonenV.setCellValueFactory(new PropertyValueFactory<>("Personenverkehr"));
        
        
        //erzeugen der spalten in der tableview
        tableView.getColumns().addAll(colUnternehmen, colStrasse, colPlz, colOrt, colGueterV, colPersonenV);
        tableView.getColumns().forEach(column -> column.setPrefWidth(200));
        //erstellung reader objekt und liste mit vorgefertigtem observer pattern
        Reader reader = new Reader();
        ObservableList<Entry> list = FXCollections.observableArrayList();

      
        // das umwaehlen in der combobox sucht fuer die aenderung der suchstrategie
        combobox.setOnAction(e -> {
            String selected = combobox.getValue();
            switch (selected) {
                case "Unternehmen":
                    search.setSearchStrategy(new UnternehmenSearchStrategy());
                    break;
                case "Strasse":
                    search.setSearchStrategy(new StrasseSearchStrategy());
                    break;
                case "PLZ":
                    search.setSearchStrategy(new PlzSearchStrategy());
                    break;
                case "Ort":
                    search.setSearchStrategy(new OrtSearchStrategy());
                    break;
            }

        });

        try {
            //datei wird eingelesen
            reader.read(file);
            /*an jedem eintrag aus der reader list werden die getter aufgerufen
            um dann in der observable list neue eintraege mit den selben werten zu erstellen
            */
            reader.getEntryList()
                    .forEach(entry -> list
                    .add(new Entry(entry.getUnternehmen(), entry.getStrasse(), entry.getPLZ(), entry.getOrt(),
                            entry.getGueterverkehr(), entry.getPersonenverkehr())));

            tableView.setItems(list);

        } catch (IOException e) {

            e.printStackTrace();
        }
        // listener fuer das textfield und die beiden checkboxen. sobald etwas eingetippt wird oder eine checkbox angeklickt wird
        // wird eine neue suche gestartet, welche in einer liste gespeichert wird. die neue liste ersetzt dann die alte.
        textfield.textProperty().addListener((observable, oldValue, newValue)
                -> {

            ObservableList<Entry> newList = search.search(list, newValue, caseSensitive, gueter, perso);
            tableView.setItems(newList);

        });
        
        cbGueter.selectedProperty().addListener((observable,oldValue,newValue)
        ->{
        gueter = cbGueter.isSelected();
        ObservableList<Entry> newList = search.search(list, textfield.getText(), caseSensitive, gueter, perso);
        tableView.setItems(newList);
        
        });
        
         cbPerso.selectedProperty().addListener((observable,oldValue,newValue)
        ->{
        perso = cbPerso.isSelected();
        ObservableList<Entry> newList = search.search(list, textfield.getText(), caseSensitive, gueter, perso);
        tableView.setItems(newList);
        
        });
         
        //gui layout
        borderpane.setCenter(tableView);
        borderpane.setTop(hbox);
        // scene mit borderpane und groesse
        var scene = new Scene(borderpane, 1280, 720);
        
        stage.setScene(scene);

        stage.show();

    }

    

  

}
