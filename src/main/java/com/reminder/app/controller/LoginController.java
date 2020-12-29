package com.reminder.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.reminder.app.exception.DomainException;
import com.reminder.app.model.User;
import com.reminder.app.service.LoginService;
import com.reminder.app.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	LoginService loginService;

	@GetMapping("/signup")
	public String showSignUpForm() {
		return "add-user";
	}

	@PostMapping("/adduser")
	public String addUser(User user, BindingResult result,
	        Model model) {
		if (result.hasErrors()) {
			return "add-user";
		}

		userService.addUser(user);
		return "redirect:/index";
	}

	@GetMapping("/login")
	public String showloginForm() {
		return "login";
	}

	@PostMapping("/login")
	public String processLogin(@ModelAttribute(value = "username") String userName,
	        @ModelAttribute(value = "password") String password, Model model) {
		try {
			loginService.processLogin(userName, password);
			return "main";
		} catch (DomainException e) {
			model.addAttribute("errorMessage", e.getViewMessage());
			return "login";
		}

	}

	@GetMapping("/")
	public String main() {
		return "welcome";
	}
}
