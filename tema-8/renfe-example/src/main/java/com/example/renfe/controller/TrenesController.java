package com.example.renfe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class TrenesController {

    @GetMapping("/api/trenes")
    public @ResponseBody ResponseEntity<List<String>> getTrenes() {

        List<String> trenes = List.of(
                "Alvia",
                "Talgo",
                "Cercanias",
                "Metroligero",
                "Ave");

        return ResponseEntity.ok().body(trenes);
    }

    @GetMapping("/api/trenes/{id}")
    public @ResponseBody
    ResponseEntity<String> getTrenesById(@PathVariable ("id") String id) {

        log.info(id);
        Optional<String> tren =  List.of(
                "Alvia",
                "Talgo",
                "Cercanias",
                "Metroligero",
                "Ave").stream()
                .filter(s -> s.toLowerCase().equals(id.toLowerCase()))
                .findAny();

        if (tren.isPresent()) {
            return ResponseEntity.ok().body(tren.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }
}
