package presentation;

import business.Finances;
import utils.MigrationRunner;

public class Main {
	public static void main(String[] args) {
		MigrationRunner migrationRunner = new MigrationRunner();
		migrationRunner.start();

		Finances finances = new Finances();
		new LoginGUI(finances);
	}
}
