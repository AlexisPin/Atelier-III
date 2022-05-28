package com.sp.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.model.User;
import com.sp.repository.UserRepository;
import com.sp.rest.FetchUser;


@Service
public class UserService {
	
	@Autowired
	UserRepository uRepository;
	
	FetchUser fetch = new FetchUser();

	public void addUser(User u) {
		List<Integer> cardsId = fetch.getCards();
		List<Integer> userCardsId = new ArrayList<>();
		Collections.shuffle(cardsId);
		
		if(cardsId.size() > 4) {
			for (int j = 0; j < 5; j++) {
				Integer card = cardsId.get(j);
				userCardsId.add(card);
			}
			u.setCardList(userCardsId);
		}
		uRepository.save(u);
		for (Integer cardId : userCardsId) {
			fetch.updateCard(cardId, u.getId());
		}
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

	public User updateUser(Integer id, User u) {
		Optional<User> user = uRepository.findById(id);
		if (user.isPresent()) {
			User uGet = user.get();
			uGet.setCardList(u.getCardList());
			uGet.setAccount(u.getAccount());
			
			uRepository.save(uGet);
			return uGet;
		}
		else {
			return null;
		}
	}

	public User getUserByLogin(String login) {
		Optional<User> uOpt = uRepository.findByLogin(login);
		if (uOpt.isPresent()) {
			return uOpt.get();
		}else {
			return null;
		}
	}
	

}
