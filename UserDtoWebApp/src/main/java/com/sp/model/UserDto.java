package com.sp.model;

import java.util.List;

import java.util.*;

public class UserDto {
	private Integer usr_id;
	private String login;
	private Integer account;		
	
    private List<Integer> cardList = new ArrayList<>();
	
	public UserDto() {
	}
	
	public UserDto(Integer usr_id, String login, Integer account,List<Integer> cardList
			) {
		super();
		this.usr_id = usr_id;
		this.login = login;
		this.account = account;
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

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public List<Integer> getCardList() {
		return cardList;
	}

	public void setCardList(List<Integer> cardList) {
		this.cardList = cardList;
	}

	
	@Override
	public String toString() {
		return "User [id=" + usr_id + ", login=" + login + ", account=" + account + ", cardList=" + cardList + "]";
		}
	}


