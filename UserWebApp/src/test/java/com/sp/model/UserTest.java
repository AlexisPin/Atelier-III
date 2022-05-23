package com.sp.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class UserTest {
	private List<String> stringList;
	private List<Integer> intList;

	@BeforeEach
	public void setUp() {
		System.out.println("[BEFORE TEST] -- Add User to test");
		stringList = new ArrayList<String>();
		intList = new ArrayList<Integer>();
		stringList.add("user1");
		stringList.add("123");
		stringList.add("M");
		stringList.add("P");
		stringList.add("M");
		stringList.add("user1@user1.com");
		intList.add(1);
		intList.add(2);
		intList.add(3);
	}

	@AfterEach
	public void tearDown() {
		System.out.println("[AFTER TEST] -- CLEAN User list");
		stringList = null;
	}

	@Test
	public void createUser() {
		for(String msg:stringList) {
			for(String msg2:stringList) {
				for(String msg5:stringList) {
					for(String msg6:stringList) {
						for(String msg7:stringList) {
							for(Integer msg4:intList) {
								User u=new User(msg4, msg, msg2, 1000, msg5, msg6, msg7, intList);
								System.out.println("msg:"+msg+", msg2:"+msg2+", msg4:"+msg4+", msg5 :"+msg5+", msg6 :"+msg6+", msg7 :"+msg7);
								assertTrue(u.getId().intValue() == msg4.intValue());
								assertTrue(u.getAccount().intValue() == 1000);
								assertTrue(u.getCardList() == intList);
								assertTrue(u.getEmail() == msg7);
								assertTrue(u.getLastName() == msg5);
								assertTrue(u.getSurName() == msg6);
								assertTrue(u.getLogin() == msg);
								assertTrue(u.getPwd() == msg2);
					
							}	
						}
					}
				}
			}
		}

	}
	
	@Test
	public void displayUser() {
		User u= new User(1,"user1","123",1000,"M","P","user1@user1.com",new ArrayList<Integer>());
		String expectedResult="User [id=1, login=user1, pwd=123, account=1000, lastName=M, surName=P, email=user1@user1.com, cardList=[]]";
		assertTrue(u.toString().equals(expectedResult));
		
	}


}
