package edu.onlineshop.dal.registration;

public class Profile {

	private long userID;
	private long roleID;
	
//	Personal Info
	private String firstName;
	private String lastName;
	private String eMailAddress;
	private String password;

//	Address
	private double latitude;
	private double longitude;

//	Credit Card
	private long cardNumber;
	private double balance;

	public Profile(){
		
	}
	public Profile(long userid, long roleid, String firstname, String lastname, String email, String pass, double latitude, double longitude, long cardnumber, double balance) {
		this.userID = userid;
		this.roleID = roleid;
		this.firstName = firstname;
		this.lastName = lastname;
		this.eMailAddress = email;
		this.latitude = latitude;
		this.longitude = longitude;
		this.cardNumber = cardnumber;
		this.balance = balance;
	}

	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public long getRoleID() {
		return roleID;
	}
	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String geteMailAddress() {
		return eMailAddress;
	}
	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Profile [firstName=" + firstName + ", lastName=" + lastName + ", eMailAddress=" + eMailAddress
				+ ", password=" + password + ", latitude=" + latitude + ", longitude=" + longitude + ", cardNumber="
				+ cardNumber + ", balance=" + balance + "]";
	}

}
