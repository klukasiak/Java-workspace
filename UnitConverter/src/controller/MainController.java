package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class MainController implements Initializable{

    @FXML
    private ChoiceBox<?> selectTypeInto;

    @FXML
    private ChoiceBox<?> selectType;

    @FXML
    private ChoiceBox<?> selectTypeFrom;

    @FXML
    private TextArea textFrom;

    @FXML
    private TextArea textInto;

    @FXML
    private Button convertButton;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	convertButton.setText("Convert");
    	selectType.setItems(FXCollections.observableArrayList("Pole", "D³ugoœæ", "Pojemnoœæ", "Czas", "Masa", "Prêdkoœæ")
    			);
    }

}
