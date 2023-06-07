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

        Label label = new Label("Suchen nach: ");
        BorderPane borderpane = new BorderPane();
        CheckBox checkbox = new CheckBox("Groß- / Kleinschreibung beachten");
        label.setFont(new Font("Arial", 18));
        CheckBox cbGueter = new CheckBox("Nur Gueterverkehr anzeigen");
        CheckBox cbPerso = new CheckBox("Nur Personenverkehr anzeigen");

        HBox hbox = new HBox();

        ComboBox<String> combobox = new ComboBox<>();

        combobox.getItems().addAll("Unternehmen", "Strasse", "PLZ", "Ort");
        combobox.setValue("Unternehmen");

        TextField textfield = new TextField();
        Search search = new Search();

        checkbox.setOnAction(e
                -> {
            caseSensitive = checkbox.isSelected();
        }
        );

        cbGueter.setOnAction(e
                -> {
            gueter = checkbox.isSelected();
        }
        );
        
        
        cbPerso.setOnAction(e
                -> {
            perso = checkbox.isSelected();
        }
        );

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

        textfield.textProperty().addListener((observable, oldValue, newValue)
                -> {
            String ca = combobox.getValue();
            ObservableList<Entry> newList = search.search(list, newValue, caseSensitive, ca, gueter, perso); 
            tableView.setItems(newList);

        });

        borderpane.setCenter(tableView);
        borderpane.setTop(hbox);

        var scene = new Scene(borderpane, 1280, 720);

        stage.setScene(scene);

        stage.show();

    }

}
