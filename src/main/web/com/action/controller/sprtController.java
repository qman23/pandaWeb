package com.action.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.entity.security.Role;
import com.entity.security.User;
import com.ibm.healthchecktool.bean.ConnBean;
import com.ibm.healthchecktool.util.SSHUtil;

@Controller
public class sprtController {
	@Autowired
	SSHUtil sSHUtil;
	
	@Autowired
	ConnBean connBean;
	
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
	
	private void connectToServer(){
		connBean.setHost("mopbz5231.cloud.dst.ibm.com");
		connBean.setPort("22");
		connBean.setName("cr_xserv");
		connBean.setPwd("2teml2ne");
		connBean.setPath("/web/server_root/datapersist/services/tools/eservicepac");
		try {
			sSHUtil.connect(connBean);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@RequestMapping(value="/home/getLogs.do" , method=RequestMethod.GET)
	public void selectLogFiles(PrintWriter writer){
		connectToServer();
		String cmd = "cd "+ connBean.getPath()+";ls *.txt";
		try {
			String tem = sSHUtil.execCmd(cmd);
			String[] result = tem.split("\n");
			String jsonString = JSON.toJSONString(result,SerializerFeature.PrettyFormat);
			writer.write(jsonString);
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
