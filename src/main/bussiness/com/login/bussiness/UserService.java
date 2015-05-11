package com.login.bussiness;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bussiness.exception.BusinessException;
import com.entity.security.User;
import com.user.dao.UserDao;
import com.utils.business.Utils;

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
	
	public void changeUser(User user,String newpasswd) throws BusinessException{
		User result=this.findByEmail(user.getEmail());
		if(result.getPassword().equals(user.getPassword())){
			result.setPassword(Utils.encrypt(newpasswd));
			this.updateUser(result);
		}else{
			throw new BusinessException("Invalid Current password!");
		}
	}
	
	public List<User> findAllUser(){
		return userDao.findAllUser();
	}
}
