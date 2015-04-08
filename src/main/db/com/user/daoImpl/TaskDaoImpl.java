package com.user.daoImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.work.Task;
import com.user.dao.TaskDao;

@Component
public class TaskDaoImpl implements TaskDao{
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	public List<Task> findTasksByGroupId(int groupId) {
		SqlSession session=sqlSessionFactory.openSession();
		return session.selectList("Task.findTasksByGroupId", groupId);
	}

}
