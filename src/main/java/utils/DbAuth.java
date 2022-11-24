package utils;

public class DbAuth {
	private static final String url = "jdbc:postgresql://localhost:5432/finances";
	private static final String user = "admin";
	private static final String password = "admin";

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
}
