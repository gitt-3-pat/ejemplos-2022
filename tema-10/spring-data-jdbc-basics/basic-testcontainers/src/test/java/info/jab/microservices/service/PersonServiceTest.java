package info.jab.microservices.service;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import info.jab.microservices.postgres.DockerPostgreDataSourceInitializer;
import info.jab.microservices.repository.PersonRepository;

@Transactional
@SpringBootTest
@Sql(scripts = { "/db/migration/V1_schema.sql" }) // to created DB tables and init sample DB data
@ContextConfiguration(initializers = DockerPostgreDataSourceInitializer.class)
public class PersonServiceTest {

	@Autowired
	private PersonService personService;

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void given_service_when_call_addBulkData_then_Ok() {

		personService.addBulkData();

		then(personRepository.count()).isEqualTo(4);
	}

}