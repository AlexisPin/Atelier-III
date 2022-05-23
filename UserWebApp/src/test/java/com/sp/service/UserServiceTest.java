package com.sp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sp.model.User;
import com.sp.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserService.class)
public class UserServiceTest {
		
	@MockBean
	private UserRepository uRepo;
	
	@Autowired
	private UserService hService;
	
	User tmpUser=new User(1,"user1","123",1000,"M","P","user1@user1.com",new ArrayList<Integer>());
	
	@Test
	public void getHero() {
		Mockito.when(
				uRepo.findById(Mockito.any())
				).thenReturn(Optional.ofNullable(tmpUser));
		User userInfo=hService.getUser(1);
		assertTrue(userInfo.toString().equals(tmpUser.toString()));
	}
}
