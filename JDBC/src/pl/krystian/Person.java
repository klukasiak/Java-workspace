package pl.krystian;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	private final IntegerProperty ID;
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final IntegerProperty age;
	private final StringProperty city;
	private final StringProperty nickname;
	
	
	public Person() {
		this(0, null, null, 0, null, null);
	}
	
	public Person(int ID, String firstName, String lastName, int age, String city, String nickname) {
		this.ID = new SimpleIntegerProperty(ID);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.age = new SimpleIntegerProperty(age);
		this.city = new SimpleStringProperty(city);
		this.nickname = new SimpleStringProperty(nickname);
	}
	
	public IntegerProperty getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID.set(ID);
	}
	
	public StringProperty getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public StringProperty getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public IntegerProperty getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age.set(age);
	}
	
	public StringProperty getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city.set(city);
	}
	
	public StringProperty getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname.set(nickname);
	}
}
