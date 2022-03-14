package info.jab.microservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.jab.microservices.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({CAController.class})
class CAControllerTest2 {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void given_app_when_login_using_right_credentials_then_ok() throws Exception {

        //Given
        String expectedResult = "Contact with the operator";
        GlobalErrorResponse expectedResponse = new GlobalErrorResponse(expectedResult);

        //When
        //Then
        this.mockMvc
                .perform(get("/katakroker").contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString(asJsonString(expectedResponse))));
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

    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}