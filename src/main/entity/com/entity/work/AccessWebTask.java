package com.entity.work;

import java.util.HashMap;

public class AccessWebTask extends Task {

	private String Url;
	private String UserName;
	private String Password;

	
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


	@Override
	protected void setData() {
		data = new HashMap<String, Object>();
		data.put("Url", Url);
		data.put("UserName", UserName);
		data.put("Password", Password);
	}

}
