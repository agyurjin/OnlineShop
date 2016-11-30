package edu.onlineshop.dal.customer;

public interface CustomerSupport {
	//User functions
	public void DoPurchase(long userid, long productid, double balance, int number);
	public void ListProducts();
	public void OrderHistory(long id);
	public void OrderStatus(long id);
	public void DeleteProfile(long id);
	
	//Admin function
	public void Deliver();
	public void OrderList();
}