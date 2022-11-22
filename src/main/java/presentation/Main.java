package presentation;

import business.Finances;

public class Main {
	public static void main(String[] args) {
		Finances finances = new Finances();
		new LoginGUI(finances);
	}
}
