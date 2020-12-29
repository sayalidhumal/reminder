package com.reminder.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reminder.app.constants.ViewMessage;
import com.reminder.app.domain.UserDetailEntity;
import com.reminder.app.exception.DomainException;
import com.reminder.app.model.User;
import com.reminder.app.model.type.ResponseCodeType;
import com.reminder.app.repository.UserDetailRepository;

@Repository
public class UserDaoService
        implements UserDaoServiceInterface {

	@Autowired
	UserDetailRepository userDetailRepository;

	public void addUser(UserDetailEntity user) {
		userDetailRepository.save(user);
	}

	public User getUserbyUserName(String userName) {
		Optional<UserDetailEntity> optionalUserDetailEntity = userDetailRepository
		        .findById(userName);

		UserDetailEntity userDetailEntity = optionalUserDetailEntity
		        .orElseThrow(() -> new DomainException(
		                ResponseCodeType.NO_DATA_FOUND, ViewMessage.INVALID_CREDENTIALS));
		return map(userDetailEntity);
	}

	private User map(UserDetailEntity userDetailEntity) {
		return User.builder()
		        .firstName(userDetailEntity.getFirstName())
		        .email(userDetailEntity.getEmail())
		        .lastName(userDetailEntity.getLastName())
		        .password(userDetailEntity.getPassword())
		        .userName(userDetailEntity.getUserName())
		        .build();
	}

}
