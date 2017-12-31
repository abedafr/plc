/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.PlcFacade;

/**
 * FXML Controller class
 *
 * @author Abed
 */
public class MyViewController implements Initializable {

    @FXML
    JFXTextField path = new JFXTextField();

    @FXML
    JFXButton browse = new JFXButton();

    @FXML
    JFXButton convert = new JFXButton();

    @FXML
    JFXButton cancel = new JFXButton();

    PlcFacade facade = new PlcFacade();
    FileChooser fileChooser = new FileChooser();

    public void convert(ActionEvent actionEvent) throws IOException {
        facade.convert(path.getText());
        convert.setDisable(true);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Stage stage = (Stage) util.Session.getAttribut("stage");
        path.setEditable(false);
        convert.setDisable(true);
        browse.setOnAction((ActionEvent e) -> {
            configureFileChooser(fileChooser); 
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                path.setText(file.getAbsolutePath());
                convert.setDisable(false);
            }
        });

        cancel.setOnAction((e) -> {
            System.exit(0);
        });

    }
    
    private static void configureFileChooser(final FileChooser fileChooser) {      
            fileChooser.setTitle("Open Text File");
//            fileChooser.setInitialDirectory(
//                new File(System.getProperty("user.home"))
//            );                 
            fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("All Images", "*.*"),
//                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
            );
    }

}
