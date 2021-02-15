package com.leaseplan.codingdojoassignment.integration;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.leaseplan.codingdojoassignment.controller.CityWeatherController;
import com.leaseplan.codingdojoassignment.exception.ApiExceptionHandler;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
class CityWeatherIntegrationTest {

	private final static String URI = "/weather";

	private MockMvc mockMvc;

	@Autowired
	CityWeatherController cityWeatherController;

	@BeforeEach
	void setUp() {
		this.mockMvc = standaloneSetup(cityWeatherController).setControllerAdvice(new ApiExceptionHandler())
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}

	@Test
	void should_Return_201_Created() throws Exception {

		mockMvc.perform(get(URI).param("city", "Dubai")).andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$.city", is("Dubai"))).andExpect(jsonPath("$.country", is("AE")))
				.andExpect(jsonPath("$.temperature", is(notNullValue())));

	}

	@Test
	void should_Return_404_NotFound() throws Exception {

		mockMvc.perform(get(URI).param("city", "NotExitsCity")).andDo(print()).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status", is("NOT_FOUND"))).andExpect(jsonPath("$.code", is(404)))
				.andExpect(jsonPath("$.message", is("not found")));

	}

	@Test
	void should_Return_BadRequest_Missing_Parameter() throws Exception {

		mockMvc.perform(get(URI).param("city", "")).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status", is("BAD_REQUEST"))).andExpect(jsonPath("$.code", is(400)))
				.andExpect(jsonPath("$.errors[0]", is("weather.city: must not be blank")));

	}
}
