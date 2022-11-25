package persistence;

import data.Category;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
	private static CategoryDAO instance = null;
	private PreparedStatement selectAllCategories;

	public static CategoryDAO getInstance() {
		if (instance == null) {
			instance = new CategoryDAO();
		}
		return instance;
	}

	public CategoryDAO() {
		Connection connection = DatabaseConnection.getConnection();
		try {
			selectAllCategories = connection.prepareStatement("SELECT * FROM main.category");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Category[] load() {
		ResultSet resultSet;
		List<Category> values = new ArrayList<>();

		try {
			resultSet = selectAllCategories.executeQuery();
			while (resultSet.next()) {
				values.add(Category.valueOf(resultSet.getString("name").toUpperCase()));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return values.toArray(new Category[0]);
	}
}
