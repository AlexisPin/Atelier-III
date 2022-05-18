package com.sp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sp.model.User;



public interface UserRepository extends CrudRepository<User, Integer> {
	public List<User> findByLogin(String login);
}
