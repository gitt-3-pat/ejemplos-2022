package info.jab.microservices.controller;

import java.util.List;

import info.jab.microservices.service.MadridService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(
        path = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

    @Autowired
    private MadridService madridService;

    @GetMapping("/postalcodes")
    public @ResponseBody ResponseEntity<List<String>> getPostalCodes() {
        return ResponseEntity.ok().body(madridService.getCodes());
    }

    @GetMapping("/cities")
    public @ResponseBody ResponseEntity<List<String>> getCities() {
        return ResponseEntity.ok().body(madridService.getCities());
    }
}
