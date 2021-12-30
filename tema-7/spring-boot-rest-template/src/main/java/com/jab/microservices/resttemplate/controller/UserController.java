package com.jab.microservices.resttemplate.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.delaware.swagger.generated.api.UsersApi;
import com.delaware.swagger.generated.model.UserItem;


@RestController
@RequestMapping("/demo")
public class UserController implements UsersApi{

	@Autowired
	ResourceLoader resourceLoader;
	
	private static final String FILE_NAME = "users.json";

	@Override
	@GetMapping("/users/v1")
	public ResponseEntity<List<UserItem>> usersListsbyId() {
		 
		List<UserItem> userList = new ArrayList<UserItem>();
		
		 Resource resource=resourceLoader.getResource(
			      "classpath:"+FILE_NAME);
		 
		 
		 JSONParser parser = new JSONParser(); 
		 try {
			Object obj = parser.parse(new FileReader(resource.getFile()));
			userList = (List<UserItem>) obj;
		 } catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		 
		return new ResponseEntity<List<UserItem>>(userList,HttpStatus.OK);
	}
	
	
}
