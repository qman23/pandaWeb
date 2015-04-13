package com.task.bussiness;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;













import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.work.Task;
import com.user.dao.TaskDao;



@Service("taskService")
public class TaskService {

	@Autowired
	private TaskDao taskDao;
	
	public List<Task> findTasksByGroupId(int groupId){
		return taskDao.findTasksByGroupId(groupId);
	}
	
	public Task findTaskById(int taskId){
		return taskDao.findTaskByTaskId(taskId);
	}
	
	public void saveTask(Task t){
		taskDao.saveTask(t);
		taskDao.saveGroupTask(t);
		t.setTaskParameter(getTaskParameter(t));
		taskDao.saveTaskParameter(t);
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
}
