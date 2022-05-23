package com.sp.controller;

  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestMethod;

  @CrossOrigin
  @Controller 
  public class RequestCrt {


  	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
  	  public String getStatic(Model model) {
  		  return "pages/login.html";
  	}
  	

 
}


