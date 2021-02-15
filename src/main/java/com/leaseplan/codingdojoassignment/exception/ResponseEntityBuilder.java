package com.leaseplan.codingdojoassignment.exception;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
	public static ResponseEntity<Object> build(ApiResponseError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
