package com.entity.work;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

public class TaskLog {
	private int logId;
	private int userId;
	private int groupId;
	private String taskLog;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Timestamp executeDate;
	private int taskStatus;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getTaskLog() {
		return taskLog;
	}

	public void setTaskLog(String taskLog) {
		this.taskLog = taskLog;
	}

	public Timestamp getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(Timestamp executeDate) {
		this.executeDate = executeDate;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}
}
