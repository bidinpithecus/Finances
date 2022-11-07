package presentation;

import business.Finances;
import data.User;

import java.util.Arrays;
import java.util.Calendar;

public class Main {
	public static void main(String[] args) {
		Finances finances = new Finances();
		Calendar birthDate = Calendar.getInstance();
		birthDate.set(1111, Calendar.FEBRUARY, 1);

		User user = new User("Abluble", "user", "123".toCharArray(), "123123123", birthDate);
		finances.newUser(user);
		finances.login("user", new char[]{'1','2','3'});

		new LoginGUI(finances);
	}
}
