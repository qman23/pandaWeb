package com.user.dao;

import java.util.List;

import com.entity.work.Task;

public interface TaskDao {
	public List<Task> findTasksByGroupId(int groupId);
}
