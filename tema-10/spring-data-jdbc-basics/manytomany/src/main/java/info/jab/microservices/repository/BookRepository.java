package info.jab.microservices.repository;

import info.jab.microservices.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
