package com.example.demo.controller;


import com.example.demo.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(
        path = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    private LoginService loginService;

    LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<LoginResponse> login (
            @Valid @RequestBody LoginRequest loginParam,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            LoginResponse loginResponse = new LoginResponse("KO");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
        }

        if(loginService.authenticate(loginParam.user(), loginParam.password())) {
            LoginResponse loginResponse = new LoginResponse("OK");
            return ResponseEntity.ok().body(loginResponse);
        } else {
            LoginResponse loginResponse = new LoginResponse("KO");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
        }
    }
}