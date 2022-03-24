package info.jab.microservices.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import info.jab.microservices.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

	@Transactional
	@Query("UPDATE PERSON SET FIRST_NAME= :userName WHERE PERSON.ID= :id ")
	@Modifying
	public int updateUserNameById(@Param("userName") String userName, @Param("id") Long id);

}
