package info.jab.microservices.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("book")
public class Book {

	@Id
	private Long id;

	private String name;

	private String isbn;

	private Double price;

	private LocalDate publishedDate;

}
