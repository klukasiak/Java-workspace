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

public class MainController implements Initializable {

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
		selectType.setItems(
				FXCollections.observableArrayList("Pole", "D³ugoœæ", "Pojemnoœæ", "Czas", "Masa", "Prêdkoœæ"));
		selectType.setValue("Pole");
	}

	@FXML
	private void change(ActionEvent event) {
		if (selectType.getValue() == "Pole") {
			selectTypeInto.setItems(FXCollections.observableArrayList("m^2", "a", "ha"));
			selectTypeFrom.setItems(FXCollections.observableArrayList("m^2", "a", "ha"));
		}
		if (selectType.getValue() == "D³ugoœæ") {
			selectTypeInto.setItems(FXCollections.observableArrayList("m", "cm", "dm"));
			selectTypeFrom.setItems(FXCollections.observableArrayList("m", "cm", "dm"));
		}
		if (selectType.getValue() == "Pojemnoœæ") {
			selectTypeInto.setItems(FXCollections.observableArrayList("l", "ml", "cl"));
			selectTypeFrom.setItems(FXCollections.observableArrayList("l", "ml", "cl"));
		}
		if (selectType.getValue() == "Czas") {
			selectTypeInto.setItems(FXCollections.observableArrayList("h", "min", "s"));
			selectTypeFrom.setItems(FXCollections.observableArrayList("h", "min", "s"));
		}
		if (selectType.getValue() == "Masa") {
			selectTypeInto.setItems(FXCollections.observableArrayList("kg", "g", "t"));
			selectTypeFrom.setItems(FXCollections.observableArrayList("kg", "g", "t"));
		}
		if (selectType.getValue() == "Prêdkoœæ") {
			selectTypeInto.setItems(FXCollections.observableArrayList("km/h", "m/s", "mil/h"));
			selectTypeFrom.setItems(FXCollections.observableArrayList("km/h", "m/s", "mil/h"));
		}
	}

	@FXML
    private void convert(ActionEvent event) {
    	final double M2_TO_A = 0.01;
    	final double M2_TO_HA = 0.0001;
    	final double A_TO_HA = 0.01;
    	final double M_TO_DM = 10;
    	final double M_TO_CM = 100;
    	final double DM_TO_CM = 0.1;
    	final double L_TO_ML = 1000;
    	final double L_TO_CL = 100;
    	final double ML_TO_CL = 0.1;
    	final double H_TO_MIN = 60;
    	final double H_TO_S = 3600;
    	final double MIN_TO_S = 60;
    	final double KG_TO_G = 1000;
    	final double KG_TO_T = 0.001;
    	final double G_TO_T = 0.000001;
    	final double MS_TO_KMH = 3.6;
    	final double KMH_TO_MPH = 0.61;
    	final double MS_TO_MPH = 2.24;
    	
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
    	if(selectTypeFrom.getValue() == "m") {
    		if(selectTypeInto.getValue() == "m") {
        		textInto.setText(String.valueOf(n));
        	}
    		if(selectTypeInto.getValue() == "cm") {
        		textInto.setText(String.valueOf(n*M_TO_CM));
        	}
    		if(selectTypeInto.getValue() == "dm") {
        		textInto.setText(String.valueOf(n*M_TO_DM));
        	}
    	}
    	if(selectTypeFrom.getValue() == "cm") {
    		if(selectTypeInto.getValue() == "m") {
        		textInto.setText(String.valueOf(n*(1/M_TO_CM)));
        	}
    		if(selectTypeInto.getValue() == "cm") {
        		textInto.setText(String.valueOf(n));
        	}
    		if(selectTypeInto.getValue() == "dm") {
        		textInto.setText(String.valueOf(n*(1/DM_TO_CM)));
        	}
    	}
    	if(selectTypeFrom.getValue() == "dm") {
    		if(selectTypeInto.getValue() == "m") {
        		textInto.setText(String.valueOf(n*(1/M_TO_DM)));
        	}
    		if(selectTypeInto.getValue() == "cm") {
        		textInto.setText(String.valueOf(n*DM_TO_CM));
        	}
    		if(selectTypeInto.getValue() == "dm") {
        		textInto.setText(String.valueOf(n));
        	}
    	}
    	if(selectTypeFrom.getValue() == "l") {
    		if(selectTypeInto.getValue() == "l") {
        		textInto.setText(String.valueOf(n));
        	}
    		if(selectTypeInto.getValue() == "ml") {
        		textInto.setText(String.valueOf(n*L_TO_ML));
        	}
    		if(selectTypeInto.getValue() == "cl") {
        		textInto.setText(String.valueOf(n*L_TO_CL));
        	}
    	}
    	if(selectTypeFrom.getValue() == "ml") {
    		if(selectTypeInto.getValue() == "l") {
        		textInto.setText(String.valueOf(n*(1/L_TO_ML)));
        	}
    		if(selectTypeInto.getValue() == "ml") {
        		textInto.setText(String.valueOf(n));
        	}
    		if(selectTypeInto.getValue() == "cl") {
        		textInto.setText(String.valueOf(n*ML_TO_CL));
        	}
    	}
    	if(selectTypeFrom.getValue() == "cl") {
    		if(selectTypeInto.getValue() == "l") {
        		textInto.setText(String.valueOf(n*(1/L_TO_CL)));
        	}
    		if(selectTypeInto.getValue() == "ml") {
        		textInto.setText(String.valueOf(n*(1/ML_TO_CL)));
        	}
    		if(selectTypeInto.getValue() == "cl") {
        		textInto.setText(String.valueOf(n));
        	}
    	}
    	if(selectTypeFrom.getValue() == "h") {
    		if(selectTypeInto.getValue() == "h") {
        		textInto.setText(String.valueOf(n));
        	}
    		if(selectTypeInto.getValue() == "min") {
        		textInto.setText(String.valueOf(n*H_TO_MIN));
        	}
    		if(selectTypeInto.getValue() == "s") {
        		textInto.setText(String.valueOf(n*H_TO_S));
        	}
    	}
    	if(selectTypeFrom.getValue() == "min") {
    		if(selectTypeInto.getValue() == "h") {
        		textInto.setText(String.valueOf(n*(1/H_TO_MIN)));
        	}
    		if(selectTypeInto.getValue() == "min") {
        		textInto.setText(String.valueOf(n));
        	}
    		if(selectTypeInto.getValue() == "s") {
        		textInto.setText(String.valueOf(n*MIN_TO_S));
        	}
    	}
    	if(selectTypeFrom.getValue() == "s") {
    		if(selectTypeInto.getValue() == "h") {
        		textInto.setText(String.valueOf(n*(1/H_TO_S)));
        	}
    		if(selectTypeInto.getValue() == "min") {
        		textInto.setText(String.valueOf(n*(1/MIN_TO_S)));
        	}
    		if(selectTypeInto.getValue() == "s") {
        		textInto.setText(String.valueOf(n));
        	}
    	}
    	if(selectTypeFrom.getValue() == "kg") {
    		if(selectTypeInto.getValue() == "kg") {
        		textInto.setText(String.valueOf(n));
        	}
    		if(selectTypeInto.getValue() == "g") {
        		textInto.setText(String.valueOf(n*KG_TO_G));
        	}
    		if(selectTypeInto.getValue() == "t") {
        		textInto.setText(String.valueOf(n*KG_TO_T));
        	}
    	}
    	if(selectTypeFrom.getValue() == "g") {
    		if(selectTypeInto.getValue() == "kg") {
        		textInto.setText(String.valueOf(n*(1/KG_TO_G)));
        	}
    		if(selectTypeInto.getValue() == "g") {
        		textInto.setText(String.valueOf(n));
        	}
    		if(selectTypeInto.getValue() == "t") {
        		textInto.setText(String.valueOf(n*G_TO_T));
        	}
    	}
    	if(selectTypeFrom.getValue() == "t") {
    		if(selectTypeInto.getValue() == "kg") {
        		textInto.setText(String.valueOf(n*(1/KG_TO_T)));
        	}
    		if(selectTypeInto.getValue() == "g") {
        		textInto.setText(String.valueOf(n*(1*G_TO_T)));
        	}
    		if(selectTypeInto.getValue() == "t") {
        		textInto.setText(String.valueOf(n));
        	}
    	}
    	if(selectTypeFrom.getValue() == "km/h") {
    		if(selectTypeInto.getValue() == "km/h") {
        		textInto.setText(String.valueOf(n));
        	}
    		if(selectTypeInto.getValue() == "m/s") {
        		textInto.setText(String.valueOf(n*(1/MS_TO_KMH)));
        	}
    		if(selectTypeInto.getValue() == "mil/h") {
        		textInto.setText(String.valueOf(n*KMH_TO_MPH));
        	}
    	}
    	if(selectTypeFrom.getValue() == "m/s") {
    		if(selectTypeInto.getValue() == "km/h") {
        		textInto.setText(String.valueOf(n*MS_TO_KMH));
        	}
    		if(selectTypeInto.getValue() == "m/s") {
        		textInto.setText(String.valueOf(n));
        	}
    		if(selectTypeInto.getValue() == "mil/h") {
        		textInto.setText(String.valueOf(n*MS_TO_MPH));
        	}
    	}
    	if(selectTypeFrom.getValue() == "mil/h") {
    		if(selectTypeInto.getValue() == "km/h") {
        		textInto.setText(String.valueOf(n*(1/KMH_TO_MPH)));
        	}
    		if(selectTypeInto.getValue() == "m/s") {
        		textInto.setText(String.valueOf(n*(1/MS_TO_MPH)));
        	}
    		if(selectTypeInto.getValue() == "mil/h") {
        		textInto.setText(String.valueOf(n));
        	}
    	}
    }

}
