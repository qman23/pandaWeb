package com.action.taskManage;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.action.controller.ActionController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.entity.work.AccessWebTask;
import com.entity.work.ScriptTask;
import com.entity.work.Task;
import com.entity.work.ValidateTask;
import com.task.bussiness.TaskGroupService;
import com.task.bussiness.TaskService;
import com.utils.business.PandaConstants;
import com.utils.business.Utils;

@Controller
public class TaskController extends ActionController {

	@Autowired
	private TaskGroupService taskGroupService;
	@Autowired
	private TaskService taskService;

	@RequestMapping(value = "/home/taskManage.do", method = RequestMethod.GET)
	public ModelAndView getUserTaskGroup() {
		ModelAndView mv = new ModelAndView("task/task");
		int userId = (Integer) getSeesionValue("CurrentUserId");
		mv.addObject("TaskGroupList",
				taskGroupService.findTaskGroupByUserId(userId));
		mv.addObject("taskClass", "active");
		mv.addObject("TKcurrentTab", "in");
		return mv;
	}

	@RequestMapping(value = "/home/getTasks.do", method = RequestMethod.GET)
	public void getTasks(int groupId, PrintWriter writer) {
		writer.write(JSON.toJSONString(taskService.findTasksByGroupId(groupId),
				SerializerFeature.PrettyFormat));
		writer.flush();
		writer.close();
	}

	@RequestMapping(value = "/home/saveTasks.do", method = RequestMethod.POST)
	public ModelAndView saveTask(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/home/taskManage.do");
		Task t=getSaveTask(request);
		taskService.saveTask(t);
		return mv;
	}
	
	@RequestMapping(value = "getTask.do", method = RequestMethod.GET)
	public ModelAndView getTask(int taskId, PrintWriter writer) {
		ModelAndView mv = new ModelAndView("task/taskModify");
		mv.addObject(taskService.findTaskById(taskId));
		return mv;
	}
	

	private Task getSaveTask(HttpServletRequest request) {
		int catalogId=Integer.parseInt(request
				.getParameter("taskCatalogId"));
		Task t = Task.getTask(catalogId);
		
		if (t instanceof ScriptTask) {
			ScriptTask st=new ScriptTask();
			st.setScript(request.getParameter("script"));
			st.setExpectResult(request.getParameter("expectResult"));
			st.setCatalogName(PandaConstants.SCRIPT_CATALOG_NAME);
			t=st;
		}
		if (t instanceof AccessWebTask) {
			AccessWebTask awt=new AccessWebTask();
			awt.setUrl(request.getParameter("serverUrl"));
			awt.setUserName(request.getParameter("userName"));
			awt.setPassword(request.getParameter("userPassword"));
			awt.setCatalogName(PandaConstants.ACCESSWEB_CATALOG_NAME);
			t=awt;
		}
		if (t instanceof ValidateTask) {
			ValidateTask vt=new ValidateTask();
			vt.setValidateExpression(request.getParameter("validateExpression"));
			vt.setExpectResult(request.getParameter("expectResult"));
			vt.setCatalogName(PandaConstants.VALIDATE_CATALOG_NAME);
			t=vt;
		}
		
		t.setCatalogId(catalogId);
		t.setTaskName(request.getParameter("taskName"));
		t.setComments(request.getParameter("comments"));
		t.setGroupId(Integer.parseInt(request.getParameter("taskGroupId")));
		t.setRelativeId(Utils.isStringNull(request.getParameter("relativeTaskId"))?0:Integer.parseInt(request.getParameter("relativeTaskId")));
		t.setIndex(Integer.parseInt(request.getParameter("taskIndex")));
		t.setCatalogId(Integer.parseInt(request.getParameter("taskCatalogId")));
		t.setCreateDate(Utils.getCurrentTimes());
		return t;
	}
}
