package info.jab.microservices.repository;

import static org.assertj.core.api.BDDAssertions.then;

import java.time.LocalDate;

import info.jab.microservices.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void given_repository_when_add_then_Ok() {

		//Given
		Book book = Book.builder()
				.name("My Book")
				.isbn("ISBN1234")
				.publishedDate(LocalDate.of(2018, 12, 12))
				.price(12.99).build();

		//When
		bookRepository.save(book);

		//Then
		Book savedBook = bookRepository.findById(book.getId()).orElseThrow(RuntimeException::new);
		then(savedBook.getId()).isNotNull();
	}

	@Test
	public void given_repository_when_add_multiple_records_then_Ok() {

		//Given
		Book book = Book.builder()
				.name("My Book")
				.isbn("ISBN1234")
				.publishedDate(LocalDate.of(2018, 12, 12))
				.price(12.99).build();

		Book book2 = Book.builder()
				.name("My Book 2")
				.isbn("ISBN1234")
				.publishedDate(LocalDate.of(2018, 12, 12))
				.price(12.99).build();

		//When
		bookRepository.save(book);
		bookRepository.save(book2);

		//Then
		long count = bookRepository.count();
		then(count).isEqualTo(2);
	}

	@Test
	public void given_repository_and_fetch_record_when_update_then_Ok() {

		//Given
		Book book = Book.builder()
				.name("My Book")
				.isbn("ISBN1234")
				.publishedDate(LocalDate.of(2018, 12, 12))
				.price(12.99).build();
		bookRepository.save(book);

		//When
		Book book2 = bookRepository.findAll().iterator().next();
		book2.setPrice(15.00);
		bookRepository.save(book2);

		//Then
		Book book3 = bookRepository.findAll().iterator().next();
		then(book3).isEqualTo(book2);
	}
}
