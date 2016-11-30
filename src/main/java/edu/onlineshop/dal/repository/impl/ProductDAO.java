package edu.onlineshop.dal.repository.impl;

import java.util.List;

import edu.onlineshop.dal.entity.Product;

public interface ProductDAO {

	public List<Product> allProducts();
	public void addProduct(Product product);
	public void deleteProduct(long id);
	public List<Product> search(long id);
	public void subtractProduct(long id, int number);
}
