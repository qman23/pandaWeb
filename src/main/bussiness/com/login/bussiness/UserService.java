package com.login.bussiness;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bussiness.exception.BusinessException;
import com.entity.security.Role;
import com.entity.security.User;
import com.entity.security.UserRole;
import com.entity.work.Task;
import com.entity.work.TaskGroup;
import com.role.dao.RoleDao;
import com.task.bussiness.TaskGroupService;
import com.task.bussiness.TaskService;
import com.user.dao.UserDao;
import com.utils.business.PandaConstants;
import com.utils.business.Utils;

@Service("userService")
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskGroupService taskGroupService;
	
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	public void insertUser(User user){
		userDao.insertUser(user);
		Role role=new Role();
		role.setRoleId(PandaConstants.ROLE_USER);
		insertOrUpdateUserRole(user, role);
	}
	
	public void updateUser(User user){
		userDao.updateUser(user);
	}
	
	public void changeUser(User user,String newpasswd) throws BusinessException{
		User result=this.findByEmail(user.getEmail());
		if(result!=null){
			if(Utils.decrypt(result.getPassword()).equals(user.getPassword())){
				result.setPassword(Utils.encrypt(newpasswd));
				this.updateUser(result);
			}else{
				throw new BusinessException("Invalid Current password!");
			}
		}else{
			throw new BusinessException("User not exist!");
		}
	}
	
	public List<User> findAllUser(){
		return userDao.findAllUser();
	}
	
	public void deleteUserWithTasks(User user){
		List<TaskGroup> taskGroupList=taskGroupService.findTaskGroupByUserId(user.getUserid());
		for(TaskGroup tg:taskGroupList){
			taskService.deleteTaskLogByGroupId(tg.getGroupId());
			taskGroupService.deleteTaskUserGroup(user.getUserid(), tg.getGroupId());
			for(Task t:taskService.findTasksByGroupId(tg.getGroupId())){
				taskService.deleteTask(t);
			}
		}
		deleteUser(user);
	}
	
	public void deleteUser(User user){
		userDao.deleteUser(user);
	}
	
	public List<Role> findRolesByUser(User user){
		return roleDao.getRoleListByEmail(user.getEmail());
	}
	
	public void insertOrUpdateUserRole(User user,Role r){
		if(findRolesByUser(user).size()==0){
			UserRole ur=new UserRole();
			ur.setEmail(user.getEmail());
			ur.setRoleId(r.getRoleId());
			roleDao.insertUserRole(ur);
		}else{
			UserRole ur=new UserRole();
			ur.setEmail(user.getEmail());
			ur.setRoleId(r.getRoleId());
			roleDao.updateUserRole(ur);
		}
	}
}
