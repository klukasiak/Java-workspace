package pl.krystian;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public Connection connect() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"qwerty");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		System.out.println("Podlaczono do bazy :)");
		return c;
		
	}
}
