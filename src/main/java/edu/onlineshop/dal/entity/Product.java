package edu.onlineshop.dal.entity;

public class Product {

	private long productID;
	private String productName;
	private int productQuantity;
	private double price;
	
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", productQuantity="
				+ productQuantity + ", price=" + price + "]";
	}
	
	
}
