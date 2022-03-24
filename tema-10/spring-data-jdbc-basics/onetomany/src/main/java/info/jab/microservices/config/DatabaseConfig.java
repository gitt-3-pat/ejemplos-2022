package info.jab.microservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@Configuration
@EnableJdbcAuditing
public class DatabaseConfig {

	@Bean
	public AuditorAware<String> auditProvider() {
		return new AuditorAwareImpl();
	}

}
