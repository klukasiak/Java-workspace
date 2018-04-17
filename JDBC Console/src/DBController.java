import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DBController {


	private static String username;
	private static String password;
	private static DBConnection conn = new DBConnection();
	
	public void showAllRecords() {
		try {
			conn.connect(username, password);
			Statement stmt = conn.connect(username, password).createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from users ORDER BY id_user;");
			while (rs.next()) {
				int userID = rs.getInt("id_user");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				int age = rs.getInt("age");
				String city = rs.getString("city");
				String nickname = rs.getString("nickname");
				DatabaseUser user = new DatabaseUser(userID, firstName, lastName, age, city, nickname);
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public void addNewUser() {
		try {
			DatabaseUser user = createNewUser();
			conn.connect(username, password);
			PreparedStatement stmt = conn.connect(username, password).prepareStatement(
					"INSERT INTO users (firstname, lastname, age, city, nickname) VALUES (?, ?, ?, ?, ?);");
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setInt(3, user.getAge());
			stmt.setString(4, user.getCity());
			stmt.setString(5, user.getNickname());
			stmt.executeUpdate();
			System.out.println("Uzytkownik dodany");
		} catch (Exception e) {
			System.out.println("Nie udalo sie dodac uzytkownika");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public DatabaseUser createNewUser() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Podaj imie uzytkownika");
		String firstName = sc.nextLine();
		System.out.println("Podaj nazwisko uzytkownika");
		String lastName = sc.nextLine();
		System.out.println("Podaj wiek uzytkownika");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.println("Podaj miasto uzytkownika");
		String city = sc.nextLine();
		System.out.println("Podaj ksywke uzytkownika");
		String nickname = sc.nextLine();
		DatabaseUser user = new DatabaseUser(firstName, lastName, age, city, nickname);
		sc.close();
		return user;
	}

	public void editUser() {
		try {
			Scanner sc = new Scanner(System.in);
			showAllRecords();
			System.out.println("Podaj ID uzytkownika do edycji");
			int id = sc.nextInt();
			sc.nextLine();
			System.out.println("Podaj nowe dane uzytkonika: ");
			DatabaseUser user = createNewUser();
			PreparedStatement stmt = conn.connect(username, password).prepareStatement(
					"UPDATE users SET firstname=? , lastname=? , age=? , city=? , nickname=? WHERE id_user=? ;");
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setInt(3, user.getAge());
			stmt.setString(4, user.getCity());
			stmt.setString(5, user.getNickname());
			stmt.setInt(6, id);
			stmt.executeUpdate();
			System.out.println("Uzytkownik edytowany");
			sc.close();
		} catch (Exception e) {
			System.out.println("Nie udalo sie edytowac uzytkownika");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public void removeUser() {
		try {
			Scanner sc = new Scanner(System.in);
			showAllRecords();
			System.out.println("Podaj ID uzytkownika do usuniecia");
			int id = sc.nextInt();
			sc.nextLine();
			PreparedStatement stmt = conn.connect(username, password).prepareStatement(
					"DELETE FROM users WHERE id_user=?;");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println("Uzytkownik usuniety");
			sc.close();
		} catch (Exception e) {
			System.out.println("Nie udalo sie usunac uzytkownika");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public boolean connectToDatabase() {
		try {
			conn.connect(username, password);
			System.out.println("Zalogowano do bazy");
			return true;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		DBController.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		DBController.password = password;
	}

	public static DBConnection getConn() {
		return conn;
	}

	public static void setConn(DBConnection conn) {
		DBController.conn = conn;
	}
	
	
}
