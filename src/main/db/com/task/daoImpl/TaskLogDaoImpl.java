package com.task.daoImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.work.TaskLog;
import com.task.dao.TaskLogDao;

@Repository
public class TaskLogDaoImpl implements TaskLogDao{

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public void addTaskLog(TaskLog tl) {
		SqlSession session=sqlSessionFactory.openSession();
		session.insert("Task.addTaskLog", tl);
	}

	public List<TaskLog> findTaskLogsByGroupId(int groupId) {
		SqlSession session=sqlSessionFactory.openSession();
		return session.selectList("Task.findTaskLogsByGroupId",groupId);
	}

	public void deleteTaskLog(TaskLog tl) {
		SqlSession session=sqlSessionFactory.openSession();
		session.delete("Task.deleteTaskLog", tl);
	}

	public void deleteTaskLogsByGroupId(int groupId) {
		SqlSession session=sqlSessionFactory.openSession();
		session.delete("Task.deleteTaskLogsByGroupId", groupId);
	}

	public TaskLog findTaskLogByLogId(int logId) {
		SqlSession session=sqlSessionFactory.openSession();
		return session.selectOne("Task.findTaskLogByLogId",logId);
	}
}
