package com.action.systemManage;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
		return mv;
	}
	
	@RequestMapping(value = "/home/userList.do", method = RequestMethod.GET)
	public void getUserList(PrintWriter writer){
		writer.write(JSON.toJSONString(userService.findAllUser(),
				SerializerFeature.PrettyFormat));
		writer.flush();
		writer.close();
	}
}
