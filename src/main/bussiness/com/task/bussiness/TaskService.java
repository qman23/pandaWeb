package com.task.bussiness;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.bussiness.exception.BusinessException;
import com.entity.work.Task;
import com.entity.work.TaskLog;
import com.task.dao.TaskDao;
import com.task.dao.TaskLogDao;
import com.utils.business.Utils;
import com.work.bussiness.ExecuteEngine;



@Service("taskService")
@Scope("prototype")
public class TaskService {

	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private TaskLogDao taskLogDao;
	
	public List<Task> findTasksByGroupId(int groupId){
		int i=0;List<Task> result=taskDao.findTasksByGroupId(groupId);
		for(;i<result.size();i++){
			try {
				putTaskParameter(result.get(i));
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public Task findTaskById(int taskId){
		try {
			return putTaskParameter(taskDao.findTaskByTaskId(taskId));
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveTask(Task t){
		taskDao.saveTask(t);
		taskDao.saveGroupTask(t);
		t.setTaskParameter(getTaskParameter(t));
		taskDao.saveTaskParameter(t);
	}
	
	public void updateTask(Task t){
		taskDao.updateGroupTask(t);
		taskDao.updateTask(t);
		t.setTaskParameter(getTaskParameter(t));
		taskDao.updateTaskParameter(t);
	}
	
	public void deleteTask(Task t){
		taskDao.deleteGroupTask(t);
		taskDao.deleteTask(t);
		taskDao.deleteTaskParameter(t);
		
	}
	
	public void addTaskLog(TaskLog tl){
		taskLogDao.addTaskLog(tl);
	}
	
	public List<TaskLog> findTaskLogsByGroupId(int groupId){
		return taskLogDao.findTaskLogsByGroupId(groupId);
	}
	
	public void addTaskLog(Task t,String log,int status){
		TaskLog tl=new TaskLog();
		tl.setGroupId(t.getGroupId());
		tl.setUserId(t.getUserId());
		tl.setTaskLog(log);
		tl.setExecuteDate(Utils.getCurrentTimes());
		tl.setTaskStatus(status);
		addTaskLog(tl);
	}
	
	public List<TaskLog> findTaskLog(int groupId){
		return taskLogDao.findTaskLogsByGroupId(groupId);
	}
	
	public void deleteTaskLogByGroupId(int groupId){
		taskLogDao.deleteTaskLogsByGroupId(groupId);
	}
	
	public void deleteTaskLogByLogId(int logId){
		TaskLog t=new TaskLog();
		t.setLogId(logId);
		taskLogDao.deleteTaskLog(t);
	}
	
	public TaskLog findTaskLogByLogId(int logId){
		return taskLogDao.findTaskLogByLogId(logId);
	}
	
	public void executeTask(int groupId) throws BusinessException{
		ExecuteEngine executeEngine=new ExecuteEngine(findTasksByGroupId(groupId));
		try {
			executeEngine.submitExecuteRequest();
		} catch (InterruptedException e) {
			throw new BusinessException("Task execute error:"+e.getMessage());
		} catch (ExecutionException e) {
			throw new BusinessException("Task execute error"+e.getMessage());
		}
	}
	
	public String getTaskParameter(Task t){
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("TaskParameter");
		Iterator<String> keyIterator= t.getData().keySet().iterator();
		while(keyIterator.hasNext()){
			String key=keyIterator.next();
			Element elment=root.addElement(key);
			elment.setText((String) t.getData().get(key));
		}
		return root.asXML();
	}
	
	public Task putTaskParameter(Task t) throws DocumentException{
		Document document = DocumentHelper.parseText(t.getTaskParameter());
		Element root = document.getRootElement();
		t.data=new HashMap<String,Object>();
		Iterator childElement=root.elementIterator();
		while(childElement.hasNext()){
			Element childNode=(Element) childElement.next();
			String key=childNode.getName();
			String value=childNode.getText();
			t.data.put(key, value);
		}
		return t;
	}
}
