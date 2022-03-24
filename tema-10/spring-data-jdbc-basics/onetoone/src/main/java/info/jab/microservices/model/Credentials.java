package info.jab.microservices.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("USER_CREDENTIALS")
public class Credentials {

	@Id
	private Long credsId;
	private String userName;
	private String password;
}
