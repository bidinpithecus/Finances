package utils;

import org.flywaydb.core.Flyway;

public class MigrationRunner {

	private final Flyway flyway;
//	private final DbAuth dbAuth = new DbAuth();

	public MigrationRunner() {
		this.flyway = Flyway
				.configure()
				.dataSource(
						"jdbc:postgresql://localhost:5432/finances",
						"admin",
						"admin"
				)
				.load();
	}

	public void start() {
		this.flyway.migrate();
	}

}
