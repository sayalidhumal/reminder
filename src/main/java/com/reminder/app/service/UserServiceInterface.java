package com.reminder.app.service;

import java.util.List;

import com.reminder.app.model.User;

public interface UserServiceInterface {
	public void addUser(User user);

	public List<User> getAllUsers();

	public User getUserByUserName(String userName);
}
