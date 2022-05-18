package com.sp.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class GetCard {

	static final String URL_CARDSID = "http://localhost:8082/cardsId";



    public GetCard() {

    }
    
	public List<Integer> getCards() {

		RestTemplate restTemplate = new RestTemplate();

		// Send request with GET method and default Headers.
		ResponseEntity<List> result = restTemplate.getForEntity(URL_CARDSID, List.class);
		
		List<Integer> cardsId = result.getBody();
		return cardsId;
	}
	
}
