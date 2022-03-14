package info.jab.microservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.jab.microservices.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LoginService service;

	@Test
	public void given_loginController_when_call_then_Ok() throws Exception {

		//Given
		when(service.authenticate(anyString(), anyString())).thenReturn(true);

		//When
		//Then
		LoginRequest loginCredential = new LoginRequest("DEMO", "DEMO");
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