package com.action.taskManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.action.controller.ActionController;
import com.entity.work.TaskGroup;
import com.task.bussiness.TaskGroupService;
import com.utils.business.Utils;

@Controller
public class TaskController extends ActionController{
	
	@Autowired
	private TaskGroupService taskGroupService;
	
	@RequestMapping(value = "/home/taskManage.do", method = RequestMethod.GET)
	public ModelAndView getUserTaskGroup(){
		ModelAndView mv = new ModelAndView("task/task");
		int userId=(Integer)getSeesionValue("CurrentUserId");
		mv.addObject("TaskGroupList", taskGroupService.findTaskGroupByUserId(userId));
		mv.addObject("taskGroupClass","active");
		mv.addObject("TKcurrentTab","in");
		return mv;
	}
	
	@RequestMapping(value = "/home/taskManage.do", method = RequestMethod.POST)
	public ModelAndView addUserTaskGroup(TaskGroup taskGroup){
		ModelAndView mv = new ModelAndView("redirect:/home/taskManage.do");
		int userId=(Integer)getSeesionValue("CurrentUserId");
		taskGroup.setCreateDate(Utils.getCurrentTimes());
		taskGroupService.insertTaskGroupWithUser(userId,taskGroup);
		return mv;
	}
	
	@RequestMapping(value = "home/taskGroupModify.do", method = RequestMethod.POST)
	public ModelAndView updateUserTaskGroup(TaskGroup taskGroup){
		ModelAndView mv = new ModelAndView("redirect:/home/taskManage.do");
		taskGroup.setCreateDate(Utils.getCurrentTimes());
		taskGroupService.updateTaskGroup(taskGroup);
		return mv;
	}
	
	@RequestMapping(value = "home/deleteTaskGroup.do", method = RequestMethod.GET)
	public ModelAndView delteTaskGroup(TaskGroup taskGroup){
		ModelAndView mv = new ModelAndView("redirect:/home/taskManage.do");
		int userId=(Integer)getSeesionValue("CurrentUserId");
		taskGroupService.deleteTaskGroup(userId,taskGroup);
		return mv;
	}
	
}
