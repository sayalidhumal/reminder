package com.reminder.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reminder.app.constants.ViewMessage;
import com.reminder.app.domain.UserDetailEntity;
import com.reminder.app.exception.DomainException;
import com.reminder.app.model.User;
import com.reminder.app.model.type.ResponseCodeType;
import com.reminder.app.model.type.RoleType;
import com.reminder.app.repository.UserDetailRepository;

@Repository
public class UserDaoService implements UserDaoServiceInterface {

	@Autowired
	UserDetailRepository userDetailRepository;

	@Override
	public void addUser(UserDetailEntity user) {
		userDetailRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		Iterable<UserDetailEntity> userDetails = userDetailRepository.findAll();
		List<UserDetailEntity> userList = new ArrayList<>();
		userDetails.forEach(userList::add);
		
		return userList.stream()
		        .map(this::map)
		        .collect(Collectors.toList());
	}

	@Override
	public User getUserbyUserName(String userName) {
		Optional<UserDetailEntity> optionalUserDetailEntity = userDetailRepository
		        .findById(userName);

		UserDetailEntity userDetailEntity = optionalUserDetailEntity
		        .orElseThrow(() -> new DomainException(
		                ResponseCodeType.NO_DATA_FOUND, ViewMessage.INVALID_CREDENTIALS));
		return map(userDetailEntity);
	}

	@Override
	public Optional<UserDetailEntity> getOptionalUserByUserName(String userName) {
		return userDetailRepository.findById(userName);
	}

	@Override
	public List<UserDetailEntity> getUsersByEmail(String email) {
		return userDetailRepository.findByEmail(email);
	}

	private User map(UserDetailEntity userDetailEntity) {
		Optional<RoleType> roleType = RoleType.byName(userDetailEntity.getRole());
		return User.builder()
		        .firstName(userDetailEntity.getFirstName())
		        .email(userDetailEntity.getEmail())
		        .lastName(userDetailEntity.getLastName())
		        .password(userDetailEntity.getPassword())
		        .username(userDetailEntity.getUserName())
		        .role(roleType.orElse(RoleType.USER))
		        .build();
	}

}
