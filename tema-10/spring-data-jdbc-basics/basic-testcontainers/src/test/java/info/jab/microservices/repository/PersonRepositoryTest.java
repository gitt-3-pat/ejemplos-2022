package info.jab.microservices.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import info.jab.microservices.model.Person;
import info.jab.microservices.postgres.DockerPostgreDataSourceInitializer;

@SpringBootTest
@Sql(scripts = { "/db/migration/V1_schema.sql" }) // to created DB tables and init sample DB data
@ContextConfiguration(initializers = DockerPostgreDataSourceInitializer.class)
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testTransactionalUpdate() {
		final Person person = new Person();
		person.setFirst_name("Javier");
		person.setLast_name("Gomez-Cornejo");
		final Person saved = personRepository.save(person);

		assertTrue(saved != null);
		personRepository.updateUserNameById(saved.getFirst_name(), saved.getId());

	}
}
