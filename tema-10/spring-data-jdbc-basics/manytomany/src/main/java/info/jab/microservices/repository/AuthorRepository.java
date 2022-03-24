package info.jab.microservices.repository;

import info.jab.microservices.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
