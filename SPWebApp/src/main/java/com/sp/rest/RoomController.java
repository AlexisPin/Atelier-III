package com.sp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.sp.model.Card;
import com.sp.model.User;


@Controller
public class RoomController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/join")
	public User register(@Payload User user) {
		simpMessagingTemplate.convertAndSendToUser(user.getSurName(), "/private", user);
		return user;
	}
	
	@MessageMapping("/start")
	@SendTo("/room/public")
	public Card startParty(@Payload Card card) {

		return card;
		
	}
}
