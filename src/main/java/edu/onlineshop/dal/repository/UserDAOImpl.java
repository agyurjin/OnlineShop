package edu.onlineshop.dal.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import edu.onlineshop.dal.entity.User;
import edu.onlineshop.dal.repository.impl.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public List<User> allUsers() {
		String sqlSelect = "SELECT * FROM users";
        List<User> user = getJdbcTemplate().query(sqlSelect, new UserRowMapper());
		if(user.isEmpty()) {
			System.out.println("There is no any user!");
			return new ArrayList<User>();
		}
        return user;
	}

	@Override
	public void addUser(final User user) {
		final String sqlInsert = "INSERT INTO users (RoleID ,FirstName, LastName, EMailAddress, Pass, Latitude, Longitude, LastLoginDate) values(?, ?, ?, ?, ?, ?, ?, ?)";
		final KeyHolder keyHolder = new GeneratedKeyHolder();

		getJdbcTemplate().update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sqlInsert,
						Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, user.getRoleID());
				ps.setString(2, user.getFirstName());
				ps.setString(3, user.getLastName());
				ps.setString(4, user.geteMailAddress());
				ps.setString(5, user.getPassword());
				ps.setDouble(6, user.getLatitude());
				ps.setDouble(7, user.getLongitude());
				ps.setString(8, user.getLastLoginDate());
				return ps;
			}

		}, keyHolder);
		user.setUserID(keyHolder.getKey().longValue());
	}

	@Override
	public void deleteUser(long id) {
		String sqlDelete = "DELETE FROM accounts WHERE UserID = ?";
		int rows = getJdbcTemplate().update(sqlDelete, id);
		
		System.out.println(rows + " row(s) deleted.");
	}

	@Override
	public User search(String email, String pass) {
		String query = "SELECT * FROM users where EMailAddress = ? and pass = ?";
        List<User> user = getJdbcTemplate().query(query, new UserRowMapper(), email, pass);
        
        if(user.size() == 0) {
        	System.out.println("Either email or password are not correct. Try again!");
        	return null;
        }

        return user.get(0);
	}

	@Override
	public User searchById(long id) {
		String query = "SELECT * FROM users where UserID = ?";
        List<User> user = getJdbcTemplate().query(query, new UserRowMapper(), id);
        
        if(user.size() == 0) {
        	System.out.println("User id is not correct!");
        	return new User();
        }

        return user.get(0);
	}

}

class UserRowMapper implements RowMapper<User> {
	 
    public User mapRow(ResultSet result, int rowNum) throws SQLException {
    	User user = new User();
    	user.setUserID(result.getLong("UserID"));
    	user.setRoleID(result.getLong("RoleID"));
    	user.setFirstName(result.getString("FirstName"));
    	user.setLastName(result.getString("LastName"));
    	user.seteMailAddress(result.getString("EMailAddress"));
    	user.setPassword(result.getString("Pass"));
    	user.setLatitude(result.getDouble("Latitude"));
    	user.setLongitude(result.getDouble("Longitude"));
       	user.setLastLoginDate(result.getString("LastLoginDate"));

    	return user;
    }     
}
