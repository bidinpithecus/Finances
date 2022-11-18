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

		Calendar spentDate0 = Calendar.getInstance();
		spentDate0.set(2020, Calendar.FEBRUARY, 1);
		Spent spent = new Spent(UUID.randomUUID(), "Café", spentDate0, "Caféeee", 36, Category.FOOD);
		finances.newSpent(spent);

		Calendar spentDate1 = Calendar.getInstance();
		spentDate1.set(2020, Calendar.MARCH, 1);
		spent = new Spent(UUID.randomUUID(), "Cabo", spentDate1, "2 Cabos usb", 18, Category.FUN);
		finances.newSpent(spent);

		Calendar spentDate2 = Calendar.getInstance();
		spentDate2.set(2020, Calendar.APRIL, 1);
		spent = new Spent(UUID.randomUUID(), "Uber", spentDate2, "Faculdade", 14, Category.TRANSPORTATION);
		finances.newSpent(spent);

		Calendar spentDate3 = Calendar.getInstance();
		spentDate3.set(2020, Calendar.APRIL, 1);
		spent = new Spent(UUID.randomUUID(), "Uber", spentDate3, "Casa", 16, Category.TRANSPORTATION);
		finances.newSpent(spent);

		new HomeGUI(finances);
	}
}
