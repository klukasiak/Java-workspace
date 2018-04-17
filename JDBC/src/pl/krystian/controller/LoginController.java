package pl.krystian.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class LoginController implements Initializable {

	private String username;
	private String password;

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
		username = String.valueOf(usernameTextField.getText());
		password = String.valueOf(passwordTextField.getText());

		try {
			conn.connect(username, password);

			Parent root;

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("pl/krystian/view/MainPane.fxml"));
			root = loader.load();
			MainController controller = loader.<MainController>getController();
			controller.setUsername(username);
			controller.setPassword(password);
			Stage stage = new Stage();
			stage.setTitle("SQL CRUD Panel - LOGGED IN");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();

			if (rememberCheckbox.isSelected()) {
				File file = new File("Account.dat");
				FileWriter fileWriter = new FileWriter("Account.dat");
				BufferedWriter writer = new BufferedWriter(fileWriter);
				boolean fileExists = file.exists();
				if (!fileExists) {
					try {
						fileExists = file.createNewFile();
					} catch (Exception e) {
						System.err.println(e.getClass().getName() + ": " + e.getMessage());
					}
				}
				writer.write(usernameTextField.getText());
				writer.newLine();
				writer.write(passwordTextField.getText());
				writer.close();
			}
		} catch (Exception e) {
			connectionLabel.setText("Wrong data :(");
			connectionLabel.setTextFill(Color.RED);
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			File file = new File("Account.dat");
			Scanner sc = new Scanner(file);
			String user = sc.nextLine();
			String pass = sc.nextLine();
			usernameTextField.setText(user);
			passwordTextField.setText(pass);
			sc.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}

}
