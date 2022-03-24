package info.jab.microservices.repository;

import info.jab.microservices.model.TableDemo;
import org.springframework.data.repository.CrudRepository;

public interface TableDemoRepository extends CrudRepository<TableDemo, Long> {

}
