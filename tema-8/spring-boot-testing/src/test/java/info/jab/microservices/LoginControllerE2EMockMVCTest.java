package info.jab.microservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.jab.microservices.controller.LoginRequest;
import info.jab.microservices.controller.LoginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//https://spring.io/guides/gs/testing-web/

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerE2EMockMVCTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void given_loginController_when_call_with_good_credential_then_Ok() throws Exception {

		//Given
		LoginRequest loginCredential = new LoginRequest("DEMO", "DEMO");

		//When
		//Then
		LoginResponse expectedLoginResponse = new LoginResponse("OK");
		this.mockMvc
				.perform(post("/api/login")
						.contentType(APPLICATION_JSON_VALUE)
						.content(asJsonString(loginCredential)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(asJsonString(expectedLoginResponse))));
	}

	public static String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}