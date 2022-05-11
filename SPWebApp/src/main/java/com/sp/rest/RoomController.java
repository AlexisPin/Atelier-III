package com.sp.rest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sp.model.Card;
import com.sp.model.User;

@CrossOrigin
@Controller
public class RoomController {

	@CrossOrigin
	@MessageMapping("/room.register")
	@SendTo("/game/public")
	public User register(@Payload User user, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username",user.getSurName());
		return user;
	}
	
	@CrossOrigin
	@MessageMapping("/room.send")
	@SendTo("/game/public")
	public Card sendCard(@Payload Card card) {
		return card;
		
	}
}
