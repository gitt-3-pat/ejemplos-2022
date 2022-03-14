package com.example.renfe.controller;

import com.example.renfe.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping("/api/destinos")
    public @ResponseBody
    ResponseEntity<List<String>> getDestinations() {

        return ResponseEntity.ok().body(destinationService.getDestinations());
    }
}
