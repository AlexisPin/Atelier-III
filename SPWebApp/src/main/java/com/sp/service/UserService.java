package com.sp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.model.Card;
import com.sp.model.User;
import com.sp.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository uRepository;
	
	@Autowired
	CardService cService;
	
	public void addUser(User u) {
		List<Card> cards = cService.getCards();
		
		List<Card> userCards = new ArrayList<>();
		if(cards.size() > 4) {
			Collections.shuffle(cards);
			for (int j = 0; j < 5; j++) {
				Card card = cards.get(j);
				userCards.add(card);
				card.setUser(u);
			}
			u.setCardList(userCards);
		}
		uRepository.save(u);
	}
	
	public User getUser(int id) {
		Optional<User> uOpt = uRepository.findById(id);
		if (uOpt.isPresent()) {
			return uOpt.get();
		}else {
			return null;
		}
	}


	public Iterable<User> getUsers() {
		Iterable<User> uOpt =uRepository.findAll();
		return uOpt; 
	}
	

}
