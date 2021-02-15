package com.leaseplan.codingdojoassignment.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private ResponseEntity<Object> handleTreeNotFoundException(Exception ex) {
		ApiResponseError apiResponseError = ApiResponseError.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND).code(404).message(ex.getMessage()).build();

		return ResponseEntityBuilder.build(apiResponseError);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(Exception ex) {
		List<String> errors = new ArrayList<String>();
		errors.add(ex.getMessage());
		ApiResponseError apiResponseError = ApiResponseError.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST).code(400).message("Constraint Violations").errors(errors).build();
		return ResponseEntityBuilder.build(apiResponseError);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {

		List<String> errors = new ArrayList<String>();
		errors.add(ex.getParameterName() + " parameter is missing");

		ApiResponseError apiResponseError = ApiResponseError.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST).code(400).message("Missing Parameters").errors(errors).build();
		return ResponseEntityBuilder.build(apiResponseError);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {

		List<String> errors = new ArrayList<String>();
		errors.add(ex.getLocalizedMessage());

		ApiResponseError apiResponseError = ApiResponseError.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST).code(400).message("Something went wrong").errors(errors).build();
		log.error("Something went wrong, Exception : " + ex.getMessage());
		ex.printStackTrace();
		return ResponseEntityBuilder.build(apiResponseError);
	}

}
