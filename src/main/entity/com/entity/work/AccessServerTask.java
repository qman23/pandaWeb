package com.entity.work;

import java.util.HashMap;

public class AccessServerTask extends Task {

	private String hostName;
	private String userName;
	private String passWord;
	private String port;
	
	@Override
	protected void setData() {
		super.data=new HashMap<String,String>();
		super.data.put("hostName", hostName);
		super.data.put("userName", userName);
		super.data.put("passWord", passWord);
		super.data.put("port", port);
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}
