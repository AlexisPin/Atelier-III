package com.sp.rest;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class FetchUser {

	static final String URL_UpdateCard = "http://localhost:8082/card";
	static final String URL_CARDSID = "http://localhost:8082/cardsId";
	
	RestTemplate restTemplate;

    public FetchUser() {
		this.restTemplate = new RestTemplate();
    }
    
	public List<Integer> getCards() {

		// Send request with GET method and default Headers.
		ResponseEntity<List> result = restTemplate.getForEntity(URL_CARDSID, List.class);
		
		List<Integer> cardsId = result.getBody();
		return cardsId;
	}
	
	public void updateCard(Integer cardId, Integer userId) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		// Data attached to the request.
		HttpEntity<?> requestBody = new HttpEntity<>(userId, headers);
		
		// Send request with PUT method.
		restTemplate.put(URL_UpdateCard+"/"+cardId, requestBody, new Object[] {});
	}
}
