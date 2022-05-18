package com.sp.service;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.sp.model.Card;
import com.sp.repository.CardRepository;

@Service
public class CardService {
	@Autowired
	CardRepository cRepository;
	
	public void addCard(Card c) {
		c.setPrice(5*(c.getAttack()+c.getDefence()+c.getEnergy()+c.getHp()));
		Card createdCard=cRepository.save(c);
		System.out.println(createdCard);
	}
	
	public Card getCard(int id) {
		Optional<Card> cOpt =cRepository.findById(id);
		if (cOpt.isPresent()) {
			return cOpt.get();
		}else {
			return null;
		}
	}
	public List<Card> getCards(){
		Iterable<Card> cOptIt = cRepository.findByUser(null);
		List<Card> cOpt = (List<Card>) cOptIt;	
		return cOpt; 
	}
	
	public Card updateCard(@PathVariable Integer id,@Valid @RequestBody Integer idUser) {
		Optional<Card> card = cRepository.findById(id);
		if (card.isPresent()) {
			card.get().setUser(idUser);
			cRepository.save(card.get());
			return card.get();
		}
		else {
			return null;
		}
		
	}
	
}
