package edu.onlineshop.dal.entity;

//import java.sql.Date;

//import java.util.*;

public class Account {

	private long userID;
	private double balance;
	private long accountNumber;
//	private List<String> orderHistory; 

	public Account() {
//		this.orderHistory = new ArrayList();
	}

	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}


	@Override
	public String toString() {
		return "Account [userID=" + userID + ", balance=" + balance + ", accountNumber=" + accountNumber
				+ "]";
	}

	
}