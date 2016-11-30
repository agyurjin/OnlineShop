package edu.onlineshop.dal.repository.impl;

import java.util.List;

import edu.onlineshop.dal.entity.Role;

public interface RoleDAO {

	public List<Role> allRoles();
	public void addRole(Role role);
	public void deleteRole(long id);
	public Role getRoleByID(long id);
}
