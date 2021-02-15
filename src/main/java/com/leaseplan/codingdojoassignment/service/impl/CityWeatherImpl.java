package com.leaseplan.codingdojoassignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import com.leaseplan.codingdojoassignment.dto.CityWeatherDTO;
import com.leaseplan.codingdojoassignment.model.CityWeather;
import com.leaseplan.codingdojoassignment.repository.CityWeatherRepository;
import com.leaseplan.codingdojoassignment.service.CityWeatherService;
import com.leaseplan.codingdojoassignment.util.Constants;

@Service
@Validated
public class CityWeatherImpl implements CityWeatherService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CityWeatherRepository cityWeatherRepository;

	@Override
	public CityWeather getCityWeather(String city) {
		String url = Constants.WEATHER_API_URL.replace("{city}", city).replace("{appid}", Constants.APP_ID);
		ResponseEntity<CityWeatherDTO> cityWeatherDTO = restTemplate.getForEntity(url, CityWeatherDTO.class);

		CityWeather cityWeather = saveCityWeatherDto(cityWeatherDTO.getBody());

		return cityWeatherRepository.save(cityWeather);
	}

	private CityWeather saveCityWeatherDto(CityWeatherDTO cityWeatherDto) {
		String city = cityWeatherDto.getCity();
		String country = cityWeatherDto.getSys().getCountry();
		double temp = cityWeatherDto.getMain().getTemp();
		CityWeather cityWeather = new CityWeather(city, country, temp);

		return cityWeatherRepository.save(cityWeather);

	}

}
