package edu.onlineshop.optimizer;

public class Points {

	private long id;
	private double latitude;
	private double longitude;

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Points [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
}
