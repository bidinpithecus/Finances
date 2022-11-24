package utils;

import org.flywaydb.core.Flyway;

public class MigrationRunner {

	private final Flyway flyway;

	public MigrationRunner() {
		DbAuth dbAuth = new DbAuth();
		this.flyway = Flyway
				.configure()
				.defaultSchema("main")
				.baselineOnMigrate(true)
				.dataSource(
						dbAuth.getURL(),
						dbAuth.getUSER(),
						dbAuth.getPASSWORD()
				)
				.load();
	}

	public void start() {
		this.flyway.migrate();
	}

}
