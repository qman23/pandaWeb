package com.entity.work;

import java.sql.Timestamp;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.utils.business.PandaConstants;

public abstract class Task {

	protected int userId;
	protected int taskId;
	protected int catalogId;
	protected int groupId;
	protected int relativeId;
	protected int index;
	protected String comments;
	protected String catalogName;
	protected String taskName;
	protected String taskParameter;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	protected Timestamp createDate;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	protected Timestamp lastModify;
	
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

	public Timestamp getLastModify() {
		return lastModify;
	}

	public void setLastModify(Timestamp lastModify) {
		this.lastModify = lastModify;
	}

	public String getCatalogName() {
		switch (catalogId) {
		case PandaConstants.SCRIPT_CATALOG_ID:
			return PandaConstants.SCRIPT_CATALOG_NAME;
		case PandaConstants.ACCESSWEB_CATALOG_ID:
			return PandaConstants.ACCESSWEB_CATALOG_NAME;
		case PandaConstants.VALIDATE_CATALOG_ID:
			return PandaConstants.VALIDATE_CATALOG_NAME;
		case PandaConstants.ACCESSSEV_CATALOG_ID:
			return PandaConstants.ACCESSSEV_CATALOG_NAME;
		default:
			return "";
		}
	}

	public static Task getTask(int catalogId) {
		switch (catalogId) {
		case PandaConstants.SCRIPT_CATALOG_ID:
			return new ScriptTask();
		case PandaConstants.ACCESSWEB_CATALOG_ID:
			return new AccessWebTask();
		case PandaConstants.VALIDATE_CATALOG_ID:
			return new ValidateTask();
		case PandaConstants.ACCESSSEV_CATALOG_ID:
			return new AccessServerTask();
		default:
			return null;
		}
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public int getRelativeId() {
		return relativeId;
	}

	public void setRelativeId(int relativeId) {
		this.relativeId = relativeId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getTaskParameter() {
		return taskParameter;
	}

	public void setTaskParameter(String taskParameter) {
		this.taskParameter = taskParameter;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
