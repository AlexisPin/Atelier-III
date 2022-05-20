package com.sp.service;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sp.model.AuthUserDto;
import com.sp.model.UserDto;
import com.sp.rest.FetchAuth;
import com.sp.util.CustomErrorType;

@Service
public class AuthService {
	
	FetchAuth fetch = new FetchAuth();
	
	public ResponseEntity<?> authUser(AuthUserDto u) {
		UserDto[] users = fetch.getUsers();
		for(UserDto user : users) {
			System.out.println(user.getLogin());
			if(u.getLogin().equals(user.getLogin()) && u.getPwd().equals(user.getPwd()) ) {

				return new ResponseEntity<UserDto>(user,
		                HttpStatus.OK);
			}
		 }
		return new ResponseEntity<CustomErrorType>(new CustomErrorType("Wrong credentials"),
                HttpStatus.NOT_FOUND);
	}
}
