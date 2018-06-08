package com.blog.dao;

import java.util.List;

import com.blog.domain.User;

public interface UserDAO {

	public boolean addUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String emailId);
	public User getUser(String emailId);
	public List<User> getAllUsers();
}