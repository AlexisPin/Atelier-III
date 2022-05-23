package com.sp.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sp.model.Card;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CardRepositoryTest {
	@Autowired
	CardRepository crepo;

	@BeforeEach
	public void setUp() {
		crepo.save(new Card(1,"jdoe", "strong","famille","affinité","imgURL","petiteimage", 100,50,25,2,1000,2));
	}

	@AfterEach
	public void cleanUp() {
		crepo.deleteAll();
	}

	@Test
	public void saveCard() {
		crepo.save(new Card(1,"jdoe", "strong","famille","affinité","imgURL","petiteimage", 100,50,25,2,1000,2));
		assertTrue(true);
	}

	@Test
	public void saveAndGetCard() {
		crepo.deleteAll();
		crepo.save(new Card(2,"jdoe", "strong","famille","affinité","imgURL","petiteimage", 100,50,25,2,1000,2));
		List<Card> cardList = new ArrayList<>();
		crepo.findAll().forEach(cardList::add);
		assertTrue(cardList.size() == 1);
		assertTrue(cardList.get(0).getId().equals(4));
		assertTrue(cardList.get(0).getName().equals("jdoe"));
		assertTrue(cardList.get(0).getDescription().equals("strong"));
		assertTrue(cardList.get(0).getFamily().equals("famille"));
		assertTrue(cardList.get(0).getAffinity().equals("affinité"));
		assertTrue(cardList.get(0).getImgUrl().equals("imgURL"));
		assertTrue(cardList.get(0).getSmallImgUrl().equals("petiteimage"));
		assertTrue(cardList.get(0).getEnergy().equals(100));
		assertTrue(cardList.get(0).getHp().equals(50));
		assertTrue(cardList.get(0).getDefence().equals(25));
		assertTrue(cardList.get(0).getAttack().equals(2));
		assertTrue(cardList.get(0).getPrice().equals(1000));
		assertTrue(cardList.get(0).getIdUser().equals(2));
	}

	@Test
	public void getCard() {
		List<Card> cardList = crepo.findByName("jdoe");
		assertTrue(cardList.size() == 1);
		assertTrue(cardList.get(0).getId().equals(10));
		assertTrue(cardList.get(0).getName().equals("jdoe"));
		assertTrue(cardList.get(0).getDescription().equals("strong"));
		assertTrue(cardList.get(0).getFamily().equals("famille"));
		assertTrue(cardList.get(0).getAffinity().equals("affinité"));
		assertTrue(cardList.get(0).getImgUrl().equals("imgURL"));
		assertTrue(cardList.get(0).getSmallImgUrl().equals("petiteimage"));
		assertTrue(cardList.get(0).getEnergy().equals(100));
		assertTrue(cardList.get(0).getHp().equals(50));
		assertTrue(cardList.get(0).getDefence().equals(25));
		assertTrue(cardList.get(0).getAttack().equals(2));
		assertTrue(cardList.get(0).getPrice().equals(1000));
		assertTrue(cardList.get(0).getIdUser().equals(2));
	}
	
	@Test
	public void getCardByIdUser() {
		List<Card> cardList = crepo.findByidUser(2);
		assertTrue(cardList.size() == 1);
		assertTrue(cardList.get(0).getId().equals(2));
		assertTrue(cardList.get(0).getName().equals("jdoe"));
		assertTrue(cardList.get(0).getDescription().equals("strong"));
		assertTrue(cardList.get(0).getFamily().equals("famille"));
		assertTrue(cardList.get(0).getAffinity().equals("affinité"));
		assertTrue(cardList.get(0).getImgUrl().equals("imgURL"));
		assertTrue(cardList.get(0).getSmallImgUrl().equals("petiteimage"));
		assertTrue(cardList.get(0).getEnergy().equals(100));
		assertTrue(cardList.get(0).getHp().equals(50));
		assertTrue(cardList.get(0).getDefence().equals(25));
		assertTrue(cardList.get(0).getAttack().equals(2));
		assertTrue(cardList.get(0).getPrice().equals(1000));
		assertTrue(cardList.get(0).getIdUser().equals(2));
	}

	@Test
	public void findByName() {
		crepo.save(new Card(1,"jdoe", "strong","famille","affinité","imgURL","petiteimage", 100,50,25,2,1000,2));
		crepo.save(new Card(2,"jdoe1", "strong","famille","affinité","imgURL","petiteimage", 100,50,25,2,1000,2));
		crepo.save(new Card(3,"jdoe1", "strong","famille","affinité","imgURL","petiteimage", 100,50,25,2,1000,2));
		crepo.save(new Card(4,"jdoe1", "strong","famille","affinité","imgURL","petiteimage", 100,50,25,2,1000,2));
		List<Card> heroList = new ArrayList<>();
		crepo.findByName("jdoe1").forEach(heroList::add);
		assertTrue(heroList.size() == 3);
	}


}
