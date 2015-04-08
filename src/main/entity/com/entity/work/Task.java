package com.entity.work;

import java.sql.Timestamp;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.utils.business.PandaConstants;

public abstract class Task {

	private Task nextTask;
	protected int taskId;
	protected String taskName;
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	protected Timestamp createDate;
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	protected Timestamp lastModify;
	protected int catalogId;
	protected String catalogName;
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

	public Timestamp getLastModify() {
		return lastModify;
	}

	public void setLastModify(Timestamp lastModify) {
		this.lastModify = lastModify;
	}

	public String getCatalogName() {
		switch(catalogId){
		case PandaConstants.SCRIPT_CATALOG_ID:return PandaConstants.SCRIPT_CATALOG_NAME;
		case PandaConstants.ACCESSWEB_CATALOG_ID:return PandaConstants.ACCESSWEB_CATALOG_NAME;
		case PandaConstants.VALIDATE_CATALOG_ID:return PandaConstants.VALIDATE_CATALOG_NAME;
		default:return "";
		}
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
}
