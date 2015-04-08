package com.action.taskManage;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.action.controller.ActionController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.task.bussiness.TaskGroupService;
import com.task.bussiness.TaskService;

@Controller
public class TaskController extends ActionController{

	@Autowired
	private TaskGroupService taskGroupService;
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value = "/home/taskManage.do", method = RequestMethod.GET)
	public ModelAndView getUserTaskGroup(){
		ModelAndView mv = new ModelAndView("task/task");
		int userId=(Integer)getSeesionValue("CurrentUserId");
		mv.addObject("TaskGroupList", taskGroupService.findTaskGroupByUserId(userId));
		mv.addObject("taskClass","active");
		mv.addObject("TKcurrentTab","in");
		return mv;
	}
	
	@RequestMapping(value = "/home/getTaskList.do", method = RequestMethod.GET)
	public ModelAndView getUserTask(){
		ModelAndView mv = new ModelAndView("task/task");
		int userId=(Integer)getSeesionValue("CurrentUserId");
		mv.addObject("TaskGroupList", taskGroupService.findTaskGroupByUserId(userId));
		mv.addObject("taskClass","active");
		mv.addObject("TKcurrentTab","in");
		return mv;
	}
		
	@RequestMapping(value="/home/getTasks.do" , method=RequestMethod.GET)
	public void getTasks(int groupId, PrintWriter writer){
		writer.write(JSON.toJSONString(taskService.findTasksByGroupId(groupId),SerializerFeature.PrettyFormat));
		writer.flush();
		writer.close();
	}
}
