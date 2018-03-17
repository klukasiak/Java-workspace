package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class MainController implements Initializable{

    @FXML
    private ChoiceBox<String> selectTypeInto;

    @FXML
    private ChoiceBox<String> selectType;

    @FXML
    private ChoiceBox<String> selectTypeFrom;

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
    	selectType.setValue("Pole");
    }
    
    @FXML
    private void change(ActionEvent event) {
    	if(selectType.getValue() == "Pole") {
    		selectTypeInto.setItems(FXCollections.observableArrayList("m^2", "a", "ha"));
    		selectTypeFrom.setItems(FXCollections.observableArrayList("m^2", "a", "ha"));
    	}
    	if(selectType.getValue() == "D³ugoœæ") {
    		selectTypeInto.setItems(FXCollections.observableArrayList("m", "cm", "dm"));
    		selectTypeFrom.setItems(FXCollections.observableArrayList("m", "cm", "dm"));
    	}
    	if(selectType.getValue() == "Pojemnoœæ") {
    		selectTypeInto.setItems(FXCollections.observableArrayList("l", "ml", "cl"));
    		selectTypeFrom.setItems(FXCollections.observableArrayList("l", "ml", "cl"));
    	}
    	if(selectType.getValue() == "Czas") {
    		selectTypeInto.setItems(FXCollections.observableArrayList("h", "min", "s"));
    		selectTypeFrom.setItems(FXCollections.observableArrayList("h", "min", "s"));
    	}
    	if(selectType.getValue() == "Masa") {
    		selectTypeInto.setItems(FXCollections.observableArrayList("kg", "g", "t"));
    		selectTypeFrom.setItems(FXCollections.observableArrayList("kg", "g", "t"));
    	}
    	if(selectType.getValue() == "Prêdkoœæ") {
    		selectTypeInto.setItems(FXCollections.observableArrayList("km/h", "m/s", "mil/h"));
    		selectTypeFrom.setItems(FXCollections.observableArrayList("km/h", "m/s", "mil/h"));
    	}
    }
    
    @FXML
    private void convert(ActionEvent event) {
    	final double M2_TO_A = 0.01;
    	final double M2_TO_HA = 0.0001;
    	final double A_TO_HA = 0.01;
    	
    	String a = textFrom.getText();
    	double n = Double.parseDouble(a);
    	if(selectTypeFrom.getValue() == "m^2") {
    		if(selectTypeInto.getValue() == "m^2") {
        		textInto.setText(String.valueOf(n));
        	}
    		if(selectTypeInto.getValue() == "a") {
        		textInto.setText(String.valueOf(n*M2_TO_A));
        	}
    		if(selectTypeInto.getValue() == "ha") {
        		textInto.setText(String.valueOf(n*M2_TO_HA));
        	}
    	}
    	if(selectTypeFrom.getValue() == "a") {
    		if(selectTypeInto.getValue() == "m^2") {
        		textInto.setText(String.valueOf(n*(1/M2_TO_A)));
        	}
    		if(selectTypeInto.getValue() == "a") {
        		textInto.setText(String.valueOf(n));
        	}
    		if(selectTypeInto.getValue() == "ha") {
        		textInto.setText(String.valueOf(n*A_TO_HA));
        	}
    	}
    	if(selectTypeFrom.getValue() == "ha") {
    		if(selectTypeInto.getValue() == "m^2") {
        		textInto.setText(String.valueOf(n*(1/M2_TO_HA)));
        	}
    		if(selectTypeInto.getValue() == "a") {
        		textInto.setText(String.valueOf(n*(1/A_TO_HA)));
        	}
    		if(selectTypeInto.getValue() == "ha") {
        		textInto.setText(String.valueOf(n));
        	}
    	}
    }


}
