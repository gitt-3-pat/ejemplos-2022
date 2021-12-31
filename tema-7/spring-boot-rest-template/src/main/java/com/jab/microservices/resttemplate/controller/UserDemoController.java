package com.jab.microservices.resttemplate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.delaware.swagger.generated.api.UsersApi;
import com.delaware.swagger.generated.model.UserItem;


@RestController
@RequestMapping("/demo")
public class UserDemoController implements UsersApi{

	@Autowired
	RestTemplate res;

	@SuppressWarnings("unchecked")
	@Override
	@GetMapping("/users/v2")
	public ResponseEntity<List<UserItem>> usersListsbyId() {
		 
		List<UserItem> userList = new ArrayList<UserItem>();
		String resourceUrl
		  = "http://localhost:8080/demo/v1/users";
		
		userList = (List<UserItem>) res.getForObject(
				resourceUrl,
				List.class);
		 
		return new ResponseEntity<List<UserItem>>(userList,HttpStatus.OK);
	}
	
	
}
