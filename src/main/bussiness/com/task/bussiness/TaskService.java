package com.task.bussiness;

import java.util.List;

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
}
