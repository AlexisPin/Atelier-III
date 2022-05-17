package com.sp.rest;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import com.sp.model.Card;


@Controller
public class RoomController {

	
	@MessageMapping("/start")
	public Card register(@Payload Card card) {
		System.out.println(card);
		return card;
	}
	
}
