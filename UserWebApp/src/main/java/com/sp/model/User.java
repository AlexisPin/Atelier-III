package com.sp.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private Integer usr_id;
	private String login;
	private String pwd;
	private Integer account;
	private String lastName;
	private String surName;
	private String email;
    private List<Integer> cardList = new ArrayList<>();
	
	public User() {
	}
	
	public User(Integer usr_id, String login, String pwd, Integer account, String lastName, String surName, String email,List<Integer> cardList
			) {
		super();
		this.usr_id = usr_id;
		this.login = login;
		this.pwd = pwd;
		this.account = account;
		this.lastName = lastName;
		this.surName = surName;
		this.email = email;
		this.cardList = cardList;
	}
	
	public Integer getId() {
		return usr_id;
	}

	public void setId(Integer id) {
		this.usr_id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getCardList() {
		return cardList;
	}

	public void setCardList(List<Integer> cardList) {
		this.cardList = cardList;
	}

	
	@Override
	public String toString() {
		return "User [id=" + usr_id + ", login=" + login + ", pwd=" + pwd + ", account=" + account + ", lastName="
				+ lastName + ", surName=" + surName + ", email=" + email + ", cardList=" + cardList + "]";
	}
}
