package edu.onlineshop.dal.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.onlineshop.dal.entity.Account;
import edu.onlineshop.dal.repository.impl.AccountDAO;

@Repository
public class AccountDAOImpl implements AccountDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public List<Account> allAccounts() {
		String sqlSelect = "SELECT * FROM accounts";
        List<Account> account = getJdbcTemplate().query(sqlSelect, new AccountRowMapper());
		if(account.isEmpty()) {
			System.out.println("There is no any account!");
			return new ArrayList<Account>();
		}
         return account;
	}

	@Override
	public void addAccount(Account account) {
		String sqlInsert = "INSERT INTO accounts (UserID, Balance, AccountNumber) values(?, ?, ?)";
		getJdbcTemplate().update(sqlInsert, account.getUserID(), account.getBalance(), account.getAccountNumber());
	}

	@Override
	public void deleteAccount(long id) {
		String sqlDelete = "DELETE FROM accounts WHERE UserID = ?";
		int rows = getJdbcTemplate().update(sqlDelete, id);
		
		System.out.println(rows + " row(s) deleted.");
	}

	@Override
	public Account serach(long id) {
		String query = "SELECT * FROM accounts where UserID = ?";
        List<Account> account = getJdbcTemplate().query(query, new AccountRowMapper(), id);
        
        if(account.isEmpty()) {
        	return new Account();
        }
        
        return account.get(0);
	}

	@Override
	public void doPayment(long id, double newBalance) {
		String query = "UPDATE accounts SET Balance = ? where UserID = ?";
		getJdbcTemplate().update(query, newBalance, id);
	}
}

class AccountRowMapper implements RowMapper<Account> {
	 
    public Account mapRow(ResultSet result, int rowNum) throws SQLException {
    	Account account = new Account();
    	account.setUserID(result.getLong("UserID"));
    	account.setBalance(result.getDouble("Balance"));
    	account.setAccountNumber(result.getLong("AccountNumber"));
    	        
        return account;
    }     
}
