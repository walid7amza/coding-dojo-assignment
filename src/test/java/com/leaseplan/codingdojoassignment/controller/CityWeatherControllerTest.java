package com.leaseplan.codingdojoassignment.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.leaseplan.codingdojoassignment.model.CityWeather;
import com.leaseplan.codingdojoassignment.service.CityWeatherService;

@WebMvcTest(CityWeatherController.class)
class CityWeatherControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CityWeatherService cityWeatherService;

	private CityWeather cityWeather;

	@BeforeEach
	void Setup() {
		this.cityWeather = new CityWeather("amsterdam", "NL", 292.37);
	}

	@AfterEach
	void tearDown() {
		reset(cityWeatherService);

	}

	@Test
	void should_Return_201_created() throws Exception {
		when(cityWeatherService.getCityWeather(cityWeather.getCity())).thenReturn(cityWeather);

		this.mockMvc.perform(get("/weather?city=" + cityWeather.getCity())).andDo(print())
				.andExpect(status().isCreated()).andExpect(jsonPath("$.city", is("amsterdam")))
				.andExpect(jsonPath("$.country", is("NL"))).andExpect(jsonPath("$.temperature", is(292.37)));
	}

	@Test
	void should_Return_BadRequest_Missing_Parameters() throws Exception {

		when(cityWeatherService.getCityWeather(cityWeather.getCity())).thenReturn(this.cityWeather);

		this.mockMvc.perform(get("/weather?city")).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("Missing Parameters")))
				.andExpect(jsonPath("$.errors[0]", is("city parameter is missing")));
	}

}
