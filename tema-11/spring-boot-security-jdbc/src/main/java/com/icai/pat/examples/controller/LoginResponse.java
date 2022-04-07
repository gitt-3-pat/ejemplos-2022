package com.icai.pat.examples.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String result;
    private String access_token;

    public LoginResponse(String ko) {
        this.result = ko;
    }
}
