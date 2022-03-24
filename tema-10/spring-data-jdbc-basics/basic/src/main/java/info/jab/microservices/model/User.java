package info.jab.microservices.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;


@Data // lomok
public class User {

	@Id
	private Long id;
	private String userName;
	private String password;
	private String email;
	private Date createdTime;
	private Date updatedTime;
	@Column("DOB") // to map db column if property not same as column name
	private Date dateofBirth;
	private UserType userType; // Enum Type

	@org.springframework.data.annotation.Transient // to not persist into DB (just to expose to view/client)
	private String dateOfBirthString;
	
	// to display on view
	public String getDateOfBirthString() {
		return this.dateofBirth.toString();
	}
}
