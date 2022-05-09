package com.example.demo.repository;

import com.example.demo.model.UserDocument;
import com.example.demo.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    String query =
            """
            SELECT U.USER_ID, U.USER, D.DOC_ID, D.DOC 
            FROM DOCUMENTS D, USERS U 
            WHERE U.USER_ID = D.USER_ID                                                                        
           """;

    @Query(query)
    List<UserDocument> myQuery();
}
