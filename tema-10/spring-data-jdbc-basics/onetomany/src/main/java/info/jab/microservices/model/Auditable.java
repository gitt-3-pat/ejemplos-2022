package info.jab.microservices.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
public class Auditable {

	@CreatedBy
	private String createdBy;

	@CreatedDate
	private Instant createdDate;

	@LastModifiedBy
	private String lastModifiedBy;

	@LastModifiedDate
	private Instant lastModifiedDate;

}