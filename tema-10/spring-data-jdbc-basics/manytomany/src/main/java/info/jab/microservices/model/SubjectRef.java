package info.jab.microservices.model;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Table("BRANCH_SUBJECT")
@Data
@AllArgsConstructor
public class SubjectRef {
	Long subjectId;
}
