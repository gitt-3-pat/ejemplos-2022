package edu.icai.gittpat.controller;

import edu.icai.gittpat.model.MyTable;
import edu.icai.gittpat.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/elements")
    public ResponseEntity<List<MyTable>> getElements() {

        var elements = myService.getElements();

        return ResponseEntity.ok().body(elements);
    }

}
