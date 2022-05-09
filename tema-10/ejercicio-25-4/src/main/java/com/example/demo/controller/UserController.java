package com.example.demo.controller;

import com.example.demo.model.UserDocument;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users/documents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDocument>> getUsersWithDocuments() {
        return ResponseEntity.ok().body(userService.getUserDocuments());
    }
}
