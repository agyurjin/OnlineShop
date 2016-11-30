package edu.onlineshop.dal.registration;

public class AdminProfile extends Profile {

	public AdminProfile(long userid, String firstname, String lastname, String email, String pass) {
		super(userid, 1, firstname, lastname, email, pass, 0.0, 0.0, 0, 0.0);
	}
}
