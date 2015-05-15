package com.action.taskManage;

import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.action.controller.ActionController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bussiness.exception.BusinessException;
import com.entity.work.AccessServerTask;
import com.entity.work.AccessWebTask;
import com.entity.work.ScriptTask;
import com.entity.work.Task;
import com.entity.work.TaskTemplate;
import com.entity.work.ValidateTask;
import com.task.bussiness.TaskGroupService;
import com.task.bussiness.TaskService;
import com.utils.business.PandaConstants;
import com.utils.business.Utils;

/**
 * 
 * @author Allen
 * Task controller every session will create new instance.
 */
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TaskController extends ActionController implements Serializable {

	private static final long serialVersionUID = -1643088089856236132L;

	private static Logger log=Logger.getLogger(TaskController.class.getName());
	
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

	@RequestMapping(value = "/home/saveTask.do", method = RequestMethod.POST)
	public ModelAndView saveTask(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/home/taskManage.do");
		Task t=getTaskFromRequest(request);
		mv.addObject("groupId",request.getParameter("taskGroupId"));
		taskService.saveTask(t);
		return mv;
	}
	
	@RequestMapping(value = "/getTask.do", method = RequestMethod.GET)
	public ModelAndView getTask(int taskId, PrintWriter writer) {
		ModelAndView mv = new ModelAndView("task/taskModify");
		mv.addObject("task",taskService.findTaskById(taskId));
		return mv;
	}
	
	@RequestMapping(value = "/home/updateTask.do", method = RequestMethod.POST)
	public ModelAndView updateTask(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/home/taskManage.do");
		Task t=getTaskFromRequest(request);
		mv.addObject("groupId",request.getParameter("taskGroupId"));
		taskService.updateTask(t);
		return mv;
	}
	
	@RequestMapping(value = "/home/deleteTask.do", method = RequestMethod.POST)
	public ModelAndView deleteTask(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/home/taskManage.do");
		TaskTemplate t=new TaskTemplate();
		t.setTaskId(Utils.isStringNull(request.getParameter("taskId"))?0:Integer.parseInt(request.getParameter("taskId")));
		taskService.deleteTask(t);
		return mv;
	}

	@RequestMapping(value = "/home/taskExecute.do", method = RequestMethod.GET)
	public ModelAndView initexecuteTask(){
		ModelAndView mv = new ModelAndView("task/taskExecute");
		int userId = (Integer) getSeesionValue("CurrentUserId");
		mv.addObject("TaskGroupList",
				taskGroupService.findTaskGroupByUserId(userId));
		mv.addObject("taskRunClass", "active");
		mv.addObject("TKcurrentTab", "in");
		return mv;
	}
	
	@RequestMapping(value = "/home/getTaskLogs.do", method = RequestMethod.GET)
	public void getTaskLogs(int groupId,PrintWriter writer){
		writer.write(JSON.toJSONString(taskService.findTaskLogsByGroupId(groupId),
				SerializerFeature.PrettyFormat));
		writer.flush();
		writer.close();
	}
	
	@RequestMapping(value = "/home/executeTaskBygroupId.do", method = RequestMethod.GET)
	public ModelAndView executeTask(int groupId){
		ModelAndView mv = new ModelAndView("redirect:/home/taskExecute.do");
		mv.addObject("taskRunClass", "active");
		mv.addObject("TKcurrentTab", "in");
		try {
			taskService.executeTask(groupId);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value = "/home/deleteTaskLog.do", method = RequestMethod.GET)
	public ModelAndView deleteTaskLog(int logId){
		ModelAndView mv = new ModelAndView("redirect:/home/taskExecute.do");
		mv.addObject("taskRunClass", "active");
		mv.addObject("TKcurrentTab", "in");
		taskService.deleteTaskLogByLogId(logId);
		return mv;
	}
	
	@RequestMapping(value = "/home/taskReports.do", method = RequestMethod.GET)
	public ModelAndView getTaskReport(){
		ModelAndView mv = new ModelAndView("task/taskReport");
		mv.addObject("taskReportClass", "active");
		mv.addObject("TKcurrentTab", "in");
		return mv;
	}
	
	@RequestMapping(value = "/getTaskLog.do", method = RequestMethod.GET)
	public void getTaskLog(int logId,PrintWriter writer){
		writer.write(taskService.findTaskLogByLogId(logId).getTaskLog());
	}
	/**
	 * Convert from request parameter into Task object.
	 * @param request
	 * @return
	 */
	private Task getTaskFromRequest(HttpServletRequest request) {
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
			awt.setUserName(Utils.isStringNull(request.getParameter("userName"))?"":request.getParameter("userName"));
			String password=request.getParameter("userPassword");
			awt.setPassword(Utils.isStringNull(password)?"":password);
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
		
		if (t instanceof AccessServerTask) {
			AccessServerTask ast=new AccessServerTask();
			ast.setHostName(request.getParameter("serverUrl"));
			ast.setUserName(request.getParameter("userName"));
			ast.setPassWord(Utils.encrypt(request.getParameter("userPassword")));
			ast.setPort(request.getParameter("port"));
			ast.setCatalogName(PandaConstants.ACCESSSEV_CATALOG_NAME);
			t=ast;
		}
		
		t.setCatalogId(catalogId);
		t.setTaskName(request.getParameter("taskName"));
		t.setComments(request.getParameter("comments"));
		t.setTaskId(Utils.isStringNull(request.getParameter("taskId"))?0:Integer.parseInt(request.getParameter("taskId")));
		t.setGroupId(Utils.isStringNull(request.getParameter("taskGroupId"))?0:Integer.parseInt(request.getParameter("taskGroupId")));
		t.setRelativeId(Utils.isStringNull(request.getParameter("relativeTaskId"))?0:Integer.parseInt(request.getParameter("relativeTaskId")));
		t.setIndex(Utils.isStringNull(request.getParameter("taskIndex"))?0:Integer.parseInt(request.getParameter("taskIndex")));
		t.setCreateDate(Utils.getCurrentTimes());
		return t;
	}
}
