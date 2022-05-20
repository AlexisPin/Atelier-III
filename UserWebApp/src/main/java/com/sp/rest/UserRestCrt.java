package com.sp.rest;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.sp.model.User;
import com.sp.service.UserService;


@CrossOrigin
@RestController
public class UserRestCrt {
    @Autowired
    UserService uService;
    

    
    @RequestMapping(method=RequestMethod.POST,value="/user")
    public void addUser(@RequestBody User card) {
        uService.addUser(card);
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/user/{id}")
    public User getUser(@PathVariable String id) {
		User user = uService.getUser(Integer.valueOf(id));
		return user;
    }
    
    @RequestMapping(method=RequestMethod.PUT,value="/user/{id}")
    public User updateUser(@PathVariable String id,@Valid @RequestBody User u) {
		User user = uService.updateUser(Integer.valueOf(id), u);
		return user;
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/users")
    public List<User> getUsers() {
        List<User> users=(List<User>) uService.getUsers();
        return users;
    }
    
}