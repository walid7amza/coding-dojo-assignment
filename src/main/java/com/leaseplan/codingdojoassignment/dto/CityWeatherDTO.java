package com.leaseplan.codingdojoassignment.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CityWeatherDTO {
	@JsonProperty("name")
	private String city;
	@JsonProperty("main")
	private MainDTO main;
	@JsonProperty("sys")
	private SysDTO sys;

}