package com.task.dao;

import java.util.List;
import java.util.Map;

import com.entity.work.TaskGroup;

/**
 * Task Group *User Task Group Access
 * @author Allen
 *
 */
public interface TaskGroupDao {
	
	//Task Group
	public TaskGroup findTaskGroupByGroupId(int groupId);

	public void insertTaskGroup(TaskGroup taskGroup);

	public void updateTaskGroup(TaskGroup taskGroup);

	public void deleteTaskGroup(TaskGroup taskGroup);

	//Task User Group
	public void insertTaskUserGroup(Map map);

	public List<TaskGroup> findTaskGroupByUserId(int userId);
	
	public void deleteTaskUserGroup(Map map);
	
}
