package info.jab.microservices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Table("BOOK")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@Id
	private Long id;
	private String title;
	private String isbn;
	private Set<AuthorRef> authorIds = new HashSet<>();

	public void addAuthor(Author author) {
		authorIds.add(new AuthorRef(author.getId()));
	}
}