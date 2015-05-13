package com.action.systemManage;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.entity.security.Role;
import com.entity.security.User;
import com.login.bussiness.UserService;
import com.utils.business.Utils;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/home/userManage.do", method = RequestMethod.GET)
	public ModelAndView changeActionMethod() {
		ModelAndView mv = new ModelAndView("systemManage/userManage");
		mv.addObject("userManageClass", "active");
		mv.addObject("SMcurrentTab", "in");
		return mv;
	}
	
	@RequestMapping(value = "/home/userList.do", method = RequestMethod.GET)
	public void getUserList(PrintWriter writer){
		writer.write(JSON.toJSONString(userService.findAllUser(),
				SerializerFeature.PrettyFormat));
		writer.flush();
		writer.close();
	}
	
	@RequestMapping(value = "/home/deleteUser.do", method = RequestMethod.GET)
	public ModelAndView deleteUser(int userId){
		ModelAndView mv = new ModelAndView("redirect:/home/userManage.do");
		User u=new User();
		u.setUserid(userId);
		userService.deleteUserWithTasks(u);
		return mv;
	}
	
	@RequestMapping(value = "/home/updateUser.do", method = RequestMethod.POST)
	public ModelAndView updateUser(User u){
		ModelAndView mv = new ModelAndView("redirect:/home/userManage.do");
		u.setPassword(Utils.encrypt(u.getPassword()));
		userService.updateUser(u);
		return mv;
	}
	
	@RequestMapping(value = "/home/updateUserRole.do", method = RequestMethod.POST)
	public ModelAndView updateUserRole(String email,int roleId){
		ModelAndView mv = new ModelAndView("redirect:/home/userManage.do");
		User u=new User();
		u.setEmail(email);
		Role role=new Role();
		role.setRoleId(roleId);
		userService.insertOrUpdateUserRole(u, role);
		return mv;
	}
}
