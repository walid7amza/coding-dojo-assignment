package com.leaseplan.codingdojoassignment.exception;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestTemplateErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {

		return new DefaultResponseErrorHandler().hasError(response);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {

		if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {

			log.error("Service Not available" + response.getStatusCode());

		} else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				&& response.getStatusCode() == HttpStatus.NOT_FOUND)
			throw new NotFoundException("not found");

	}

}