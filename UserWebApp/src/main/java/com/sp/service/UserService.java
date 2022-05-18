package com.sp.service;


import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sp.model.User;
import com.sp.repository.UserRepository;
import com.sp.rest.GetCard;


@Service
public class UserService {
	@Autowired
	UserRepository uRepository;
	
	GetCard getCard = new GetCard();
	
	
	public void addUser(User u) {
		List<Integer> cardsId = getCard.getCards();
		
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
