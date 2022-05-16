package com.sp.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sp.model.Card;
import com.sp.model.UpdateUserDto;
import com.sp.model.User;
import com.sp.repository.UserRepository;
import com.sp.util.CustomErrorType;
import com.sp.util.CustomSuccesType;

@Service
public class MarketService {

	@Autowired
	CardService cService;
	
	@Autowired
	UserService uService;
	
	@Autowired
	UserRepository uRepository;
	
	
	public ResponseEntity<?> buyCard(Integer userId, @Valid UpdateUserDto userRequest) {
		User currentUser = uService.getUser(userId);
		
		if(currentUser == null) { 
			return new ResponseEntity<>(new CustomErrorType("Unable to upate. User with id " + userId + " not found."),
                    HttpStatus.NOT_FOUND);
		}
		
		int userAccount = currentUser.getAccount();
		int cardId = userRequest.getCard();
		Card currentCard = cService.getCard(cardId);
		
		if(currentCard.equals(null)) {
			return new ResponseEntity<>(new CustomErrorType("Unable to upate. Card with id " + userRequest.getCard() + " not found."),
                    HttpStatus.NOT_FOUND);
		}
		
		else {
			List<Card> userCardList = currentUser.getCardList();
				if(!userCardList.contains(currentCard)) {
					int cardPrice = currentCard.getPrice();
					if(userAccount >= cardPrice) {
						if(currentCard.getUser() == null)
						{					
							currentUser.setAccount(userAccount - cardPrice);
							List<Card> newCardList = new ArrayList<Card>();
							newCardList.addAll(userCardList);
							newCardList.add(currentCard);
							currentUser.setCardList(newCardList);
							currentCard.setUser(currentUser);
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

		}
		uRepository.save(currentUser);
		return new ResponseEntity<>(new CustomSuccesType(currentUser.getAccount()).getAccount(), HttpStatus.OK);
	}
	
	public ResponseEntity<?> sellCard(Integer userId, @Valid UpdateUserDto userRequest) {
		User currentUser = uService.getUser(userId);
		
		if(currentUser == null) { 
			return new ResponseEntity<>(new CustomErrorType("Unable to upate. User with id " + userId + " not found."),
                    HttpStatus.NOT_FOUND);
		}
		
		int userAccount = currentUser.getAccount();
		int cardId = userRequest.getCard();
		Card currentCard = cService.getCard(cardId);
		
		if(currentCard.equals(null)) {
			return new ResponseEntity<>(new CustomErrorType("Unable to upate. Card with id " + userRequest.getCard() + " not found."),
                    HttpStatus.NOT_FOUND);
		} else {
			List<Card> userCardList = currentUser.getCardList();
			if(userCardList.contains(currentCard)) {
				int cardPrice = currentCard.getPrice();
				currentUser.setAccount(userAccount + cardPrice);
				List<Card> newCardList = new ArrayList<Card>();
				newCardList.addAll(userCardList);
				int sellCard = newCardList.indexOf(currentCard);
				newCardList.remove(sellCard);
				currentUser.setCardList(newCardList);
				currentCard.setUser(null);
			}else {
				return new ResponseEntity<>(new CustomErrorType("Vous ne possédez pas cette carte"),
						HttpStatus.BAD_GATEWAY);
			}
		}
	
		uRepository.save(currentUser);
		return new ResponseEntity<>(new CustomSuccesType(currentUser.getAccount()).getAccount(), HttpStatus.OK);
	}
	
}
