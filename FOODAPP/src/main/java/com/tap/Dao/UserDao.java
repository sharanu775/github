package com.tap.Dao;

import com.tap.models.User;
import java.util.List;


public interface UserDao {
 
	void addUser(User user);
	
	int updateUser(User user);
	int deleteUser(int id);
	
	User getUser(int id);
	List<User>getAllUsers();
	
}
