package hawhamburg.pm2_praktikum4;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        var label = new Label();
        BorderPane borderpane = new BorderPane(label);

        HBox hbox = new HBox();

        ComboBox combobox = new ComboBox();
        TextField textfield = new TextField();

        hbox.getChildren().add(combobox);
        hbox.getChildren().add(textfield);

        //UNTERNEHMEN;STRASSE;PLZ;ORT;GUETERVERKEHR;PERSONENVERKEHR
        TableView tableView = new TableView();

        
       
        
        
        
        borderpane.setCenter(tableView);
        borderpane.setTop(hbox);

        var scene = new Scene(borderpane, 640, 480);

        stage.setScene(scene);

        stage.show();

    }

}
