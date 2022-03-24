package info.jab.microservices.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import info.jab.microservices.model.Credentials;
import info.jab.microservices.model.User;
import info.jab.microservices.model.UserType;

@SpringBootTest
public class OneToOneRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	@DisplayName("One-to-One Mapping Test")
	void embeddedMappingTest() {

		final Credentials credentials = new Credentials();
		credentials.setUserName("peterm");
		credentials.setPassword("password");

		final User user = new User();
		user.setCreatedTime(new Date());
		user.setDateofBirth(new Date());
		user.setUserType(UserType.EMPLOYEE);
		user.setCredentials(credentials);
		User createdUser = userRepository.save(user);
		assertTrue(createdUser != null);
		assertTrue(createdUser.getCredentials() != null);
		createdUser.getCredentials().setPassword("newPass");
		createdUser = userRepository.save(user);

		userRepository.delete(user);
		assertTrue(createdUser.getCredentials() != null);
		assertTrue(createdUser.getCredentials().getPassword().equals("newPass"));
		assertTrue(userRepository.findByUserName(user.getCredentials().getUserName()) == 0);
	}

	@Test
	@DisplayName("One-to-One Mapping Test")
	void embeddedMappingTest2() {

		final Credentials credentials = new Credentials();
		credentials.setUserName("peterm");
		credentials.setPassword("password");

		final User user = new User();
		user.setCreatedTime(new Date());
		user.setDateofBirth(new Date());
		user.setUserType(UserType.EMPLOYEE);
		user.setCredentials(credentials);

		final User createdUser = userRepository.save(user);
		assertTrue(createdUser != null);

		createdUser.setCredentials(null);
		userRepository.save(createdUser);

		StreamSupport.stream(userRepository.findAll().spliterator(), false).forEach(x -> x.toString());
	}

}
