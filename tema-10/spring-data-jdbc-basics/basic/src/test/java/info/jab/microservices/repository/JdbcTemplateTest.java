package info.jab.microservices.repository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import info.jab.microservices.model.User;
import info.jab.microservices.model.UserType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@Slf4j
@SpringBootTest
@Sql(scripts= "/sql_data.sql") //to created DB tables and init sample DB data
@Transactional
public class JdbcTemplateTest {

	@Autowired
	private UserJdbcTemplateRepository userRepository;

	@Test
	void given_repository_when_add_user_then_Ok() {

		//Given

		//When
		User user = getUser();
		int created = userRepository.save(user);

		//Then
		then(created).isEqualTo(1);
	}
	
	@Test
	void given_repository_when_update_then_Ok() {

		//Given
		List<User> allUsers = userRepository.findAll();
		String newPassword = "ABC123abc#";

		//When
		allUsers.forEach(user -> {
			user.setPassword(newPassword);
			int updated = userRepository.update(user);
			
			then(updated).isEqualTo(1);
		});

		//Then
		List<User> allUsers2 = userRepository.findAll();
		allUsers2.stream()
				.map(User::getPassword)
				.forEach(s -> {
					then(s).isEqualTo(newPassword);
				});
	}

	@Test
	void given_repository_when_update_all_users_Ok() {

		//Given
		List<User> allUsers = (List<User>) userRepository.findAll();

		//When
		//Then
		allUsers.forEach(user -> {
			user.setPassword("ABC123abc#");
			int updated = userRepository.update(user);

			then(updated).isEqualTo(1);
		});

	}

	@Test
	void given_repository_when_delete_one_record_then_Ok() {

		//Given
		List<User> allUsers = userRepository.findAll();
		User user = allUsers.get(0);

		//When
		userRepository.delete(user);

		//Then
		int count = userRepository.count();
		then(allUsers.size()).isEqualTo(count + 1);
	}

	@Test
	void given_repository_when_paginate_and_fetch_first_page_then_Ok() {

		//Given
		PageRequest pageable = PageRequest.of(0, 5);

		//When
		List<User> users = userRepository.findAll();
		int count = users.size();
		LOGGER.info("Count : {}", count);
		Page<User> pagedUsers = userRepository.findAll(pageable);

		//Then
		then(pagedUsers.getTotalPages()).isEqualTo(3);
		then(pagedUsers.getContent()).isEqualTo(users.subList(0, 5));
	}

	@Test
	void given_repository_when_paginate_and_sort_and_fetch_first_page_then_Ok() {

		//Give
		PageRequest pageable = PageRequest
				.of(0, 5, Direction.fromString("DESC"), "USER_NAME");
		LOGGER.info("{}", pageable.getSort().toList().get(0));

		//When
		List<User> users = userRepository.findAll();
		int count = users.size();
		LOGGER.info("Count : {}", count);

		Page<User> pagedUsers = userRepository.findAll(pageable);

		List<User> usersList = users.stream()
				.sorted(Comparator.comparing(User::getUserName)
				.reversed())
				.collect(Collectors.toList()).subList(0, 5);

		//Then
		then(pagedUsers.getTotalPages()).isEqualTo(3);
		then(pagedUsers.getContent()).isEqualTo(usersList);
	}

	/**
	 * Private method to create a new User
	 *
	 * @return a new User Object
	 */
	private User getUser() {
		
		User user = new User();
		user.setUserType(UserType.STUDENT);
		user.setUserName("PhilipX");
		user.setPassword("ABC123abc*");
		user.setDateofBirth(new Date());
		user.setCreatedTime(new Date());
		
		return user;
	}

}
