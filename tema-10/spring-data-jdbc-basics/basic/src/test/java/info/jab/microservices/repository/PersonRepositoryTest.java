package info.jab.microservices.repository;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import info.jab.microservices.model.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	@Transactional
	public void given_repository_when_execute_modified_method_then_Ok() {

		//Given
		final Person saved = personRepository.save(getPerson());
		then(saved).isNotNull();

		//When
		personRepository.updateUserNameById(saved.getFirst_name(), saved.getId());
		Person person2 = personRepository.findById(1L).orElseThrow(RuntimeException::new);

		//Then
		then(saved).isEqualTo(person2);
	}

	@Test
	@Transactional
	public void given_repository_when_execute_myquery_then_Ok() {

		//Given
		Person person = getPerson();
		personRepository.save(person);

		//When
		List<Person> list = personRepository.myQuery();

		//Then
		then(List.of(person)).isEqualTo(list);
	}

	private Person getPerson() {

		final Person person = new Person();
		person.setFirst_name("Javier");
		person.setLast_name("Gomez-Cornejo");
		return person;
	}
}
