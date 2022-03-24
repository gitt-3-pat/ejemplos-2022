package info.jab.microservices.controller;

import info.jab.microservices.model.Credentials;
import info.jab.microservices.model.User;
import info.jab.microservices.model.UserType;
import info.jab.microservices.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @GetMapping("api/users")
    public ResponseEntity<List<User>> getPersons() {

        List<User> list = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.ok().body(list);
    }

    @Transactional
    @GetMapping("api/users/add")
    public ResponseEntity<User> addPerson() {

        final Credentials credentials = new Credentials();
        credentials.setUserName("peterm");
        credentials.setPassword("password");

        final User user = new User();
        user.setCreatedTime(new Date());
        user.setDateofBirth(new Date());
        user.setUpdatedTime(new Date());
        user.setUserType(UserType.EMPLOYEE);
        user.setCredentials(credentials);

        userRepository.save(user);

        userRepository.getU().forEach(System.out::println);
        userRepository.getUC().forEach(System.out::println);

        return ResponseEntity.ok().body(user);
    }

    @Transactional
    @GetMapping("api/users/update/{id}")
    public ResponseEntity<User> updatePerson(@PathVariable("id") Long id) {

        LOGGER.info("ID: {}", id);

        Optional<User> ouser = userRepository.findById(id);

        if(ouser.isPresent()) {
            User user = ouser.get();
            user.setCredentials(null);

            userRepository.save(user);

            return ResponseEntity.ok().body(user);
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("api/users/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Long id) {

        LOGGER.info("ID: {}", id);

        userRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
