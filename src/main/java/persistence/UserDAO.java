package persistence;

import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
	private static UserDAO instance = null;
	private PreparedStatement selectUserGivenPassword;
	private PreparedStatement selectUserForgotPassword;
	private PreparedStatement insertUser;
	private PreparedStatement updateUser;

	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public UserDAO() {
		Connection connection = DatabaseConnection.getConnection();
		try {
			selectUserGivenPassword = connection.prepareStatement("SELECT * FROM main.user WHERE login = ? AND password = ?");
			selectUserForgotPassword = connection.prepareStatement("SELECT * FROM main.user WHERE " +
					"name = ? AND login = ? AND phone = ? AND birthdate = ?");
			updateUser = connection.prepareStatement("UPDATE main.user SET password = ? WHERE id = ?");
			insertUser = connection.prepareStatement("INSERT INTO main.user (" +
					"name, login, password, phone, birthdate) VALUES (?, ?, ?, ?, ?)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
