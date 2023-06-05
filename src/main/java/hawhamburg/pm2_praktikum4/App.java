package hawhamburg.pm2_praktikum4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        

        var label = new Label();
        BorderPane borderpane = new BorderPane(label);
        
        
        
         //UNTERNEHMEN;STRASSE;PLZ;ORT;GUETERVERKEHR;PERSONENVERKEHR
        TableView tableView = new TableView();
        
        borderpane.setCenter(tableView);
        
        TableColumn<String,String> column1 = new TableColumn<>("test");
        
        var scene = new Scene(borderpane,640,480);
        
        
        stage.setScene(scene);
        
        stage.show();
       
        
        
    }

  

}