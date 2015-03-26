package com.user.daoImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.User;
import com.user.dao.UserDao;

@Component
public class UserMapperImpl implements UserDao{

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public User findByEmail(String email) {
		SqlSession session=sqlSessionFactory.openSession();
		return session.selectOne("userDao.findByEmail", email);
	}

	public void insertUser(User user) {
		SqlSession session=sqlSessionFactory.openSession();
		 session.insert("userDao.insertUser", user);		
	}

	public void updateUser(User user) {
		SqlSession session=sqlSessionFactory.openSession();
		session.update("userDao.updateUser",user);
	}

	public List<User> findAllUser() {
		SqlSession session=sqlSessionFactory.openSession();
		return session.selectList("userDao.findAllUser", null);
	}
	
}
