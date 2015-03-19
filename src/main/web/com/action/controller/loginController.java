package com.action.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.User;
import com.login.bussiness.UserService;

@Controller
public class loginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String registerActionMethod(User user) {
		userService.insertUser(user);
		return "redirect:/home/index.do";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String loginActionMethod(User user) {
		String email = userService.findByEmail(user.getEmail()).getEmail();
		String password = userService.findByEmail(user.getEmail())
				.getPassword();
		if (user.getEmail().equals(email)
				&& user.getPassword().equals(password)) {
			return "redirect:/home/index.do";
		} else {
			return "login/loginError";
		}
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String getLoginActionMethod() {
		return "login/login";
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public String getRegisterActionMethod() {
		return "login/register";
	}
}
