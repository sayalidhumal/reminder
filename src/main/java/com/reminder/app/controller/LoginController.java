package com.reminder.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String showSignUpForm(Model model) {
		model.addAttribute("user", User.builder().build());
		return "welcome/signup";
	}

	@PostMapping("/signup")
	public String processSignup(@ModelAttribute(value = "user") User user, Model model) {
		try {
			userService.addUser(user);
			return "redirect:/login";
		} catch (DomainException e) {
			model.addAttribute("errorMessage", e.getViewMessage());
			return "welcome/signup";
		}
	}

	@GetMapping("/login")
	public String showloginForm() {
		return "welcome/login";
	}

	@PostMapping("/login")
	public String processLogin(@ModelAttribute(value = "username") String userName,
	        @ModelAttribute(value = "password") String password, Model model) {
		try {
			loginService.processLogin(userName, password);
			return "user/main";
		} catch (DomainException e) {
			model.addAttribute("errorMessage", e.getViewMessage());
			return "welcome/login";
		}

	}

	@GetMapping("/")
	public String main() {
		return "redirect:/login";
	}
}
