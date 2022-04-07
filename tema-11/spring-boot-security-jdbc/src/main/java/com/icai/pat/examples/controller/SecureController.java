package com.icai.pat.examples.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class SecureController {

    @GetMapping(value = "/secure", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> greeting(@RequestHeader("Authorization") String token) {
        String access_token = new String(Base64.getDecoder().decode(token));
        System.out.println(access_token);
        return new ResponseEntity<String>("{'result':'OK'}", HttpStatus.OK);
    }

}
