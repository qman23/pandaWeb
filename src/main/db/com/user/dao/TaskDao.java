package com.user.dao;

import java.util.List;
import java.util.Map;

import com.entity.work.Task;

public interface TaskDao {
	public List<Task> findTasksByGroupId(int groupId);
	
	public void saveTask(Task t);
	
	public void saveGroupTask(Task t);
	
	public void saveTaskParameter(Task t);
	
	public Task findTaskByTaskId(int taskId);
}
