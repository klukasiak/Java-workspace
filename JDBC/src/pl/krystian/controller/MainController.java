package pl.krystian.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.krystian.DBConnection;
import pl.krystian.Person;

public class MainController implements Initializable {

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

	@FXML
	private Label idLabel;

	private ObservableList<Person> perslist = FXCollections.observableArrayList();
	private DBConnection conn = new DBConnection();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableRefresh();
		mainTable.addEventFilter(MouseEvent.MOUSE_CLICKED, x -> showValue());
	}

	public ObservableList<Person> getPersonList() {
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
			while (rs.next()) {
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

	public void showValue() {
		Person person = mainTable.getSelectionModel().getSelectedItem();
		int id = person.getID().intValue();
		idLabel.setText(String.valueOf(id));
		idLabel.setTextFill(Color.WHITE);
		String firstName = person.getFirstName().getValue();
		firstNameLabel.setText(firstName);
		firstNameLabel.setTextFill(Color.WHITE);
		String lastName = person.getLastName().getValue();
		lastNameLabel.setText(lastName);
		lastNameLabel.setTextFill(Color.WHITE);
		int age = person.getAge().intValue();
		ageLabel.setText(String.valueOf(age));
		ageLabel.setTextFill(Color.WHITE);
		String city = person.getCity().getValue();
		cityLabel.setText(city);
		cityLabel.setTextFill(Color.WHITE);
		String nickname = person.getNickname().getValue();
		nicknameLabel.setText(nickname);
		nicknameLabel.setTextFill(Color.WHITE);
	}

	@FXML
	private void addNewUser(ActionEvent event) {
		try {
			Parent root;

			root = FXMLLoader.load(getClass().getClassLoader().getResource("pl/krystian/view/AddPane.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Add new user");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

}
