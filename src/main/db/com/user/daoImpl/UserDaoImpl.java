package com.user.daoImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.security.User;
import com.user.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao{

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

	public void deleteUser(User user) {
		SqlSession session=sqlSessionFactory.openSession();
		 session.delete("userDao.deleteUser", user);
	}
	
}
