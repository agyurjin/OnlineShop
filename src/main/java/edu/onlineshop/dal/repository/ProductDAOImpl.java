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

import edu.onlineshop.dal.entity.Product;
import edu.onlineshop.dal.repository.impl.ProductDAO;

@Repository
public class ProductDAOImpl implements ProductDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	@Override
	public List<Product> allProducts() {
		String sqlSelect = "SELECT * FROM products";
		List<Product> product = getJdbcTemplate().query(sqlSelect, new ProductRowMapper());

		if(product.isEmpty()) {
			System.out.println("There is no any product!");
			return new ArrayList<Product>();
		}
		return product;
	}

	@Override
	public void addProduct(final Product product) {
		final String sqlInsert = "INSERT INTO products (ProductName, ProductQuantity, Price) values(?, ?, ?)";
		final KeyHolder keyHolder = new GeneratedKeyHolder();

		getJdbcTemplate().update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sqlInsert,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, product.getProductName());
				ps.setInt(2, product.getProductQuantity());
				ps.setDouble(3, product.getPrice());
				return ps;
			}

		}, keyHolder);
		product.setProductID(keyHolder.getKey().longValue());
		
	}

	@Override
	public void deleteProduct(long id) {
		String sqlDelete = "DELETE FROM products WHERE ProductID = ?";
		int rows = getJdbcTemplate().update(sqlDelete, id);
		
		System.out.println(rows + " row(s) deleted.");
	}

	@Override
	public List<Product> search(long id) {
		String sqlSelect = "SELECT * FROM products where ProductID = ?";
		List<Product> product = getJdbcTemplate().query(sqlSelect, new ProductRowMapper(), id);
		
		if(product.isEmpty()) {
			return new ArrayList<Product>();
		}
		return product;
	}

	@Override
	public void subtractProduct(long id, int number) {
		String sqlSelect = "SELECT * FROM products where ProductID = ?";
		List<Product> product =  getJdbcTemplate().query(sqlSelect, new ProductRowMapper(), id);
		
		int quantity = product.get(0).getProductQuantity();
		int newQuantity = quantity - number;
		
		String query = "UPDATE products SET ProductQuantity = ? where productID = ?";
		getJdbcTemplate().update(query, newQuantity, id);

	}
}

class ProductRowMapper implements RowMapper<Product> {

	public Product mapRow(ResultSet result, int rowNum) throws SQLException {
		Product product = new Product();
		product.setProductID(result.getLong("ProductID"));
		product.setProductName(result.getString("ProductName"));
		product.setProductQuantity(result.getInt("ProductQuantity"));
		product.setPrice(result.getDouble("Price"));
		
		return product;
	}
	
}