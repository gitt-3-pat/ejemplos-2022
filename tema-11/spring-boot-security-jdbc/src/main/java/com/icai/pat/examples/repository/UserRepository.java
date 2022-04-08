package com.icai.pat.examples.repository;


import org.springframework.stereotype.Repository;
import com.icai.pat.examples.model.User;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    @Override
    void delete(User user);

}
