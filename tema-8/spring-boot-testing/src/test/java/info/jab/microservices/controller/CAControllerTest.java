package info.jab.microservices.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CAControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void given_app_when_login_using_right_credentials_then_ok() {

        //Given
        String address = "http://localhost:" + port + "/katakroker";

        //When
        ResponseEntity<GlobalErrorResponse> result = this.restTemplate.getForEntity(address, GlobalErrorResponse.class);

        //Then
        String expectedResult = "Contact with the operator";
        GlobalErrorResponse expectedResponse = new GlobalErrorResponse(expectedResult);

        then(result.getBody().getMessage()).isEqualTo(expectedResult);
        then(result.getBody()).isEqualTo(expectedResponse);
    }

    @TestConfiguration
    public static class TestCfg {

        @RestController
        public class testEndpoint {

            @GetMapping("/katakroker")
            public String hello() {
                throw new RuntimeException("Boom!");
            }
        }
    }
 }