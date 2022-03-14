package com.example.renfe.controller;


import com.example.renfe.services.DestinationService;
import com.fasterxml.jackson.databind.ObjectMapper;

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



@WebMvcTest({DestinationController.class })
public class DestinationsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DestinationService service;

	@Test
	public void given_DestinationController_when_call_getDestination_thenOk() throws Exception {

		//Given
		List<String> expectedList = List.of(
				"Valencia",
				"Sevilla");
		when(service.getDestinations()).thenReturn(expectedList);

		//When
		//Then
		this.mockMvc
				.perform(get("/api/destinos").contentType(APPLICATION_JSON_VALUE))
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