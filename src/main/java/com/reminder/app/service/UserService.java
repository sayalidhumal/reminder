package com.reminder.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reminder.app.dao.UserDaoService;
import com.reminder.app.model.User;

@Service
public class UserService implements UserServiceInterface{
	
	@Autowired
	UserDaoService userDaoService;

	public void addUser(User user) {
		userDaoService.addUser(null);
	}

	public User getUserByUserName(String userName) {
		return userDaoService.getUserbyUserName(userName);
	}
	
}
