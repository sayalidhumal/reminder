package com.reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.reminder.domain.UserDetail;
import com.reminder.model.UserData;
import com.reminder.repository.UserDetailRepository;
import com.reminder.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserDetailRepository userDetailRepository;

	@GetMapping("/signup")
	public String showSignUpForm(UserDetail user) {
		return "add-user";
	}

	@PostMapping("/adduser")
	public String addUser(UserData user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-user";
		}

		userService.addUser(user);
		return "redirect:/index";
	}

	@GetMapping("/index")
	public String showUserList(Model model) {
		model.addAttribute("users", userDetailRepository.findAll());
		return "index";
	}
}
