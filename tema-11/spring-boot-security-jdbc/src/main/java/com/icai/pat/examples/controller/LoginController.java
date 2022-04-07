package com.icai.pat.examples.controller;

import com.icai.pat.examples.service.LoginService;
import com.icai.pat.examples.service.LoginServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
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

        LoginServiceResult loginServiceResult = loginService.authenticate(loginParam.getUser(), loginParam.getPassword());

        if(loginServiceResult.getFlag()) {
            String token = loginServiceResult.getAccess_token();
            //String access_token = new String(Base64.getDecoder().decode(token));
            LoginResponse loginResponse = new LoginResponse("OK", token);
            return ResponseEntity.ok().body(loginResponse);
        } else {
            LoginResponse loginResponse = new LoginResponse("KO");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
        }
    }
}
