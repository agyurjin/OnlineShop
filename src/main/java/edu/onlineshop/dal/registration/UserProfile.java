package edu.onlineshop.dal.registration;

public class UserProfile extends Profile{

	public UserProfile(long userid, String firstname, String lastname, String email, String pass, double latitude, double longitude, long cardnumber, double balance) {
		super(userid, 2, firstname, lastname, email, pass, latitude, longitude, cardnumber, balance);
	}

}
