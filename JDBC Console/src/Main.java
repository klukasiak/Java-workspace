import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class Main {

	static String username;
	static String password;
	public static DBConnection conn = new DBConnection();

	public static void main(String[] args) {
		boolean login = false;
		Scanner sc = new Scanner(System.in);
		while (!login) {
			System.out.println("Podaj nazwe uzytkownika");
			username = sc.nextLine();
			System.out.println("Podaj haslo");
			password = sc.nextLine();
			login = connectToDatabase(username, password);
		}
		boolean exit = false;
		int input;
		while (!exit) {
			printMenu();
			input = sc.nextInt();
			sc.nextLine();
			switch (input) {
			case 1:
				showAllRecords(username, password);
				break;
			case 2:
				addNewUser(username, password);
				break;
			case 3:
				editUser(username, password);
				break;
			case 5:
				exit = true;
				break;
			default:
				System.out.println("Zly wybor");
			}
		}
		sc.close();
	}

	public static boolean connectToDatabase(String username, String password) {
		try {
			conn.connect(username, password);
			System.out.println("Zalogowano do bazy");
			return true;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

	public static void printMenu() {
		System.out.println();
		System.out.println("Wybierz co chcesz teraz zrobic?");
		System.out.println("1. Wypisz wszystkich uzytkownikow");
		System.out.println("2. Dodaj uzytkownika");
		System.out.println("5. Wyjdz z programu");
	}

	public static void showAllRecords(String username, String password) {
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

	public static void addNewUser(String username, String password) {
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

	public static DatabaseUser createNewUser() {
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

	public static void editUser(String username, String password) {
		try {
			Scanner sc = new Scanner(System.in);
			showAllRecords(username, password);
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
	
	public static void removeUser(String username, String password) {
		try {
			Scanner sc = new Scanner(System.in);
			showAllRecords(username, password);
			System.out.println("Podaj ID uzytkownika do usuniecia");
			int id = sc.nextInt();
			sc.nextLine();
			PreparedStatement stmt = conn.connect(username, password).prepareStatement(
					"DELETE FROM users WHERE id=?;");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println("Uzytkownik usuniety");
			sc.close();
		} catch (Exception e) {
			System.out.println("Nie udalo sie usunac uzytkownika");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}