package com.task.bussiness;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
















import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.work.Task;
import com.entity.work.TaskTemplate;
import com.task.dao.TaskDao;



@Service("taskService")
public class TaskService {

	@Autowired
	private TaskDao taskDao;
	
	public List<Task> findTasksByGroupId(int groupId){
		return taskDao.findTasksByGroupId(groupId);
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
