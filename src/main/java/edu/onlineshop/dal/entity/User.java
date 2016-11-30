package edu.onlineshop.dal.entity;

public class User {

	private long userID;
	private long roleID;
	private String firstName;
	private String lastName;
	private double latitude;
	private double longitude;
	private String eMailAddress;
	private String password;
	private String lastLoginDate;

	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
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
	public long getRoleID() {
		return roleID;
	}
	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}	
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	@Override
	public String toString() {
		return "User [userID=" + userID + ", roleID=" + roleID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", eMailAddress=" + eMailAddress
				+ ", password=" + password + ", lastLoginDate=" + lastLoginDate + "]";
	}
}
