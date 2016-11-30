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

import edu.onlineshop.dal.entity.Order;
import edu.onlineshop.dal.repository.impl.OrderDAO;

@Repository
public class OrderDAOImpl implements OrderDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public List<Order> allOrders() {
		String sqlSelect = "SELECT * FROM orders";
		List<Order> order = getJdbcTemplate().query(sqlSelect, new OrderRowMapper());
		if(order.isEmpty()){
			System.out.println("There is no any order!");
			return new ArrayList<Order>();
		}
		return order;
	}

	@Override
	public void addOrder(final Order order) {
		final String sqlInsert = "INSERT INTO orders (UserID, ProductID, Status) values (?, ?, ?)";
		final KeyHolder keyHolder = new GeneratedKeyHolder();

		getJdbcTemplate().update(new PreparedStatementCreator() {

		public PreparedStatement createPreparedStatement(
				Connection connection) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, order.getUserID());
			ps.setLong(2, order.getProductID());
			ps.setString(3, order.getStatus());
			return ps;
		}

	}, keyHolder);
	order.setOrderID(keyHolder.getKey().longValue());
}

	@Override
	public void deleteOrder(long id) {
		String sqlDelete = "DELETE FROM orders WHERE OrderID = ?";
		int rows = getJdbcTemplate().update(sqlDelete, id);
		
		System.out.println(rows + " row(s) deleted.");
	}

	@Override
	public List<Order> getOrderHistoryById(long id) {
		String query = "SELECT * FROM orders where UserId = ?";
		List<Order> order = getJdbcTemplate().query(query, new OrderRowMapper(), id);
		
		if(order.isEmpty()){
			System.out.println("There is no any order!");
			return new ArrayList<Order>();
		}
		return order;
	}

	@Override
	public List<Order> getOrderHistoryByStatus(String status) {
		String query = "SELECT * FROM orders where Status = ?";
		List<Order> order = getJdbcTemplate().query(query, new OrderRowMapper(), status);
		
		if(order.isEmpty()){
			System.out.println("There is no any order!");
			return new ArrayList<Order>();
		}
		return order;
	}

	@Override
	public void getOrderStatus(long id) {
		String query = "SELECT * FROM orders where OrderID = ?";
		List<Order> order = getJdbcTemplate().query(query, new OrderRowMapper(), id);
		
		if(order.isEmpty()){
			System.out.println("There is no any order with that ID!");
		}
		else {
			System.out.println(order.toString());
		}
	}

	@Override
	public void updateOrderStatus(long id, String status) {
		String query = "UPDATE orders SET Status = ? where OrderID = ?";
		getJdbcTemplate().update(query, status, id);
	}

}

class OrderRowMapper implements RowMapper<Order> {
	
	public Order mapRow(ResultSet result, int rowNum) throws SQLException {
		Order order = new Order();
		order.setOrderID(result.getLong("OrderID"));
		order.setUserID(result.getLong("UserID"));
		order.setProductID(result.getLong("ProductID"));
		order.setStatus(result.getString("Status"));
		return order;
	}	
}