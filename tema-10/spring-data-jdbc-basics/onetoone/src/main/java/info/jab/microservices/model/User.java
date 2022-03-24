package info.jab.microservices.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("USERS")
public class User {

	@Id
	private Long id;
	private Date createdTime;
	private Date updatedTime;
	@Column("DOB")
	private Date dateofBirth;
	private UserType userType;

	@MappedCollection(idColumn = "CREDS_ID", keyColumn = "CREDS_ID")
	private Credentials credentials;
}
