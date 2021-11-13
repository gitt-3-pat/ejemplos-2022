package com.ecommerce.market.webapp.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.market.webapp.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query("select * from user where user.email= :email")
	public User findByEmail(@Param("email") String email);

	@Override
	@Query("select * from user")
	public List<User> findAll();

}
