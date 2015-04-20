package com.task.dao;

import java.util.List;

import com.entity.work.TaskLog;

public interface TaskLogDao {

	public void addTaskLog(TaskLog tl);
	
	public List<TaskLog> findTaskLogsByGroupId(int groupId);
	
}
