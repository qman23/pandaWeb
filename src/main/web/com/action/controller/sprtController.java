package com.action.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.entity.security.Role;
import com.entity.security.User;

@Controller
public class sprtController {
	
	@RequestMapping(value="/home/ajax.do" , method=RequestMethod.GET)
	public void ajaxTest(@ModelAttribute User user, PrintWriter writer){
		List<Role> roles = new ArrayList<Role>();
		Role r = new Role();
		r.setRoleName("testRole");
		roles.add(r);
		user.setRoles(roles);
		String jsonString = JSON.toJSONString(user,SerializerFeature.PrettyFormat);
		writer.write(jsonString);
		writer.flush();
		writer.close();
	}
}
