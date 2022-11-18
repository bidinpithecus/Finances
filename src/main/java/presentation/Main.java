package presentation;

import business.Finances;
import data.Category;
import data.Spent;
import data.User;

import java.util.Calendar;
import java.util.UUID;

public class Main {
	public static void main(String[] args) {
		Finances finances = new Finances();
		Calendar birthDate = Calendar.getInstance();
		birthDate.set(1111, Calendar.FEBRUARY, 1);

		User user = new User("User", "user", "123".toCharArray(), "123123123", birthDate);
		finances.newUser(user);
		finances.login("user", new char[]{'1', '2', '3'});

		Calendar spentDate = Calendar.getInstance();
		spentDate.set(2020, Calendar.FEBRUARY, 1);
		Spent spent = new Spent(UUID.randomUUID(), "Café", spentDate, "Caféeee", 36, Category.FOOD);
		finances.newSpent(spent);

		spentDate.set(2020, Calendar.FEBRUARY, 1);
		spent = new Spent(UUID.randomUUID(), "Cabo", spentDate, "2 Cabos usb", 18, Category.FUN);
		finances.newSpent(spent);

		spentDate.set(2020, Calendar.FEBRUARY, 1);
		spent = new Spent(UUID.randomUUID(), "Uber", spentDate, "Faculdade", 14, Category.TRANSPORTATION);
		finances.newSpent(spent);

		spentDate.set(2020, Calendar.FEBRUARY, 1);
		spent = new Spent(UUID.randomUUID(), "Uber", spentDate, "Casa", 16, Category.TRANSPORTATION);
		finances.newSpent(spent);

		new HomeGUI(finances);
	}
}
