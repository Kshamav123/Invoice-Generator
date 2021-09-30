package com.invoicegenerator;

public class MultipleRide {
	int userId;
	public double distance;
	public double time;
	String rideType;
/**
 * Constructor for multiple rides
 * @param distance is the distance travelled by the passenger
 * @param time time is the time taken to reach the destination
 */
	public MultipleRide(int userId, double distance, double time, String rideType) {
		this.distance = distance;
		this.time = time;
		this.userId = userId;
		this.rideType = rideType;

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public String getRideType() {
		return rideType;
	}

	public void setRideType(String rideType) {
		this.rideType = rideType;
	}

}
