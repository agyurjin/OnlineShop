package edu.onlineshop.dal.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.onlineshop.configuration.AppConfiguration;
import edu.onlineshop.dal.entity.Order;
import edu.onlineshop.dal.entity.Product;
import edu.onlineshop.dal.entity.User;
import edu.onlineshop.dal.repository.AccountDAOImpl;
import edu.onlineshop.dal.repository.OrderDAOImpl;
import edu.onlineshop.dal.repository.ProductDAOImpl;
import edu.onlineshop.dal.repository.UserDAOImpl;
import edu.onlineshop.dal.repository.impl.AccountDAO;
import edu.onlineshop.dal.repository.impl.OrderDAO;
import edu.onlineshop.dal.repository.impl.ProductDAO;
import edu.onlineshop.dal.repository.impl.UserDAO;
import edu.onlineshop.optimizer.Optimize;
import edu.onlineshop.optimizer.Points;

public class Customer implements CustomerSupport {

	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
	ProductDAO productDaoImpl = (ProductDAO) context.getBean("productDAOImpl",ProductDAOImpl.class);
	OrderDAO orderDaoImpl = (OrderDAO) context.getBean("orderDAOImpl",OrderDAOImpl.class);
	AccountDAO accountDaoImpl = (AccountDAO) context.getBean("accountDAOImpl",AccountDAOImpl.class);
	UserDAO userDaoImpl = (UserDAO) context.getBean("userDAOImpl",UserDAOImpl.class);

	@Override
	public void DoPurchase(long userid, long productid, double balance, int number) {
		List<Product> product = productDaoImpl.search(productid);
		if(product.isEmpty()) {
			System.out.println("There is no product with that id!");
		}
		else{
			if(balance < product.get(0).getPrice()*number) {
				System.out.println("Sorry, you don't have enoght money in you balance.");
			}
			else {
				accountDaoImpl.doPayment(userid, (balance - product.get(0).getPrice()*number));
				Order order = new Order();
				order.setProductID(productid);
				order.setUserID(userid);
				order.setStatus("Ordered");
				order.setOrderID(-1);
				orderDaoImpl.addOrder(order);
				System.out.println("You paid for product!");
				productDaoImpl.subtractProduct(productid, number);
			}
		}
	}

	@Override
	public void ListProducts() {
		List<Product> product = productDaoImpl.allProducts();
		for(Product i : product) {
			System.out.println(i.toString());
		}
	}

	@Override
	public void OrderHistory(long id) {
		List<Order> order = orderDaoImpl.getOrderHistoryById(id);
		for(Order i : order) {
			System.out.println(i.toString());
		}
	}

	@Override
	public void OrderStatus(long id) {
		orderDaoImpl.getOrderStatus(id);
	}

	@Override
	public void DeleteProfile(long id) {
		userDaoImpl.deleteUser(id);
		accountDaoImpl.deleteAccount(id);
	}

	@Override
	public void Deliver() {
		List<Order> order = orderDaoImpl.getOrderHistoryByStatus("Ordered");
		List<User> user = new ArrayList<User>();
		for(Order i : order) {
			user.add(userDaoImpl.searchById(i.getUserID()));
		}
		
		List<Points> pointsList = new ArrayList<Points>();
		if(order.size() == user.size()) {
			for(int i =0; i < order.size(); i++) {
				Points point =  new Points();
				point.setId(order.get(i).getOrderID());
				point.setLatitude(user.get(i).getLatitude());
				point.setLongitude(user.get(i).getLongitude());
				pointsList.add(point);
			}
		}
		
		Optimize optimize = new Optimize(pointsList, orderDaoImpl);
		optimize.optimization();
	}

	@Override
	public void OrderList() {
		List<Order> order = orderDaoImpl.getOrderHistoryByStatus("Ordered");
		if(order.isEmpty()) {
			System.out.println("There is no any order to deliver!");
		}
		for(Order i : order) {
			System.out.println(i.toString());
		}
	}

}
