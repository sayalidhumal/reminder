package com.reminder.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reminder.domain.UserDetail;
import com.reminder.repository.UserDetailRepository;

@Repository
public class UserDaoService implements UserDaoServiceInterface {

	@Autowired
	UserDetailRepository userDetailRepository;

	public void addUser(UserDetail user) {
		userDetailRepository.save(user);
	}

}
