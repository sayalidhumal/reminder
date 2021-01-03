package com.reminder.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.reminder.app.model.User;
import com.reminder.app.repository.UserDetailRepository;
import com.reminder.app.service.UserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserDetailRepository userDetailRepository;

	@GetMapping(path = "/main")
	public String showloginForm() {
		return "user/main";
	}

	@GetMapping(path = "/view-users")
	public String viewAllUsers(Model model) {
		List<User> users = new ArrayList<>();
		try {
			users = userService.getAllUsers();
		} catch (Exception e) {
			log.info("Exception occured while getting all users: {}", e.getMessage(), e);

		}
		model.addAttribute("users", users);
		return "user/viewUsers";
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
		model.addAttribute("users", users);
		return "user/viewUsers";
	}

}
