package info.jab.microservices.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
public class NordicController {

    @Value("${endpoint}")
    private String endpoint;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/gods")
    public @ResponseBody
    ResponseEntity<List<String>> getGods() {
        ResponseEntity<List<String>> result = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {});

        return ResponseEntity.ok().body(result.getBody());
    }

}
