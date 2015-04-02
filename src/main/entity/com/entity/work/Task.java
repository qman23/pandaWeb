package com.entity.work;

import java.sql.Timestamp;
import java.util.Map;

public abstract class Task {

	private Task nextTask;
	protected int taskId;
	protected String taskName;
	protected Timestamp createDate;
	protected int catalogId;
	protected int groupId;
	protected String comments;
	protected String executeParam;

	public String getExecuteParam() {
		return executeParam;
	}

	public void setExecuteParam(String executeParam) {
		this.executeParam = executeParam;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

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
