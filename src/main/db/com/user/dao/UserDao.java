package com.user.dao;

import java.util.List;

import com.entity.security.User;

public interface UserDao {
    public User findByEmail(String email);  
    public void insertUser(User user); 
    public void updateUser(User user);
    public List<User> findAllUser();
}  