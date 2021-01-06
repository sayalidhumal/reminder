package com.reminder.app.service;

import java.util.List;

import com.reminder.app.model.User;

public interface UserServiceInterface {
	public void addUser(User user);

	public List<User> getAllUsers();

	public List<User> getAllUsersByKeyword(String searchText);

	public User getUserByUserName(String userName);

	public void updateUser(User user);
	
	public void deleteUser(String username);
}
