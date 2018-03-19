package pl.krystian.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pl.krystian.DBConnection;

public class LoginController{

    @FXML
    private Label titleLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usernameTextfield;

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
    private void test(ActionEvent event) {
    	DBConnection conn = new DBConnection ();
    	try {
    		Statement stmt = conn.connect().createStatement();
    	    String sql;
    	    sql = "SELECT * from users";
    	    ResultSet rs = stmt.executeQuery(sql);
    	    while(rs.next()){
    	    	int id  = rs.getInt("id_user");
    	        int age = rs.getInt("age");
    	        String first = rs.getString("firstname");
    	        String last = rs.getString("lastname");
    	        String nick = rs.getString("nickname");
    	        String city = rs.getString("city");

    	        System.out.print("User ID: " + id);
    	        System.out.print(", Age: " + age);
    	        System.out.print(", First Name: " + first);
    	        System.out.print(", Last Name: " + last);
    	        System.out.print(", Nickname: " + nick);
    	        System.out.print(", City: " + city);
    	      }
    	      //STEP 6: Clean-up environment
    	      rs.close();
    	      stmt.close();
    	} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
    }
}

