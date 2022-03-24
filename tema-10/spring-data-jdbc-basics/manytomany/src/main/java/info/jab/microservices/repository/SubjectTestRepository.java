package info.jab.microservices.repository;

import org.springframework.data.repository.CrudRepository;

import info.jab.microservices.model.Subject;

public interface SubjectTestRepository extends CrudRepository<Subject, Long> {
}
