package com.sp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.aspectj.apache.bcel.util.SyntheticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.model.Card;
import com.sp.model.UpdateUserDto;
import com.sp.model.User;
import com.sp.repository.CardRepository;
import com.sp.repository.UserRepository;
import com.sp.util.CustomErrorType;
import com.sp.util.CustomSuccesType;

@Service
public class UserService {
	@Autowired
	UserRepository uRepository;
	
	@Autowired
	CardRepository cRepository;
	
	public void addUser(User u) {
		List<Card> cards = cRepository.findByUser(null);
		
		if(cards.size() > 4) {
			Collections.shuffle(cards);
			List<Card> userCards = new ArrayList<>();
			for (int j = 0; j < 5; j++) {
				Card card = cards.get(j);
				userCards.add(card);
				card.setUser(u);
				
			}
			u.setCardList(userCards);
		}
		User createdUser=uRepository.save(u);
		System.out.println(createdUser);
	}
	
	public ResponseEntity<?>  getUser(int id) {
		Optional<User> uOpt =uRepository.findById(id);
		if (uOpt.isPresent()) {
			return new ResponseEntity<>(new CustomSuccesType(uOpt.get()).getUser(),
                    HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new CustomErrorType("Unable to find user with id " + id + "."),
                    HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> updateUser(Integer userId, @Valid UpdateUserDto userRequest, @RequestParam String transaction) {
		Optional<User> uOpt = uRepository.findById(userId);
		User currentUser = uOpt.get();
		if(currentUser == null) { 
			return new ResponseEntity<>(new CustomErrorType("Unable to upate. User with id " + userId + " not found."),
                    HttpStatus.NOT_FOUND);
		}
		int userAccount = currentUser.getAccount();
		int cardId = userRequest.getCard();
		Optional<Card> currentCard = cRepository.findById(cardId);
		
		if(currentCard.isEmpty()) {
			return new ResponseEntity<>(new CustomErrorType("Unable to upate. Card with id " + userRequest.getCard() + " not found."),
                    HttpStatus.NOT_FOUND);
		}
		
		if(currentCard.isPresent()) {
			List<Card> userCardList = currentUser.getCardList();
			if(transaction.equals("buy")) {
				if(!userCardList.contains(currentCard.get())) {
					int cardPrice = currentCard.get().getPrice();
					if(userAccount >= cardPrice) {
						if(currentCard.get().getUser() == null)
						{					
							currentUser.setAccount(currentUser.getAccount() - cardPrice);
							List<Card> newCardList = new ArrayList<Card>();
							newCardList.addAll(userCardList);
							newCardList.add(currentCard.get());
							currentUser.setCardList(newCardList);
							currentCard.get().setUser(currentUser);
						} else {
							return new ResponseEntity<>(new CustomErrorType("Un utilisateur possède déja cette carte"),
									HttpStatus.CONFLICT);
						}
					}
					else {
						return new ResponseEntity<>(new CustomErrorType("Vous n'avez pas assez d'argent pour acheter cette carte"),
								HttpStatus.CONFLICT);
					}
				}else {
					return new ResponseEntity<>(new CustomErrorType("Vous possédez déja cette carte"),
							HttpStatus.BAD_GATEWAY);
					}
			} else if (transaction.equals("sell")) {
				if(userCardList.contains(currentCard.get())) {
					int cardPrice = currentCard.get().getPrice();
						currentUser.setAccount(currentUser.getAccount() + cardPrice);
						List<Card> newCardList = new ArrayList<Card>();
						newCardList.addAll(userCardList);
						int sellCard = newCardList.indexOf(currentCard.get());
						newCardList.remove(sellCard);
						currentUser.setCardList(newCardList);
						currentCard.get().setUser(null);
				}else {
					return new ResponseEntity<>(new CustomErrorType("Vous ne possédez pas cette carte"),
							HttpStatus.BAD_GATEWAY);
				}
			} else {
				return new ResponseEntity<>(new CustomErrorType("Transaction non reconnu"),
						HttpStatus.BAD_REQUEST);
			}
		}
		uRepository.save(currentUser);
		cRepository.save(currentCard.get());
		
		return new ResponseEntity<>(new CustomSuccesType(currentUser.getAccount()).getAccount(), HttpStatus.OK);
	}

	public Iterable<User> getUsers() {
		Iterable<User> uOpt =uRepository.findAll();
		return uOpt; 
	}
	

}
