package com.icai.pat.examples.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;


    public User(Long id, String firstName, String lastName, String email, String password,String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role=role;

	}

	public User() {
		super();
	}

}
