package edu.onlineshop.dal.repository.impl;

import java.util.List;

import edu.onlineshop.dal.entity.User;

public interface UserDAO {

	public List<User> allUsers();
	public void addUser(User user);
	public void deleteUser(long id);
	public User search(String email, String pass);
	public User searchById(long id);
}
