package com.action.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bussiness.exception.BusinessException;
import com.entity.security.User;
import com.login.bussiness.UserService;
import com.utils.business.Utils;

@Controller
public class loginController extends ActionController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public ModelAndView registerActionMethod(User user) {
		if (userService.findByEmail(user.getEmail()) == null) {
			user.setPassword(Utils.encrypt(user.getPassword()));
			userService.insertUser(user);
			ModelAndView mv = new ModelAndView("redirect:/login.do");
			mv.addObject("message", "Register Success!");
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("login/register");
			mv.addObject("message", "Register Failed,user already exist!");
			return mv;
		}
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView loginActionMethod(User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getEmail(), Utils.encrypt(user.getPassword()));
		token.setRememberMe(user.isRememberMe());
		try {
			subject.login(token);
			logger.debug("------------------User login success: "
					+ user.getEmail() + "," + user.getPassword()
					+ "---------------------");
			return new ModelAndView("redirect:/home/index.do");
		} catch (Exception e) {
			logger.debug("------------------User login failed:"
					+ user.getEmail() + "," + user.getPassword()
					+ "---------------------");
			ModelAndView mv = new ModelAndView("login/login");
			mv.addObject("message",
					"Login Failed,Please check your email or password!");
			mv.addObject("messageType","danger");
			return mv;
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

	@RequestMapping(value = "/logout.do")
	public String logOutActionMethod() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login.do";
	}

	@RequestMapping(value = "/changePwd.do", method = RequestMethod.GET)
	public String changeActionMethod() {
		return "login/changePwd";
	}

	@RequestMapping(value = "/changePwd.do", method = RequestMethod.POST)
	public ModelAndView changeActionMethod(HttpServletRequest request) {
		User u = new User();
		u.setEmail(request.getParameter("email"));
		u.setPassword(request.getParameter("currentPassword"));
		ModelAndView mv;
		try {
			userService.changeUser(u, request.getParameter("newPassword"));
			mv= new ModelAndView("login/login");
			mv.addObject("message",
					"Change Password Sucess,Please login!");
			mv.addObject("messageType","success");
		} catch (BusinessException e) {
			mv= new ModelAndView("login/changePwd");
			mv.addObject("message",
					e.getMessage());
			mv.addObject("messageType","danger");
		}
		return mv;
	}
}
