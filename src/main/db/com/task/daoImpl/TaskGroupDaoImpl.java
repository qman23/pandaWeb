package com.task.daoImpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.work.TaskGroup;
import com.task.dao.TaskGroupDao;

@Component
public class TaskGroupDaoImpl implements TaskGroupDao{

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public TaskGroup findTaskGroupByGroupId(int groupId) {
		SqlSession session=sqlSessionFactory.openSession();
		return session.selectOne("Task.findTaskGroupByGroupId", groupId);
	}

	public void insertTaskGroup(TaskGroup taskGroup) {
		SqlSession session=sqlSessionFactory.openSession();
		session.insert("Task.insertTaskGroup", taskGroup);
	}

	public void updateTaskGroup(TaskGroup taskGroup) {
		SqlSession session=sqlSessionFactory.openSession();
		session.update("Task.updateTaskGroup", taskGroup);
	}

	public void deleteTaskGroup(TaskGroup taskGroup) {
		SqlSession session=sqlSessionFactory.openSession();
		session.delete("Task.deleteTaskGroup", taskGroup);
	}

	public void insertTaskUserGroup(Map map) {
		SqlSession session=sqlSessionFactory.openSession();
		session.delete("Task.insertTaskUserGroup", map);
	}

	public List<TaskGroup> findTaskGroupByUserId(int userId) {
		SqlSession session=sqlSessionFactory.openSession();
		return session.selectList("findTaskGroupByUserId", userId);
	}

	public void deleteTaskUserGroup(Map map) {
		SqlSession session=sqlSessionFactory.openSession();
		session.delete("deleteTaskUserGroup", map);
	}

}
