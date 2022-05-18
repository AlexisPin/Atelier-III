package com.sp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan
@SpringBootApplication
@ComponentScan("com.sp")
public class SpAppStatic {
	
	public static void main(String[] args) {
		SpringApplication.run(SpAppStatic.class,args);
	}
}
