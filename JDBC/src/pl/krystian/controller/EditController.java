package pl.krystian.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import pl.krystian.Person;

public class EditController implements Initializable{

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField nicknameField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button editButton;
    
    @FXML
    private Label idLabel;

    private Person person;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int id = person.getID().intValue();
		idLabel.setText("ID: " + String.valueOf(id));
		idLabel.setTextFill(Color.WHITE);
		String firstName = person.getFirstName().getValue();
		firstNameField.setText(firstName);
		String lastName = person.getLastName().getValue();
		lastNameField.setText(lastName);
		int age = person.getAge().intValue();
		ageField.setText(String.valueOf(age));
		String city = person.getCity().getValue();
		cityField.setText(city);
		String nickname = person.getNickname().getValue();
		nicknameField.setText(nickname);
		
	}

    public void setPerson(Person person) {
    	this.person = person;
    }
    
    public Person getPerson() {
    	return person;
    }

}
