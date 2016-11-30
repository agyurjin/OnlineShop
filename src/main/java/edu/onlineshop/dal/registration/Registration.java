package edu.onlineshop.dal.registration;

public interface Registration {

	public Profile createUserProfile();
	public Profile createAdminProfile();
	public Profile logIn(String email, String pass);
}
