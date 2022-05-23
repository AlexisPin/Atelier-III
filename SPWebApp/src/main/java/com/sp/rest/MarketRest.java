package com.sp.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sp.model.UpdateUserDto;
import com.sp.service.MarketService;


@CrossOrigin
@RestController
public class MarketRest {
    @Autowired
    MarketService mService;
	
    @RequestMapping(method=RequestMethod.PUT,value="/user/{id}/buy")
    public ResponseEntity<?> buyCard(@PathVariable String id, @RequestBody UpdateUserDto userRequest) {
		return mService.buyCard(Integer.valueOf(id), userRequest);
        
    }
    @RequestMapping(method=RequestMethod.PUT,value="/user/{id}/sell")
    public  ResponseEntity<?> sellCard(@PathVariable String id,@RequestBody UpdateUserDto userRequest) {
		return mService.sellCard(Integer.valueOf(id), userRequest);
  	  
    }
    	
}
