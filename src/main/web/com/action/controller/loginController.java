package com.action.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.User;
import com.login.bussiness.UserService;

@Controller
public class loginController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String registerActionMethod(User user) {
		userService.insertUser(user);
		return "redirect:/login.do";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String loginActionMethod(User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getEmail(), user.getPassword());
		try {
			subject.login(token);
			return "redirect:/home/index.do";
		} catch (Exception e) {
			e.printStackTrace();
			return "login/login";
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
