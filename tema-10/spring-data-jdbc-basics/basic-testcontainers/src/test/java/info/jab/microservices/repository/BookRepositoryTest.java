package info.jab.microservices.repository;

import static org.assertj.core.api.BDDAssertions.then;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import info.jab.microservices.model.Book;
import info.jab.microservices.postgres.DockerPostgreDataSourceInitializer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
@Sql(scripts = { "/db/migration/V1_schema.sql" }) // to created DB tables and init sample DB data
@ContextConfiguration(initializers = DockerPostgreDataSourceInitializer.class)
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void createBookWithAuthor() {

		final Book book = Book.builder().name("My Book").isbn("ISBN1234").publishedDate(LocalDate.of(2018, 12, 12))
				.price(12.99).build();

		bookRepository.save(book);

		final Book savedBook = bookRepository.findById(book.getId()).orElseThrow(RuntimeException::new);
		then(savedBook.getId()).isNotNull();
	}
}
