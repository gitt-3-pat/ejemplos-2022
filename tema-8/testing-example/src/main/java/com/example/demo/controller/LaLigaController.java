package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LaLigaController {

    @GetMapping("/la-liga/teams/in-champions")
    public @ResponseBody ResponseEntity<List<String>> getLaLigaChampionsTeams() {

        List<String> teams = List.of(
                "Real Madrid",
                "Sevilla",
                "Barcelona",
                "Atletico");

        return ResponseEntity.ok().body(teams);
    }

}
