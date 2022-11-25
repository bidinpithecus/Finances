package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				DbAuth dbAuth = new DbAuth();

				Connection connection = DriverManager.getConnection(dbAuth.getURL(), dbAuth.getUSER(), dbAuth.getPASSWORD());
				return connection;
			} catch (ClassNotFoundException e) {
				System.err.println("Driver not found");
			} catch (SQLException e) {
				System.err.println("Unable to connect");
			}
		}
		return connection;
	}
}
