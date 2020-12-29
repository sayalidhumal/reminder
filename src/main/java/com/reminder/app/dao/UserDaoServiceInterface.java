package com.reminder.app.dao;

import com.reminder.app.domain.UserDetailEntity;
import com.reminder.app.model.User;

public interface UserDaoServiceInterface {
	public void addUser(UserDetailEntity user);

	public User getUserbyUserName(String userName);
}
