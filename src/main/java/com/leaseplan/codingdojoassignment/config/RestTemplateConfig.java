package com.leaseplan.codingdojoassignment.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.leaseplan.codingdojoassignment.exception.RestTemplateErrorHandler;

@Configuration
public class RestTemplateConfig {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder().errorHandler(new RestTemplateErrorHandler()).build();
	}
}