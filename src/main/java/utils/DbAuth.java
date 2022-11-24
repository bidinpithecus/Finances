package utils;

public class DbAuth {
	private static final String URL = "jdbc:postgresql://localhost:5432/finances";
	private static final String USER = "admin";
	private static final String PASSWORD = "admin";

	public String getURL() {
		return URL;
	}

	public String getUSER() {
		return USER;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}
}
