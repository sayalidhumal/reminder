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

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	LoginService loginService;

	@GetMapping(path = "/signup")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", User.builder().build());
		return "welcome/signup";
	}

	@PostMapping(path = "/signup")
	public String processSignup(@ModelAttribute(value = "user") User user, Model model) {
		try {
			userService.addUser(user);
			return "redirect:/login";
		} catch (DomainException e) {
			model.addAttribute("errorMessage", e.getViewMessage());
			return "welcome/signup";
		}
	}

	@GetMapping(path = "/login")
	public String showloginForm() {
		return "welcome/login";
	}

	@PostMapping(path = "/login")
	public String processLogin(@ModelAttribute(value = "username") String userName,
	        @ModelAttribute(value = "password") String password, Model model) {
		try {
			log.info("authenticating user: {}",userName);
			loginService.processLogin(userName, password);
			return "user/main";
		} catch (DomainException e) {
			model.addAttribute("errorMessage", e.getViewMessage());
			return "welcome/login";
		}

	}

	@GetMapping(path = "/")
	public String main() {
		return "redirect:/login";
	}
}
