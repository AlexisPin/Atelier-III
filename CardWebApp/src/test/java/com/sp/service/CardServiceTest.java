package com.sp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sp.model.Card;
import com.sp.repository.CardRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CardService.class)

public class CardServiceTest {
	
	@MockBean
	private CardRepository cRepo;
	
	@Autowired
	private CardService cService;
	
	Card tmpCard = new Card(1,"jdoe", "strong","famille","affinité","imgURL","petiteimage", 100,50,25,2,1000,2);
	
	@Test
	public void getCard() {
		Mockito.when(
				cRepo.findById(Mockito.any())
				).thenReturn(Optional.ofNullable(tmpCard));
		Card cardInfo=cService.getCard(45);
		assertTrue(cardInfo.toString().equals(tmpCard.toString()));
	}


}
