package com.login.bussiness;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bussiness.exception.bussnessException;
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
	
	public void updateUser(User user){
		userDao.updateUser(user);
	}
	
	public void changeUser(User user,String newpasswd) throws bussnessException{
		User result=this.findByEmail(user.getEmail());
		if(result.getPassword().equals(user.getPassword())){
			result.setPassword(newpasswd);
			this.updateUser(result);
		}else{
			throw new bussnessException("Invalid Current password!");
		}
	}
	
	public List<User> findAllUser(){
		return userDao.findAllUser();
	}
}
