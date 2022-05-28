package com.sp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sp.model.User;



public interface UserRepository extends CrudRepository<User, Integer> {
	public Optional<User> findByLogin(String login);
}
