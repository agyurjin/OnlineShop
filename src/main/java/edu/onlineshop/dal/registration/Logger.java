package edu.onlineshop.dal.registration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

import edu.onlineshop.configuration.AppConfiguration;
import edu.onlineshop.dal.entity.Account;
import edu.onlineshop.dal.entity.User;
import edu.onlineshop.dal.repository.AccountDAOImpl;
import edu.onlineshop.dal.repository.UserDAOImpl;
import edu.onlineshop.dal.repository.impl.AccountDAO;
import edu.onlineshop.dal.repository.impl.UserDAO;

public class Logger implements Registration {

	private Profile profile = new Profile();
	Scanner sc = new Scanner(System.in);

	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
	AccountDAO accountDaoImpl = (AccountDAO) context.getBean("accountDAOImpl",AccountDAOImpl.class);
	UserDAO userDaoImpl = (UserDAO) context.getBean("userDAOImpl",UserDAOImpl.class);

	public Profile createUserProfile() {
		User user = new User();
		Account account = new Account();

		System.out.println("Input personal information!");
		String input ="";		
		System.out.print("First Name: ");
		do {
			input = sc.nextLine();
		} while(input.isEmpty());
		user.setFirstName(input);
		input = "";
		
		System.out.print("Last Name: ");
		do {
			input = sc.nextLine();
		} while(input.isEmpty());
		user.setLastName(input);
		input = "";
		
		System.out.print("EMail Address: ");
		do {
			input = sc.nextLine();
		} while(input.isEmpty());
		user.seteMailAddress(input);
		input = "";
		
		System.out.print("Password: ");
		do {
			input = sc.nextLine();
		} while(input.isEmpty());
		user.setPassword(input);
		input = "";
		
		boolean quite = true;
		
		 do {
			System.out.print("Confirm Password: ");
			String str = "";
			do {
				str = sc.nextLine();
			} while(str.isEmpty());
			
			if(user.getPassword().equals(str)) {
				System.out.println("Password was set succsessfully!");
				quite = false;
			}
			else {
				System.out.println("Please enter same password!");

				System.out.print("Password: ");
				do {
					input = sc.nextLine();
				} while(input.isEmpty());
				user.setPassword(input);
			}
		} while(quite);
		System.out.println("Input address!");
		
		System.out.print("Latitude: ");
		user.setLatitude(sc.nextDouble());
		
		System.out.print("Longitude: ");
		user.setLongitude(sc.nextDouble());
		
		System.out.println("Input card information!");
		
		System.out.print("Card number: ");
		account.setAccountNumber(sc.nextLong());
		
		System.out.print("Card balance: ");
		account.setBalance(sc.nextDouble());
		
		UserProfile userprofile = new UserProfile(user.getUserID(), user.getFirstName(), user.getLastName(), user.geteMailAddress(), user.getPassword(), 
				user.getLatitude(), user.getLongitude(), account.getAccountNumber(), account.getBalance());

		user.setLastLoginDate("November");
		user.setRoleID(userprofile.getRoleID());
		userDaoImpl.addUser(user);
		account.setUserID(user.getUserID());
		accountDaoImpl.addAccount(account);

		return userprofile;
	}

	public Profile logIn(String email, String pass) {
		User user = new User();
		Account account = new Account();
		
		user = userDaoImpl.search(email, pass);
		if(user != null) {
			account = accountDaoImpl.serach(user.getUserID());
		} else {
			return new Profile();
		}
		this.profile.setUserID(user.getUserID());
		this.profile.setRoleID(user.getRoleID());
		this.profile.setFirstName(user.getFirstName());
		this.profile.setLastName(user.getLastName());
		this.profile.seteMailAddress(email);
		this.profile.setPassword(pass);
		this.profile.setLatitude(user.getLatitude());
		this.profile.setLongitude(user.getLongitude());
		this.profile.setCardNumber(account.getAccountNumber());
		this.profile.setBalance(account.getBalance());

		return profile;
	}

	public Profile createAdminProfile() {
		User user = new User();

		System.out.println("Input personal information!");
		String input ="";		
		System.out.print("First Name: ");
		do {
			input = sc.nextLine();
		} while(input.isEmpty());
		user.setFirstName(input);
		input = "";
		
		System.out.print("Last Name: ");
		do {
			input = sc.nextLine();
		} while(input.isEmpty());
		user.setLastName(input);
		input = "";
		
		System.out.print("EMail Address: ");
		do {
			input = sc.nextLine();
		} while(input.isEmpty());
		user.seteMailAddress(input);
		input = "";
		
		System.out.print("Password: ");
		do {
			input = sc.nextLine();
		} while(input.isEmpty());
		user.setPassword(input);
		input = "";
		
		boolean quite = true;
		
		 do {
			System.out.print("Confirm Password: ");
			String str = "";
			do {
				str = sc.nextLine();
			} while(str.isEmpty());
			
			if(user.getPassword().equals(str)) {
				System.out.println("Password was set succsessfully!");
				quite = false;
			}
			else {
				System.out.println("Please enter same password!");

				System.out.print("Password: ");
				do {
					input = sc.nextLine();
				} while(input.isEmpty());
				user.setPassword(input);
			}
		} while(quite);
		
		userDaoImpl.addUser(user);

		AdminProfile adminprofile = new AdminProfile(user.getUserID(), user.getFirstName(), user.getLastName(), user.geteMailAddress(), user.getPassword());
		
		return adminprofile;
	}
}