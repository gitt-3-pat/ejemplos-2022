package info.jab.microservices;

import info.jab.microservices.controller.LoginRequest;
import info.jab.microservices.controller.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerE2ETest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void given_app_when_login_using_right_credentials_then_ok() {

		//Given
		String address = "http://localhost:" + port + "/api/login";
		LoginRequest loginCredential = new LoginRequest("DEMO", "DEMO");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<LoginRequest> request = new HttpEntity<>(loginCredential, headers);

		//When
		ResponseEntity<LoginResponse> result = this.restTemplate.postForEntity(address, request, LoginResponse.class);

		//Then
		String expectedResult = "OK";
		LoginResponse expectedResponse = new LoginResponse(expectedResult);

		then(result.getBody().getResult()).isEqualTo(expectedResult);
		then(result.getBody()).isEqualTo(expectedResponse);
	}

	@Test
	public void given_app_when_login_using_bad_credentials_then_ko() {

		//Given
		String address = "http://localhost:" + port + "/api/login";
		LoginRequest loginCredential = new LoginRequest("DEMO", "DEMOS");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<LoginRequest> request = new HttpEntity<>(loginCredential, headers);

		//When
		ResourceAccessException exception = assertThrows(ResourceAccessException.class, () -> {
			this.restTemplate.postForEntity(address, request, LoginResponse.class);
		});

		//Then
		String expectedMessage = "cannot retry due to server authentication, in streaming mode";
		String actualMessage = exception.getMessage();
		then(actualMessage).contains(expectedMessage);
	}

	@Test
	public void given_app_when_login_using_bad_request_then_ko() {

		//Given
		String address = "http://localhost:" + port + "/api/login";
		LoginRequest loginCredential = new LoginRequest();//"DEMO", "DEMOS");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<LoginRequest> request = new HttpEntity<>(loginCredential, headers);

		//When
		ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

		//Then
		String expectedResult = "KO";
		LoginResponse expectedResponse = new LoginResponse(expectedResult);

		then(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
}