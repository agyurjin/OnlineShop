package edu.onlineshop.dal.repository.impl;

import java.util.List;

import edu.onlineshop.dal.entity.Account;

public interface AccountDAO {

	public List<Account> allAccounts();
	public void addAccount(Account account);
	public void deleteAccount(long id);
	public Account serach(long id);
	public void doPayment(long id, double newBalance);
}
