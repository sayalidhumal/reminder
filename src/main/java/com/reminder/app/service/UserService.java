package com.reminder.app.service;

import static com.reminder.app.util.CodeUtil.hasElemets;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reminder.app.dao.UserDaoService;
import com.reminder.app.domain.UserDetailEntity;
import com.reminder.app.exception.DomainException;
import com.reminder.app.model.User;
import com.reminder.app.model.type.ResponseCodeType;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService implements UserServiceInterface {

	@Autowired
	UserDaoService userDaoService;

	@Override
	public void addUser(User user) {

		Optional<UserDetailEntity> existingUser = userDaoService
		        .getOptionalUserByUserName(user.getUsername());

		if (existingUser.isPresent()) {
			log.info("Username {} already present in the system");
			throw new DomainException(ResponseCodeType.USERNAME_ALREADY_EXISTS,
			        user.getUsername() + " is already registered");
		}

		if (hasElemets(userDaoService.getUsersByEmail(user.getEmail()))) {
			log.info("Email {} already present in the system");
			throw new DomainException(ResponseCodeType.EMAIL_ALREADY_EXISTS,
			        user.getEmail() + " is already registered");
		}

		userDaoService.addUser(map(user));
	}

	@Override
	public void updateUser(User user) {
		userDaoService.addUser(map(user));
	}
	
	@Override
	public void deleteUser(String username) {
		userDaoService.deleteUser(username);
	}

	@Override
	public List<User> getAllUsers() {
		return userDaoService.getAllUsers();
	}

	@Override
	public List<User> getAllUsersByKeyword(String searchText) {
		return userDaoService.getAllUsersByKeyword(searchText);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDaoService.getUserbyUserName(userName);
	}

	private UserDetailEntity map(User user) {
		return UserDetailEntity.builder()
		        .firstName(user.getFirstName())
		        .lastName(user.getLastName())
		        .email(user.getEmail())
		        .password(user.getPassword())
		        .userName(user.getUsername())
		        .role(user.getRole().getName())
		        .build();
	}

}
