package com.role.daoImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.security.Role;
import com.entity.security.UserRole;
import com.role.dao.RoleDao;

@Repository
public class RoleDaoImpl implements RoleDao{

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public List<Role> getRoleListByEmail(String email) {
		SqlSession session=sqlSessionFactory.openSession();
		return session.selectList("getRoleListByEmail", email);
	}

	public void updateUserRole(UserRole ur) {
		SqlSession session=sqlSessionFactory.openSession();
		session.update("updateUserRole", ur);
	}

	public void insertUserRole(UserRole ur) {
		SqlSession session=sqlSessionFactory.openSession();
		session.insert("insertUserRole", ur);
	}

	public void deleteUserRole(UserRole ur){
		SqlSession session=sqlSessionFactory.openSession();
		session.delete("deleteUserRole", ur);
	}
}
