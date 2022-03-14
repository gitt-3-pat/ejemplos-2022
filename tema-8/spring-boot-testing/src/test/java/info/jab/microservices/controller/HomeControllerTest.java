package info.jab.microservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.jab.microservices.config.WebConfig;
import info.jab.microservices.service.MadridService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({HomeController.class, WebConfig.class})
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MadridService service;

	@Test
	public void given_HomeController_when_call_getCities_then_Ok() throws Exception {

		//Given
		List<String> expectedList = List.of(
				"Torrejon de Ardoz 100000",
				"Alcalá de Henares");
		when(service.getCities()).thenReturn(expectedList);

		//When
		//Then
		this.mockMvc
				.perform(get("/api/cities").contentType(APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(asJsonString(expectedList))));
	}

	@Test
	public void given_HomeController_when_call_getPostalCodes_then_Ok() throws Exception {

		//Given
		List<String> expectedList = List.of(
				"28800 through 28809 – Alcalá de Henares",
				"28820 – Coslada",
				"28830, 28831, 28850 – San Fernando de Henares",
				"28850 – Torrejón de Ardoz");
		when(service.getCodes()).thenReturn(expectedList);

		//When
		//Then
		this.mockMvc
				.perform(get("/api/postalcodes").contentType(APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(asJsonString(expectedList))));
	}

	public static String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}