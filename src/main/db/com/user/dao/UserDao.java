package com.user.dao;

import com.entity.User;

public interface UserDao {
    public User findByEmail(String email);  
    public void insertUser(User user); 
}  