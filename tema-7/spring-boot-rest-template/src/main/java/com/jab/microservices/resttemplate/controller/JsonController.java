package com.jab.microservices.resttemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class JsonController{

	@Autowired
	ResourceLoader resourceLoader;
	
	private static final String FILE_NAME = "swagger.json";

	@GetMapping("/swagger")
	public ResponseEntity<Object> getSwagger() {
		 Resource resource=resourceLoader.getResource(
			      "classpath:"+FILE_NAME);
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	
}
