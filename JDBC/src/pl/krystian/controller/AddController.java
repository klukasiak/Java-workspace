package pl.krystian.controller;

import java.sql.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import pl.krystian.DBConnection;

public class AddController {

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
	private Button addButton;

	@FXML
	private Label statusLabel;

	DBConnection conn = new DBConnection();

	@FXML
	private void addToDatabase(ActionEvent event) {
		try {
			PreparedStatement stmt = conn.connect("postgres", "qwerty").prepareStatement(
					"INSERT INTO users (firstname, lastname, age, city, nickname) VALUES (?, ?, ?, ?, ?);");
			stmt.setString(1, String.valueOf(firstNameField.getText()));
			stmt.setString(2, String.valueOf(lastNameField.getText()));
			stmt.setInt(3, Integer.parseInt(ageField.getText()));
			stmt.setString(4, String.valueOf(cityField.getText()));
			stmt.setString(5, String.valueOf(nicknameField.getText()));
			stmt.executeUpdate();
			statusLabel.setText("User added to database");
			statusLabel.setTextFill(Color.GREEN);
		} catch (Exception e) {
			statusLabel.setText("Error");
			statusLabel.setTextFill(Color.RED);
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
