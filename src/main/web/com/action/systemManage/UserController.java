package com.action.systemManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.login.bussiness.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/home/userManage.do", method = RequestMethod.GET)
	public ModelAndView changeActionMethod() {
		ModelAndView mv = new ModelAndView("systemManage/userManage");
		mv.addObject("userManageClass", "active");
		mv.addObject("SMcurrentTab", "in");
		mv.addObject("userList",userService.findAllUser());
		return mv;
	}
}
