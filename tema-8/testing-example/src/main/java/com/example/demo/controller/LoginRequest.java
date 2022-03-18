package com.example.demo.controller;

import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public record LoginRequest(
    @NonNull
    @NotEmpty
    @Pattern(message="max 5 words please" , regexp="^[a-zA-Z-.0-9]{1,5}$")
    String user,

    @NotNull
    @NotEmpty
    @Pattern(message="max 5 words please" , regexp="^[a-zA-Z-.0-9]{1,5}$")
    String password
) {}