package com.invoicegenerator;

import java.util.ArrayList;
import java.util.List;

public class RideRepo {
	List<MultipleRide> rides;

	public RideRepo() {
		this.rides = new ArrayList<MultipleRide>();
	}

	public List<MultipleRide> getRides() {
		return rides;
	}

	public void addRide(MultipleRide ride) {
		rides.add(ride);
	}


}
