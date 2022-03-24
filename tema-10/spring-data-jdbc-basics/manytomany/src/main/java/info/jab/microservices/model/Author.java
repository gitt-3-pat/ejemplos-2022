package info.jab.microservices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("AUTHOR")
@NoArgsConstructor
@AllArgsConstructor
public class Author {

	@Id
	private Long id;
	private String name;
	private String birthdate;
}
