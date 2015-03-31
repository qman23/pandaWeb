package com.entity.work;

import java.util.HashMap;

public class AcessWebTask extends Task {

	private String Url;
	private String UserName;

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	private String Password;

	@Override
	protected void setData() {
		data = new HashMap<String, Object>();
		data.put("taskId", taskId);
		data.put("Url", Url);
		data.put("UserName", UserName);
		data.put("Password", Password);
		data.put("catalogId", catalogId);
		data.put("groupId", groupId);
	}

}
