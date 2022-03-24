package info.jab.microservices.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;

import lombok.Data;

@Data
public class Branch {
	@Id
	private Long branchId;
	private String branchName;
	@Column("BRANCH_SHORT_NAME")
	private String branchShortName;
	private String description;
	@MappedCollection(idColumn = "BRANCH_ID")
	private Set<SubjectRef> subjects = new HashSet<>();

	public void addSubject(Subject subject) {
		subjects.add(new SubjectRef(subject.getSubjectId()));
	}
}
