package info.jab.microservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table("BOOK_AUTHOR")
@Data
@AllArgsConstructor
public class AuthorRef {
	Long author;
}
