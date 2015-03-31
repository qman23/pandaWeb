package com.entity.work;

import java.util.Map;

public abstract class Task {

	private Task nextTask;
	protected int taskId;
	protected int catalogId;
	protected int groupId;

	public Map data;

	public Map getData() {
		if (data == null) {
			setData();
		}
		return data;
	}

	protected abstract void setData();

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public Task getNextTask() {
		return nextTask;
	}

	public void setNextTask(Task nextTask) {
		this.nextTask = nextTask;
	}

}
