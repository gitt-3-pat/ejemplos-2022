package com.example.demo.jdbc.legacy.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LegacyUser {

	private String userid;
	private int age;
	private String password;

}
