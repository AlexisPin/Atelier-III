package com.sp.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sp.model.CardDto;
import com.sp.model.UserDto;

public class FetchMarket {

	static final String URL_UserID = "http://localhost:8087/user";
	static final String URL_CardID = "http://localhost:8082/card";
	static final String URL_UpdateUser = "http://localhost:8087/user";
	static final String URL_UpdateCard = "http://localhost:8082/card";
	RestTemplate restTemplate;

    public FetchMarket() {
		this.restTemplate = new RestTemplate();
    }
    
	public UserDto getUser(Integer id) {

		// Send request with GET method and default Headers.
		ResponseEntity<UserDto> result = restTemplate.getForEntity(URL_UserID+"/"+id, UserDto.class);
		
		UserDto user = result.getBody();
		return user;
	}
	
	public CardDto getCard(Integer id) {

		// Send request with GET method and default Headers.
		ResponseEntity<CardDto> result = restTemplate.getForEntity(URL_CardID+"/"+id, CardDto.class);
		
		CardDto card = result.getBody();
		return card;
	}
	
	public void updateUser(UserDto user) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		String requestJson = "{\"account\":\"\"}";
		// Data attached to the request.
		HttpEntity<?> requestBody = new HttpEntity<>(user, headers);
		
		// Send request with PUT method.
		restTemplate.put(URL_UpdateUser+"/"+user.getId(), requestBody, new Object[] {});
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
