package pl.krystian.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.krystian.DBConnection;

public class LoginController {

	@FXML
	private Label titleLabel;

	@FXML
	private Label usernameLabel;

	@FXML
	private TextField usernameTextField;

	@FXML
	private Label passwordLabel;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private Button signInButton;

	@FXML
	private CheckBox rememberCheckbox;

	@FXML
	private Label rememberLabel;

	@FXML
	private Label registerLabel;

	@FXML
	private Button registerButton;

	@FXML
	private Label connectionLabel;

	@FXML
	private void test(ActionEvent event) {
		DBConnection conn = new DBConnection();
		String username = String.valueOf(usernameTextField.getText());
		String password = String.valueOf(passwordTextField.getText());

		try {
			conn.connect(username, password);

			Parent root;

			root = FXMLLoader.load(getClass().getClassLoader().getResource("pl/krystian/view/MainPane.fxml"));
			Stage stage = new Stage();
			stage.setTitle("SQL CRUD Panel - LOGGED IN");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
		} catch (Exception e) {
			connectionLabel.setText("B³edne dane :(");
			connectionLabel.setTextFill(Color.RED);
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
