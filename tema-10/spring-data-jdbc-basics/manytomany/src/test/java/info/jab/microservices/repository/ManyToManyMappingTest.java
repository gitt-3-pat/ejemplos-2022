package info.jab.microservices.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import info.jab.microservices.model.Branch;
import info.jab.microservices.model.Subject;

@SpringBootTest
public class ManyToManyMappingTest {
	private static Branch getBranch1() {
		final Branch branch = new Branch();
		branch.setBranchName("Computer Science and Engineering");
		branch.setBranchShortName("CSE");
		branch.setDescription(
				"CSE department offers courses under ambitious curricula in computer science and computer engineering..");
		return branch;
	}

	private static Branch getBranch2() {
		final Branch branch = new Branch();
		branch.setBranchName("Information Technology");
		branch.setBranchShortName("IT");
		branch.setDescription(
				"IT is the business side of computers - usually dealing with databases, business, and accounting");
		return branch;
	}

	private static Subject getSubject1() {
		final Subject subject = new Subject();
		subject.setSubjectName("Software Engineering");
		subject.setSubjectDesc(
				"Apply key aspects of software engineering processes for the development of a complex software system");
		return subject;
	}

	private static Subject getSubject2() {
		final Subject subject = new Subject();
		subject.setSubjectName("Distributed System");
		subject.setSubjectDesc("Explore recent advances in distributed computing systems");
		return subject;
	}

	private static Subject getSubject3() {
		final Subject subject = new Subject();
		subject.setSubjectName("Business Analysis and Optimization");
		subject.setSubjectDesc("understand the Internal and external factors that impact the business strategy");
		return subject;
	}

	@Autowired
	private BranchTestRepository branchRepository;
	@Autowired
	private SubjectTestRepository subjectRepository;

	@Test
	@DisplayName("many-to-many-mapping-test")
	void embeddedMappingTest() {
		final Subject subj1 = subjectRepository.save(getSubject1());
		final Subject subj2 = subjectRepository.save(getSubject2());
		final Subject subj3 = subjectRepository.save(getSubject3());
		final Branch branch1 = getBranch1();
		branch1.addSubject(subj1);
		branch1.addSubject(subj2);
		final Branch createdBranch1 = branchRepository.save(branch1);
		assertTrue(createdBranch1 != null);
		final Branch branch2 = getBranch2();
		branch2.addSubject(subj1);
		branch2.addSubject(subj3);
		final Branch createdBranch2 = branchRepository.save(branch2);
		assertTrue(createdBranch2 != null);
		branchRepository.delete(branch1);
		assertTrue(!branchRepository.existsById(branch1.getBranchId()));
		branchRepository.findAll().forEach(b -> System.err.println(b));
	}
}