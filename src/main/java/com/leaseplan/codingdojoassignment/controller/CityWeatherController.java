package com.leaseplan.codingdojoassignment.controller;

import javax.validation.constraints.NotBlank;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.leaseplan.codingdojoassignment.model.CityWeather;
import com.leaseplan.codingdojoassignment.service.CityWeatherService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@Validated
public class CityWeatherController {

	private final CityWeatherService cityWeatherService;

	@GetMapping("/weather")
	public ResponseEntity<CityWeather> weather(@RequestParam @NotBlank String city) {

		return new ResponseEntity<CityWeather>(cityWeatherService.getCityWeather(city), HttpStatus.CREATED);

	}
}
