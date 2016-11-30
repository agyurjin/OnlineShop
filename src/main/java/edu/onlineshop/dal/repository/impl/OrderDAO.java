package edu.onlineshop.dal.repository.impl;

import java.util.List;

import edu.onlineshop.dal.entity.Order;

public interface OrderDAO {

	public List<Order> allOrders();
	public void addOrder(Order order);
	public void deleteOrder(long id);
	public List<Order> getOrderHistoryById(long id);
	public List<Order> getOrderHistoryByStatus(String status);
	public void getOrderStatus(long id);
	public void updateOrderStatus(long id, String status);
}
