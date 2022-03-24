package info.jab.microservices.repository;

import org.springframework.data.repository.CrudRepository;

import info.jab.microservices.model.Branch;

public interface BranchTestRepository extends CrudRepository<Branch, Long> {
}
