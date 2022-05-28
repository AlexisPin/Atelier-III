package com.sp.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sp.model.UserDto;

public class FetchAuth {

	static final String URL_User = "http://user-service:8087/auth/";
	RestTemplate restTemplate;

    public FetchAuth() {
		this.restTemplate = new RestTemplate();
    }
    
	public UserDto getUser(String login) {

		// Send request with GET method and default Headers.
		ResponseEntity<UserDto> result = restTemplate.getForEntity(URL_User + login, UserDto.class);
		
		UserDto user = result.getBody();
		return user;
	}

}
