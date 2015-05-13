package com.role.dao;

import java.util.List;

import com.entity.security.Role;
import com.entity.security.UserRole;

public interface RoleDao {

		public List<Role> getRoleListByEmail(String email);
		
		public void updateUserRole(UserRole ur);
		
		public void insertUserRole(UserRole ur);
		
		public void deleteUserRole(UserRole ur);
		
}
