package com.sp.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sp.model.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	UserRepository urepo;

	@BeforeEach
	public void setUp() {
		urepo.save(new User(1,"user1","123",1000,"M","P","user1@user1.com",new ArrayList<Integer>()));
	}

	@AfterEach
	public void cleanUp() {
		urepo.deleteAll();
	}

	@Test
	public void saveUser() {
		urepo.save(new User(1,"user1","123",1000,"M","P","user1@user1.com",new ArrayList<Integer>()));
		assertTrue(true);
	}

	@Test
	public void saveAndGetUser() {
		urepo.deleteAll();
		urepo.save(new User(2,"user2","123",1000,"u2","P","user2@user2.com",new ArrayList<Integer>()));
		List<User> userList = new ArrayList<>();
		urepo.findAll().forEach(userList::add);
		assertTrue(userList.size() == 1);
		assertTrue(userList.get(0).getLogin().equals("user2"));
		assertTrue(userList.get(0).getLastName().equals("u2"));
		assertTrue(userList.get(0).getEmail().equals("user2@user2.com"));
	}

	@Test
	public void getHero() {
		Optional<User> user = urepo.findByLogin("user1");
		assertTrue(user.get().getLogin().equals("user1"));
		assertTrue(user.get().getLastName().equals("M"));
		assertTrue(user.get().getEmail().equals("user1@user1.com"));
	}

	@Test
	public void findByLogin() {
		urepo.save(new User(4,"user5","123",1000,"u3","P","user3@user3.com",new ArrayList<Integer>()));
		urepo.save(new User(6,"user2","123",1000,"u5","P","user5@user5.com",new ArrayList<Integer>()));
		urepo.save(new User(7,"user2","123",1000,"u5","P","user5@user5.com",new ArrayList<Integer>()));
		urepo.save(new User(8,"user2","123",1000,"u5","P","user5@user5.com",new ArrayList<Integer>()));
		List<User> userList = new ArrayList<>();
		urepo.findByLogin("user2");
		assertTrue(userList.size() == 1);
	}

}
