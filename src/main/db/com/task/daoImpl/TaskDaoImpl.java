package com.task.daoImpl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.entity.work.Task;
import com.task.dao.TaskDao;

@Repository
public class TaskDaoImpl implements TaskDao{
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	public List<Task> findTasksByGroupId(int groupId) {
		SqlSession session=sqlSessionFactory.openSession();
		return session.selectList("Task.findTasksByGroupId", groupId);
	}
	
	public void saveTask(Task t) {
		SqlSession session=sqlSessionFactory.openSession();
		 session.insert("Task.insertTask",t);
	}

	public void saveGroupTask(Task t) {
		SqlSession session=sqlSessionFactory.openSession();
		 session.insert("Task.insertGroupTask",t);
	}

	public void saveTaskParameter(Task t) {
		SqlSession session=sqlSessionFactory.openSession();
		 session.insert("Task.insertTaskParameter",t);
	}

	public Task findTaskByTaskId(int taskId) {
		SqlSession session=sqlSessionFactory.openSession();
		return session.selectOne("Task.findTaskByTaskId", taskId);
	}

	public void updateGroupTask(Task t) {
		SqlSession session=sqlSessionFactory.openSession();
		session.update("Task.updateGroupTask", t);
	}

	public void updateTask(Task t) {
		SqlSession session=sqlSessionFactory.openSession();
		session.update("Task.updateTask", t);
	}

	public void updateTaskParameter(Task t) {
		SqlSession session=sqlSessionFactory.openSession();
		session.update("Task.updateTaskParameter", t);
	}

	public void deleteGroupTask(Task t) {
		SqlSession session=sqlSessionFactory.openSession();
		session.delete("Task.deleteGroupTask", t);
	}

	public void deleteTask(Task t) {
		SqlSession session=sqlSessionFactory.openSession();
		session.delete("Task.deleteTask", t);
	}

	public void deleteTaskParameter(Task t) {
		SqlSession session=sqlSessionFactory.openSession();
		session.delete("Task.deleteTaskParameter", t);
	}

}
