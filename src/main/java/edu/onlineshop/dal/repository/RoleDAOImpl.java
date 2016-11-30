package edu.onlineshop.dal.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.onlineshop.dal.entity.Role;
import edu.onlineshop.dal.repository.impl.RoleDAO;

@Repository
public class RoleDAOImpl implements RoleDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public List<Role> allRoles() {
		String sqlSelect = "SELECT * FROM roles";
		List<Role> role = getJdbcTemplate().query(sqlSelect, new RoleRowMapper());
		if(role.isEmpty()) {
			System.out.println("There is no any role!");
			return new ArrayList<Role>();
		}
		return role;
	}

	@Override
	public void addRole(Role role) {
		String sqlInsert = "INSERT INTO roles (RoleID, Role) values(?, ?)";
		getJdbcTemplate().update(sqlInsert, role.getRoleID(), role.getRoleName());
	}

	@Override
	public void deleteRole(long id) {
		String sqlDelete = "DELETE FROM roles WHERE RoleID = ?";
		int rows = getJdbcTemplate().update(sqlDelete, id);
		
		System.out.println(rows + " row(s) deleted.");
	}

	@Override
	public Role getRoleByID(long id) {
		String query = "Select * from roles where RoleId = ?";
		List<Role> role = getJdbcTemplate().query(query, new RoleRowMapper(), id);
		
		return role.get(0);
	}
}

class RoleRowMapper implements RowMapper<Role> {

	public Role mapRow(ResultSet result, int rowNum) throws SQLException {
		Role role = new Role();
		role.setRoleID(result.getInt("RoleID"));
		role.setRoleName(result.getString("Role"));
		
		return role;
	}
	
}