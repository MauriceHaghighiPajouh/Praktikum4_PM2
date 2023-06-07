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

    @Override
    public void start(Stage stage) {
        

        Label label = new Label("Sortieren nach: ");
        BorderPane borderpane = new BorderPane();
        CheckBox checkbox = new CheckBox("Groß- / Kleinschreibung beachten");
        label.setFont(new Font("Arial", 18));

        HBox hbox = new HBox();

        ComboBox combobox = new ComboBox();
        TextField textfield = new TextField();
        
        checkbox.setOnAction(e->
        {caseSensitive=checkbox.isSelected();}
        );

        hbox.getChildren().add(label);
        hbox.getChildren().add(combobox);
        hbox.getChildren().add(textfield);
        hbox.getChildren().add(checkbox);

        // UNTERNEHMEN;STRASSE;PLZ;ORT;GUETERVERKEHR;PERSONENVERKEHR
        // super schlecht geloest. wollte loop implementieren, aber zu faul
        TableView<Entry> tableView = new TableView<>();
        TableColumn<Entry, String> colUnternehmen = new TableColumn<>("Unternehmen");
        TableColumn<Entry, String> colStrasse = new TableColumn<>("Strasse");
        TableColumn<Entry, String> colPlz = new TableColumn<>("PLZ");
        TableColumn<Entry, String> colOrt = new TableColumn<>("Ort");
        TableColumn<Entry, String> colGueterV = new TableColumn<>("Gueterverkehr");
        TableColumn<Entry, String> colPersonenV = new TableColumn<>("Personenverkehr");

        colUnternehmen.setCellValueFactory(new PropertyValueFactory<>("Unternehmen"));
        colStrasse.setCellValueFactory(new PropertyValueFactory<>("Strasse"));
        colPlz.setCellValueFactory(new PropertyValueFactory<>("PLZ"));
        colOrt.setCellValueFactory(new PropertyValueFactory<>("Ort"));
        colGueterV.setCellValueFactory(new PropertyValueFactory<>("Gueterverkehr"));
        colPersonenV.setCellValueFactory(new PropertyValueFactory<>("Personenverkehr"));

        tableView.getColumns().addAll(colUnternehmen, colStrasse, colPlz, colOrt, colGueterV, colPersonenV);
        tableView.getColumns().forEach(column -> column.setPrefWidth(200));

        Reader reader = new Reader();

        ObservableList<Entry> list = FXCollections.observableArrayList();

        try {
            reader.read(file);
            
            reader.getEntryList()
                    .forEach(entry -> list
                            .add(new Entry(entry.getUnternehmen(), entry.getStrasse(), entry.getPLZ(), entry.getOrt(),
                                    entry.getGueterverkehr(), entry.getPersonenverkehr())));
                                   
            
            tableView.setItems(list);

        } catch (IOException e) {
           
            e.printStackTrace();
        }

        borderpane.setCenter(tableView);
        borderpane.setTop(hbox);

        var scene = new Scene(borderpane, 1280, 720);

        stage.setScene(scene);

        stage.show();

    }

}
