package com.sp.util;

import com.sp.model.User;

public class CustomSuccesType {

    private Integer account;
	private User user;
    
    public CustomSuccesType(Integer account){
        this.account = account;
    }
    public CustomSuccesType(User user){
        this.user = user;
    }
    public Integer getAccount() {
        return account;
    }
	public User getUser() {
		return user;
	}
}
