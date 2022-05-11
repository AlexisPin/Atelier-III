package com.sp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
public class SpAppCard {
	
	public static void main(String[] args) {
		SpringApplication.run(SpAppCard.class,args);
	}
}
