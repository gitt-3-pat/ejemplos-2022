package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaLigaControllerE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void given_endpoint_teams_in_champions_when_call_then_Ok() {

        //Given
        String address = "http://localhost:" + port + "/api/v1/la-liga/teams/in-champions";

        //When

        ResponseEntity<List<String>> result =
                restTemplate.exchange(
                        address,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        }
                );

        //Then
        var expectedResult = "OK";
        var expectedList = List.of(
                "Real Madrid",
                "Sevilla",
                "Barcelona",
                "Atletico");

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody().size()).isEqualTo(4);
        then(result.getBody()).isEqualTo(expectedList);
    }

}