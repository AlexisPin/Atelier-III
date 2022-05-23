package com.sp.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardTest {
	private List<String> stringList;
	private List<Integer> intList;

	@BeforeEach
	public void setUp() {
		System.out.println("[BEFORE TEST] -- Add Card to test");
		stringList = new ArrayList<String>();
		intList = new ArrayList<Integer>();
		stringList.add("normalString1");
		stringList.add("normalString2");
		stringList.add("normalString3");
		stringList.add("normalString4");
		stringList.add("normalString5");
		stringList.add("normalString6");
		stringList.add(";:!;!::!;;<>");
		intList.add(5);
		intList.add(45);
		intList.add(254);
		intList.add(-60);
		intList.add(38550);
		intList.add(500);
		intList.add(-1);
	}
	
	@AfterEach
	public void tearDown() {
		System.out.println("[AFTER TEST] -- CLEAN hero list");
		stringList = null;
		intList = null;
	};

	@Test
	public void createCard() {
		int i = 0;
		for(String msg1:stringList) {
			for(String msg2:stringList) {
							for(String msg6:stringList) {
										for(Integer msg9:intList) {
											for(Integer msg10:intList) {
												for(Integer msg11:intList) {
										i++;
										Card c=new Card(i, msg1, msg2, msg2, msg2, msg1, msg6, msg9, msg10, msg11, msg11, msg11, msg11);
										System.out.println("i:"+i+", "
												+ "msg1:"+msg1+", "
												+ "msg2:"+msg2+", "
												+ "msg6:"+msg6+", "
												+ "msg9:"+msg9+", "
												+ "msg10:"+msg10+", "
												+ "msg11:"+msg11+", "
												);
										assertTrue(c.getId().intValue() == i);
										assertTrue(c.getName() == msg1);
										assertTrue(c.getDescription() == msg2);
										assertTrue(c.getFamily() == msg2);
										assertTrue(c.getAffinity() == msg2);
										assertTrue(c.getImgUrl() == msg1);
										assertTrue(c.getSmallImgUrl() == msg6);
										assertTrue(c.getEnergy().intValue() == msg9.intValue());
										assertTrue(c.getHp().intValue() == msg10.intValue());
										assertTrue(c.getDefence().intValue() == msg11.intValue());
										assertTrue(c.getAttack().intValue() == msg11.intValue());
										assertTrue(c.getPrice().intValue() == msg11.intValue());
										assertTrue(c.getIdUser().intValue() == msg11.intValue());
	
								

													}			
														}
															}
																}
																	}
																}
	}
	

	@Test
	public void displayCard() {
		Card c=new Card(1,"jdoe", "strong","famille","affinité","imgURL","petiteimage", 100,50,25,2,1000,2);
		String expectedResult="Card [id=1, name=jdoe, description=strong, family=famille, affinity=affinité, imgUrl=imgURL, smallImgUrl=petiteimage, energy=100, hp=50, defence=25, attack=2, price=1000, user=2]";
		assertTrue(c.toString().equals(expectedResult));
		
	}

	
	
	
							}
