package com.sp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sp.model.AuthUserDto;
import com.sp.model.User;
import com.sp.model.UserDto;
import com.sp.util.CustomErrorType;

@Service
public class AuthService {
	
	
	@Autowired
	UserService uService;
	
	public ResponseEntity<?> authUser(AuthUserDto u) {
		 Iterable<User> users = uService.getUsers();
		
		for(User user : users) {

	
			if(u.getLogin().equals(user.getLogin()) && u.getPwd().equals(user.getPwd()) ) {
				UserDto uDto = new UserDto(user.getId(),user.getLogin(),user.getAccount(),user.getCardList());
				
				return new ResponseEntity<>(uDto,
		                HttpStatus.OK);
			}
		 }
		return new ResponseEntity<>(new CustomErrorType("Wrong credentials"),
                HttpStatus.NOT_FOUND);
	}
}
