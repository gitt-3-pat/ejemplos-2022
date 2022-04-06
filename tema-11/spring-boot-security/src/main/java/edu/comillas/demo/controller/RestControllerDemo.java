package edu.comillas.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestControllerDemo {

	@GetMapping("/whoami")
	public ResponseEntity<Authentication> helloWorld(Authentication authentication) {
		return new ResponseEntity<>(authentication, HttpStatus.OK);
	}

}
