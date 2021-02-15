package com.leaseplan.codingdojoassignment.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseError {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;
	private HttpStatus status;
	private int code;
	private String message;
	private List<String> errors;

}
