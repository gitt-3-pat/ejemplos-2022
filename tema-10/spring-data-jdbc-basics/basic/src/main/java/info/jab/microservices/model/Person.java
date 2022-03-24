package info.jab.microservices.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table("PERSON")
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	@Id
	private Long id;

	private String first_name;

	private String last_name;
}
