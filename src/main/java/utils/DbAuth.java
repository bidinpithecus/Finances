package utils;

public class DbAuth {
	private static final String url = "jdbc:postgresql://5432:5432/finances";
	private static final String login = "admin";
	private static final String password = "admin";

	public String getUrl() {
		return url;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return login;
	}
}
