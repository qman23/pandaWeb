package com.task.bussiness;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.work.TaskGroup;
import com.user.dao.TaskGroupDao;

@Service("taskGroupService")
public class TaskGroupService {

	@Autowired
	private TaskGroupDao taskGroupDao;

	public TaskGroup findTaskGroupByGroupId(int groupId) {
		return taskGroupDao.findTaskGroupByGroupId(groupId);
	}

	public void insertTaskGroup(TaskGroup taskGroup) {
		taskGroupDao.insertTaskGroup(taskGroup);
	}

	public void updateTaskGroup(TaskGroup taskGroup) {
		taskGroupDao.updateTaskGroup(taskGroup);
	}

	public void deleteTaskGroup(int userId,TaskGroup taskGroup) {
		deleteTaskUserGroup(userId,taskGroup.getGroupId());
		taskGroupDao.deleteTaskGroup(taskGroup);
	}

	public void insertTaskUserGroup(int userId,int groupId) {
		HashMap<String, Integer> map=new HashMap<String,Integer>();
		map.put("userId", userId);
		map.put("groupId",groupId);
		taskGroupDao.insertTaskUserGroup(map);
	}

	public List<TaskGroup> findTaskGroupByUserId(int userId) {
		return taskGroupDao.findTaskGroupByUserId(userId);
	}
	
	public void updateTaskUserGroup(int userId,int oldgroupId,int newgroupId){
		deleteTaskUserGroup(userId,oldgroupId);
		insertTaskUserGroup(userId,newgroupId);
	}
	
	public void deleteTaskUserGroup(int userId,int groupId){
		HashMap<String, String> map=new HashMap<String,String>();
		map.put("userId", String.valueOf(userId));
		map.put("groupId", String.valueOf(groupId));
		taskGroupDao.deleteTaskUserGroup(map);
	}
	
	public void insertTaskGroupWithUser(int userId,TaskGroup taskGroup){
		insertTaskGroup(taskGroup);
		insertTaskUserGroup(userId,taskGroup.getGroupId());
	}
}
