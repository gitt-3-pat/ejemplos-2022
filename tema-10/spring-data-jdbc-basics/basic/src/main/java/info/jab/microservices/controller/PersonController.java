package info.jab.microservices.controller;

import info.jab.microservices.model.Person;
import info.jab.microservices.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    @GetMapping("api/persons")
    public ResponseEntity<List<Person>> getPersons() {

        List<Person> list = StreamSupport.stream(personRepository.findAll().spliterator(), false)
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.ok().body(list);
    }

    @Transactional
    @GetMapping("api/persons/add")
    public ResponseEntity<Person> addPerson() {

        Person person = new Person();
        person.setFirst_name("demo");
        person.setLast_name("demo2");
        personRepository.save(person);

        return ResponseEntity.ok().body(person);
    }

    @Transactional
    @GetMapping("api/persons/update/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id) {

        LOGGER.info("ID: {}", id);

        Optional<Person> operson = personRepository.findById(id);

        if(operson.isPresent()) {
            Person person = operson.get();
            person.setLast_name("Demo update");
            personRepository.updatePersonById(
                    person.getFirst_name(),
                    person.getLast_name(),
                    person.getId());

            return ResponseEntity.ok().body(person);
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("api/persons/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Long id) {

        LOGGER.info("ID: {}", id);

        personRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
