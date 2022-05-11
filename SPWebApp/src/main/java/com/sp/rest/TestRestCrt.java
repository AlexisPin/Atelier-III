package com.sp.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sp.model.Card;


@RestController
public class TestRestCrt {
	
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello Card !!!";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/addcard")
	public void addCard(@RequestBody Card card) {
		System.out.println(card);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/msg/{id1}/{id2}")
	public String getMsg(@PathVariable String id1, @PathVariable String id2) {
		String msg1=id1;
		String msg2=id2;
		return "Composed Message: msg1:"+msg1+"msg2:"+msg2;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/parameters")
	public String getInfoParam(@RequestParam String param1,@RequestParam String param2) {
		return "Parameters: param1:"+param1+"param2:"+param2;
	}
}
