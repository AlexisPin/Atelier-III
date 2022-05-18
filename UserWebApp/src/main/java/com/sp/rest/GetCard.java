package com.sp.rest;

import org.springframework.web.client.RestTemplate;

public class GetCard {

	static final String URL_EMPLOYEES = "http://localhost:8082/cards";

	public String getCards() {

		RestTemplate restTemplate = new RestTemplate();

		// Send request with GET method and default Headers.
		String result = restTemplate.getForObject(URL_EMPLOYEES, String.class);

		return result;
	}
	
	public void updateCard(Integer cardId) {
		
	}
	
}
