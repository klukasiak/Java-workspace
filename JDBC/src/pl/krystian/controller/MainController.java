package pl.krystian.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.krystian.DBConnection;
import pl.krystian.Person;

public class MainController implements Initializable{

    @FXML
    private TableView<Person> mainTable;

    @FXML
    private TableColumn<Person, Integer> idColumn;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private TableColumn<Person, Integer> ageColumn;

    @FXML
    private TableColumn<Person, String> cityColumn;

    @FXML
    private TableColumn<Person, String> nicknameColumn;

    @FXML
    private Label titleLabel;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label nicknameLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LoginController main = new LoginController();
		String username = main.getUsername();
		String password = main.getPassword();
		DBConnection conn = new DBConnection();
		try {
			Statement stmt = conn.connect("postgres", "qwerty").createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS;");
			ObservableList<Person> perslist = FXCollections.observableArrayList();
			while(rs.next()) {
				Person person = new Person();
				person.setID(rs.getInt("id_user"));
				person.setFirstName(rs.getString("firstname"));
				person.setLastName(rs.getString("lastname"));
				person.setAge(rs.getInt("age"));
				person.setCity(rs.getString("city"));
				person.setNickname(rs.getString("nickname"));
				perslist.add(person);
			}
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		

		
	}

}
