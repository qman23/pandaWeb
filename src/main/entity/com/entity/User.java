package com.entity;

import java.util.List;

/**
 * User entity use to login and so on ..
 * 
 * @author Allen
 *
 */
public class User {

	private int userid;
	private String email;
	private String password;
	private List<Role> roles;
	public User(){}
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
