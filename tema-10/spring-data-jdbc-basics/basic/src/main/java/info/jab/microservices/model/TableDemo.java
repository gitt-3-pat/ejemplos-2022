package info.jab.microservices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.sql.Date;

@Data
@Builder
@Table("TABLEDEMO")
@NoArgsConstructor
@AllArgsConstructor
public class TableDemo {

	@Id
	private Long id;
	private String field1;
	private String field2;
	private Date birthday;
	private LocalTime mytime;
	private Timestamp now;
	private Boolean flag;
}
