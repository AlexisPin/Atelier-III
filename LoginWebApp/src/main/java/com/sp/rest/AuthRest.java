  package com.sp.rest;

  import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
  import org.springframework.web.bind.annotation.RequestBody;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestMethod;
  import org.springframework.web.bind.annotation.RestController;

import com.sp.model.AuthUserDto;
import com.sp.service.AuthService;

  @CrossOrigin(origins = "http://localhost:8080")
  @RestController
  public class AuthRest {
	  
      @Autowired
      AuthService authService;
      
      @RequestMapping(method=RequestMethod.POST,value="/login")
      public ResponseEntity<?> login(@RequestBody AuthUserDto user) {
          return authService.authUser(user);
      }
      
      	
  }