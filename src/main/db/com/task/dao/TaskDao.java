package com.task.dao;

import java.util.List;
import java.util.Map;

import com.entity.work.Task;
import com.entity.work.TaskTemplate;

public interface TaskDao {
	public List<Task> findTasksByGroupId(int groupId);
	
	public void saveTask(Task t);
	
	public void saveGroupTask(Task t);
	
	public void saveTaskParameter(Task t);
	
	public Task findTaskByTaskId(int taskId);
	
	public void updateGroupTask(Task t);
	
	public void updateTask(Task t);
	
	public void updateTaskParameter(Task t);
	
	public void deleteGroupTask(Task t);
	
	public void deleteTask(Task t);
	
	public void deleteTaskParameter(Task t);
}
