package com.reminder.app.service;

import com.reminder.app.model.User;

public interface UserServiceInterface {
	public void addUser(User user);

	public User getUserByUserName(String userName);
}
