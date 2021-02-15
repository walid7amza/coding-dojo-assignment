package com.leaseplan.codingdojoassignment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "weather")
public class CityWeather {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String city;
	private String country;
	private double temperature;

	public CityWeather(String city, String country, double temperature) {
		this.city = city;
		this.country = country;
		this.temperature = temperature;
	}

}
