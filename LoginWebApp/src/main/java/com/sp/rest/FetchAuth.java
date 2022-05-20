package com.sp.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sp.model.UserDto;

public class FetchAuth {

	static final String URL_Users = "http://localhost:8087/users";
	RestTemplate restTemplate;

    public FetchAuth() {
		this.restTemplate = new RestTemplate();
    }
    
	public UserDto[] getUsers() {

		// Send request with GET method and default Headers.
		ResponseEntity<UserDto[]> result = restTemplate.getForEntity(URL_Users, UserDto[].class);
		
		UserDto[] users = result.getBody();
		return users;
	}

}
