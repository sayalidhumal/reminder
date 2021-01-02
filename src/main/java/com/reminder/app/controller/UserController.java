package com.reminder.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.reminder.app.repository.UserDetailRepository;
import com.reminder.app.service.UserService;

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

}
