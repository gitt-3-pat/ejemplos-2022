package info.jab.microservices.service;

import info.jab.microservices.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@Transactional
@SpringBootTest
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