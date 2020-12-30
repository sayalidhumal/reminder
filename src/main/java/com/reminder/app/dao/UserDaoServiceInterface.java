package com.reminder.app.dao;

import java.util.List;
import java.util.Optional;

import com.reminder.app.domain.UserDetailEntity;
import com.reminder.app.model.User;

public interface UserDaoServiceInterface {
	public void addUser(UserDetailEntity user);

	public User getUserbyUserName(String userName);

	public Optional<UserDetailEntity> getOptionalUserByUserName(String userName);

	public List<UserDetailEntity> getUsersByEmail(String email);
}
