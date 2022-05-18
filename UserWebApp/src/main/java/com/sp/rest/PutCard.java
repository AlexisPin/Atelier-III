package com.sp.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class PutCard {

	static final String URL_UPDATE_CARD = "http://localhost:8082/card";
	

	public void updateCard(Integer cardId, Integer userId) {


		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		RestTemplate restTemplate = new RestTemplate();

		// Data attached to the request.
		HttpEntity<?> requestBody = new HttpEntity<>(userId, headers);

		// Send request with PUT method.
		restTemplate.put(URL_UPDATE_CARD, requestBody, new Object[] {});
	}

}
