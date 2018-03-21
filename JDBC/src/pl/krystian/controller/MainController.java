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
    private Button refreshButton;

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
    
    private ObservableList<Person> perslist = FXCollections.observableArrayList();
    private DBConnection conn = new DBConnection();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableRefresh();
	}
	
	public ObservableList<Person> getPersonList(){
		return perslist;
	}
	
	public void tableInit() {
		idColumn.setCellValueFactory(cellData -> cellData.getValue().getID().asObject());
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().getAge().asObject());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().getCity());
        nicknameColumn.setCellValueFactory(cellData -> cellData.getValue().getNickname());
	}
	
	public void tableRefresh() {
		try {
			Statement stmt = conn.connect("postgres", "qwerty").createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS;");
			tableInit();
			while(rs.next()) {
				int ID = rs.getInt("id_user");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				int age = rs.getInt("age");
				String city = rs.getString("city");
				String nickname = rs.getString("nickname");
				perslist.add(new Person(ID, firstName, lastName, age, city, nickname));
			}
			mainTable.setItems(getPersonList());
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
}
