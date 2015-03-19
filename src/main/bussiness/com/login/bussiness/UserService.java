package com.login.bussiness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.user.dao.UserDao;

@Service("userService")
public class UserService {
	@Autowired
	private UserDao userDao;

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	public void insertUser(User user){
		userDao.insertUser(user);
	}
}
