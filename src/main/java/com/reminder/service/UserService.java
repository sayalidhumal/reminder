package com.reminder.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.reminder.dao.UserDaoService;
import com.reminder.model.UserData;

public class UserService implements UserServiceInterface{
	
	@Autowired
	UserDaoService userDaoService;

	public void addUser(UserData user) {
		userDaoService.addUser(null);
	}
	
}
