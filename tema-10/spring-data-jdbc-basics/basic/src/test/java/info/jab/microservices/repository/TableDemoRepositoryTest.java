package info.jab.microservices.repository;

import info.jab.microservices.model.TableDemo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.BDDAssertions.then;

@Slf4j
@SpringBootTest
public class TableDemoRepositoryTest {

	@Autowired
	private TableDemoRepository tableDemoRepository;

	@Test
	@Transactional
	public void given_repository_when_add_then_Ok() {

		//Given
		Date date = Date.valueOf(LocalDate.of(2019, 01, 10));
		LocalTime now = LocalTime.now();
		Timestamp ts = Timestamp.from(Instant.now());

		TableDemo employee = TableDemo.builder()
				.field1("Field 1")
				.field2("Field 2")
				.birthday(date)
				.mytime(now)
				.now(ts)
				.flag(true)
				.build();

		/*
		TableDemo employee2 = new TableDemo();
		employee2.setBirthday(date);

		 */

		//
		//TableDemo employee3 = new TableDemo(asdfa, asf,, asf)


		//When
		tableDemoRepository.save(employee);

		//Then
		TableDemo savedBook = tableDemoRepository.findById(employee.getId()).orElseThrow(RuntimeException::new);
		then(savedBook.getId()).isNotNull();

		StreamSupport.stream(tableDemoRepository.findAll().spliterator(), false)
				.map(e -> e.toString())
				.forEach(LOGGER::info);
	}

}
