package info.jab.microservices.postgres;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DockerPostgreDataSourceInitializer
		implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	public PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13");

	@Override
	public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
		postgreSQLContainer.start();

		TestPropertySourceUtils.addInlinedPropertiesToEnvironment(configurableApplicationContext,
				"spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
				"spring.datasource.username=" + postgreSQLContainer.getUsername(),
				"spring.datasource.password=" + postgreSQLContainer.getPassword());
	}
}