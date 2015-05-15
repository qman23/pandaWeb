package com.task.dao;

import java.util.List;

import com.entity.work.TaskLog;

public interface TaskLogDao {

	public void addTaskLog(TaskLog tl);
	
	public List<TaskLog> findTaskLogsByGroupId(int groupId);
	
	public void deleteTaskLog(TaskLog tl);
	
	public void deleteTaskLogsByGroupId(int groupId);
	
	public TaskLog findTaskLogByLogId(int logId);
}
