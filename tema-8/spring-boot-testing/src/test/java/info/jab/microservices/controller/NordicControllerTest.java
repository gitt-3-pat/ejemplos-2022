package info.jab.microservices.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class NordicControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	WireMockServer wireMockServer;

	@BeforeEach
	public void setup () {
		wireMockServer = new WireMockServer(8090);
		wireMockServer.start();
	}

	@AfterEach
	public void teardown () {
		wireMockServer.stop();
	}

	@Test
	public void given_app_when_login_using_right_credentials_then_ok() {

		//Given
		String address = "http://localhost:" + port + "/gods";

		wireMockServer.stubFor(get(urlEqualTo("/nordic"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json")
						.withStatus(200)
						.withBodyFile("nordic.json")));

		//When
		ResponseEntity<List<String>> result = restTemplate.exchange(
				address,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<String>>() {});

		//Then
		List<String> expectedResponse = List.of(
				"Baldur",
				"Freyja",
				"Heimdall",
				"Frigga",
				"Hel",
				"Loki",
				"Njord",
				"Odin",
				"Thor",
				"Tyr");
		then(result.getBody().size()).isEqualTo(10);
		then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		then(result.getBody()).isEqualTo(expectedResponse);
	}
}