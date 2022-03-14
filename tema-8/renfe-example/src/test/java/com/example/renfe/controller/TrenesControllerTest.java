package com.example.renfe.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({TrenesController.class })
public class TrenesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void given_controller_when_call_getTrenes_then_Ok() throws Exception {

		//Given
		List<String> expectedList = List.of(
				"Alvia",
				"Talgo",
				"Cercanias",
				"Metroligero",
				"Ave");


		//When
		//Then
		this.mockMvc
				.perform(get("/api/trenes").contentType(APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(asJsonString(expectedList))));
	}

	@Test
	public void given_controller_when_call_getTrenesById_thenOk() throws Exception {

		//Given
		String expectedTren = "Ave";

		//When
		//Then
		this.mockMvc
				.perform(get("/api/trenes/" + expectedTren.toLowerCase()).contentType(APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(expectedTren));
	}

	@Test
	public void given_controller_when_call_getTrenesById_with_unknow_tren_then_Ok() throws Exception {

		//Given
		String expectedTren = "Ave2";

		//When
		//Then
		this.mockMvc
				.perform(get("/api/trenes/" + expectedTren.toLowerCase()).contentType(APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	public static String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}