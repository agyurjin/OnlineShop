package edu.onlineshop.session;

import java.util.Scanner;

import edu.onlineshop.dal.customer.Customer;
import edu.onlineshop.dal.registration.Logger;
import edu.onlineshop.dal.registration.Profile;

public class Session implements SessionManager {

	Scanner sc = new Scanner(System.in);
	Customer customer = new Customer();
	boolean quite = true;

	public boolean exit() {
		return this.quite;
	}

	@Override
	public void startUserSession(Profile profile) {
		System.out.println("\nHello,"+ profile.getFirstName() + " " + profile.getLastName()+ "!");
		do {
			System.out.println("**********************************");
			System.out.println("Please select one of the options!");
			System.out.println("1. Purchase product");
			System.out.println("2. List of products");
			System.out.println("3. Order history");
			System.out.println("4. Order Status");
			System.out.println("5. Delete profile");
			System.out.println("6. Log out");
			System.out.println("**********************************");
			int loop = sc.nextInt();

			switch (loop) {
				case 1:
					System.out.println("Please type product id which you want to purchuse!");
					long productID = sc.nextLong();
					int num;
					do {
						System.out.println("How many do you want to buy!");
						num = sc.nextInt();
						if(num <1) {
							System.out.println("You need to order at least one!");
						}
					}
					while(num<0);
					
					customer.DoPurchase(profile.getUserID(), productID, profile.getBalance(), num);
					break;
				case 2:
					customer.ListProducts();
					break;
				case 3:
					customer.OrderHistory(profile.getUserID());
					break;
				case 4:
					System.out.println("Please type order id.");
					customer.OrderStatus(sc.nextLong());
					break;
				case 5:
					customer.DeleteProfile(profile.getUserID());
					break;
				case 6:
					quite = false;
					break;
				default:
					System.out.println("Unknown option. Try again!");
			}
		} while(quite);
	}

	@Override
	public void startAdminSession(Profile profile) {
		boolean quite = true;
		System.out.println("\nHello,"+ profile.getFirstName() + " " + profile.getLastName()+ "!");
		do {
			System.out.println("**********************************");
			System.out.println("Please select one of the options!");
			System.out.println("1. Start delivering");
			System.out.println("2. Orders to deliver");
			System.out.println("3. Log out");
			System.out.println("**********************************");
			int loop = sc.nextInt();

			switch (loop) {
				case 1:
					customer.OrderList();
					System.out.println("List of orders to deliver!");
					customer.Deliver();
					break;
				case 2:
					customer.OrderList();
					break;
				case 3:
					quite = false;
					break;
				default:
					System.out.println("Unknown option. Try again!");
			}
		} while(quite);
		
	}

	@Override
	public Profile start() {
		Logger logger = new Logger();
		Profile profile = new Profile();
		
		boolean quite1 = true;
		boolean quite2 = true;
		String str1, str2;
		String pass, login;
		
		str1 = sc.nextLine();
		do { 
			if(str1.equals("yes")) {
				quite1 = false;
				System.out.print("Email address: ");
				login = sc.nextLine();
				System.out.println("Password: ");
				pass = sc.nextLine();
				profile = logger.logIn(login, pass);
			}
			else if(str1.equals("no")) {
				quite1 = false;
				System.out.println("Do you want to create a profile! (yes/no)");
				str2 = sc.nextLine();
				do { 
					if(str2.equals("yes")) {
						quite2 = false;
						profile = logger.createUserProfile();
					}
					else if(str2.equals("no")) {
						quite2 = false;
						System.out.println("GoodBye!");
					}
					else {
						System.out.println("Please input 'yes' or 'no'");
						str2 = sc.nextLine();
					}
				} while(quite2);
			}
			else {
				System.out.println("Please input 'yes' or 'no'");
				str1 = sc.nextLine();
			}

		} while(quite1);
		
		return profile;
	}


}
