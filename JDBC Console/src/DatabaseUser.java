public class DatabaseUser {
	private int idUser;
	private String firstName;
	private String lastName;
	private int age;
	private String city;
	private String nickname;
	
	public DatabaseUser () {};
	
	public DatabaseUser(int idUser, String firstName, String lastName, int age, String city, String nickname) {
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.city = city;
		this.nickname = nickname;
	}

	public DatabaseUser(String firstName, String lastName, int age, String city, String nickname) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.city = city;
		this.nickname = nickname;
	}
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return idUser + " " + firstName + " " + lastName + " " + age + " " + city + " " + nickname; 
	}
}
