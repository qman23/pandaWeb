package com.entity.work;

import java.sql.Timestamp;

public class TaskLog {
	private int userId;
	private int taskId;
	private String taskLog;
	private Timestamp executeDate;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
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
}
