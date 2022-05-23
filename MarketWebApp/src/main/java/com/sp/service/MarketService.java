package com.sp.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sp.model.CardDto;
import com.sp.model.UpdateUserDto;
import com.sp.model.UserDto;
import com.sp.rest.FetchMarket;
import com.sp.util.CustomErrorType;
import com.sp.util.CustomSuccesType;

@Service
public class MarketService {

	FetchMarket fetch = new FetchMarket();
	
	public ResponseEntity<?> buyCard(Integer userId,UpdateUserDto userRequest) {
		UserDto currentUser = fetch.getUser(userId);
		
		if(currentUser == null) { 
			return new ResponseEntity<>(new CustomErrorType("Unable to upate. User with id " + userId + " not found."),
                    HttpStatus.NOT_FOUND);
		}
		
		int userAccount = currentUser.getAccount();
		int cardId = userRequest.getCard();
		CardDto currentCard = fetch.getCard(cardId);
		
		if(currentCard.equals(null)) {
			return new ResponseEntity<>(new CustomErrorType("Unable to upate. Card with id " + userRequest.getCard() + " not found."),
                    HttpStatus.NOT_FOUND);
		}
		
		else {
			List<Integer> userCardList = currentUser.getCardList();
				if(!userCardList.contains(cardId)) {
					int cardPrice = currentCard.getPrice();
					if(userAccount >= cardPrice) {
						if(currentCard.getIdUser() == null)
						{					
							currentUser.setAccount(userAccount - cardPrice);
							List<Integer> newCardList = new ArrayList<Integer>();
							newCardList.addAll(userCardList);
							newCardList.add(cardId);
							currentUser.setCardList(newCardList);
							fetch.updateUser(currentUser);
							fetch.updateCard(cardId,userId);
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
		return new ResponseEntity<>(new CustomSuccesType(currentUser.getAccount()).getAccount(), HttpStatus.OK);
	}
	
	public ResponseEntity<?> sellCard(Integer userId,UpdateUserDto userRequest) {
		UserDto currentUser = fetch.getUser(userId);
		
		if(currentUser == null) { 
			return new ResponseEntity<>(new CustomErrorType("Unable to upate. User with id " + userId + " not found."),
                    HttpStatus.NOT_FOUND);
		}
		
		int userAccount = currentUser.getAccount();
		int cardId = userRequest.getCard();
		CardDto currentCard = fetch.getCard(cardId);
		
		if(currentCard.equals(null)) {
			return new ResponseEntity<>(new CustomErrorType("Unable to upate. Card with id " + userRequest.getCard() + " not found."),
                    HttpStatus.NOT_FOUND);
		} else {
			List<Integer> userCardList = currentUser.getCardList();
			if(userCardList.contains(cardId)) {
				int cardPrice = currentCard.getPrice();
				currentUser.setAccount(userAccount + cardPrice);
				List<Integer> newCardList = new ArrayList<Integer>();
				newCardList.addAll(userCardList);
				int sellCard = newCardList.indexOf(cardId);
				newCardList.remove(sellCard);
				currentUser.setCardList(newCardList);
				fetch.updateUser(currentUser);
				fetch.updateCard(cardId,0);
				
			}else {
				return new ResponseEntity<>(new CustomErrorType("Vous ne possédez pas cette carte"),
						HttpStatus.BAD_GATEWAY);
			}
		}
		return new ResponseEntity<>(new CustomSuccesType(currentUser.getAccount()).getAccount(), HttpStatus.OK);
	}
	
}
