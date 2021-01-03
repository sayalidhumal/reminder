package com.reminder.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reminder.app.constants.ViewMessage;
import com.reminder.app.exception.DomainException;
import com.reminder.app.model.User;
import com.reminder.app.model.type.ResponseCodeType;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private UserService userService;

	public void processLogin(String userName, String password) {
		User user = userService.getUserByUserName(userName);

		if (user.getPassword().equalsIgnoreCase(password)) {
			return;
		}

		log.info("Login failed for userName: {}. Invalid username/password", userName);
		throw new DomainException(ResponseCodeType.LOGIN_FAILED,
		        ViewMessage.INVALID_CREDENTIALS);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
	        throws UsernameNotFoundException {
		log.info("getting user by username: {}", username);
		User user = userService.getUserByUserName(username);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(
			        user.getUsername(), user.getPassword(), true, true, true, true,
			        AuthorityUtils.createAuthorityList(user.getRole().name()));

		}
		throw new UsernameNotFoundException("User not found.");

	}

}
