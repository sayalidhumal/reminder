package com.reminder.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reminder.app.constants.ViewConstants;
import com.reminder.app.constants.ViewMapping;
import com.reminder.app.exception.DomainException;
import com.reminder.app.model.User;
import com.reminder.app.repository.UserDetailRepository;
import com.reminder.app.service.UserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/main")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserDetailRepository userDetailRepository;

	@GetMapping
	public String showloginForm(Model model) {
		return ViewMapping.MAIN_USER;
	}

	@GetMapping(path = "/view-users")
	public String viewAllUsers(Model model) {
		List<User> users = new ArrayList<>();
		try {
			users = userService.getAllUsers();
		} catch (Exception e) {
			log.info("Exception occured while getting all users: {}", e.getMessage(), e);
		}
		model.addAttribute(ViewConstants.USERS, users);
		return ViewMapping.VIEW_USERS;
	}

	@GetMapping(path = "/add-user")
	public String showAddUserPage(Model model) {
		model.addAttribute(ViewConstants.USER_DETAILS, User.builder().build());
		return ViewMapping.ADD_USER;
	}

	@GetMapping(path = "/search-users")
	public String searchUsers(@ModelAttribute(value = "searchText") String searchText,
	        Model model) {
		List<User> users = new ArrayList<>();
		try {
			users = userService.getAllUsersByKeyword(searchText);
		} catch (Exception e) {
			log.info("Exception occured while getting all users: {}", e.getMessage(), e);
		}

		model.addAttribute(ViewConstants.USERS, users);
		return ViewMapping.VIEW_USERS;
	}

	@PostMapping(path = "/add-user")
	public String addUser(@ModelAttribute(value = "user") User user, Model model) {
		try {
			userService.addUser(user);
			return ViewMapping.REDIRECT_TO_VIEW_USERS;
		} catch (DomainException e) {
			model.addAttribute(ViewConstants.ERROR_MESSAGE, e.getViewMessage());
			return ViewMapping.ADD_USER;
		}
	}

	@GetMapping(path = "/edit-user/{username}")
	public String showEditUserPage(@PathVariable(name = "username") String username,
	        Model model) {
		try {
			User user = userService.getUserByUserName(username);
			model.addAttribute(ViewConstants.USER_DETAILS, user);
			return ViewMapping.EDIT_USER;
		} catch (DomainException e) {
			return ViewMapping.VIEW_USERS;
		}
	}

	@PostMapping(path = "/edit-user")
	public String updateUser(@ModelAttribute(value = "user") User user, Model model) {
		try {
			userService.updateUser(user);
			return ViewMapping.REDIRECT_TO_VIEW_USERS;
		} catch (DomainException e) {
			model.addAttribute(ViewConstants.ERROR_MESSAGE, e.getViewMessage());
			return ViewMapping.REDIRECT_TO_VIEW_USERS;
		}
	}

	@GetMapping(path = "/delete-user/{username}")
	public String deleteUser(@PathVariable(name = "username") String username,
	        Model model) {
		try {
			userService.deleteUser(username);
			return ViewMapping.REDIRECT_TO_VIEW_USERS;
		} catch (DomainException e) {
			model.addAttribute(ViewConstants.ERROR_MESSAGE, e.getViewMessage());
			return ViewMapping.REDIRECT_TO_VIEW_USERS;
		}
	}

}
