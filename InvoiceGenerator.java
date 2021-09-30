package com.invoicegenerator;

import java.util.List;

/**
 * This class is used to generate invoices to cabs.
 * 
 * @author kshamavidyananda
 *
 */
public class InvoiceGenerator {
	
	
	private final int NORMAL_MINIMUM_FARE = 5;
	private final int PREMIUM_MINIMUM_FARE = 20;
	private final int CHARGE_PER_MINUTE_NORMAL = 1;
	private final int CHARGE_PER_MINUTE_PREMIUM = 2;
	private final int CHARGE_PER_KM_PREMIUM = 15;
	private final int CHARGE_PER_KM_NORMAL = 10;
	// Constructor
	public InvoiceGenerator() {
		
	}
	/**
	 * The method calculateFare calculates the total fare of the passenger.
	 * @param type
	 * @param distance distance is the distance travelled by the passenger
	 * @param time time is the time taken to reach the destination
	 * @return
	 */
	public double calculateFare(String type, double distance, double time) {
		double cost;
		if (type.equalsIgnoreCase("premium")) {
			cost = (distance * CHARGE_PER_KM_PREMIUM) + (CHARGE_PER_MINUTE_PREMIUM * time);
			return cost > PREMIUM_MINIMUM_FARE ? cost : PREMIUM_MINIMUM_FARE;
		} else {
			cost = (distance * CHARGE_PER_KM_NORMAL) + (CHARGE_PER_MINUTE_NORMAL * time);
			return cost > NORMAL_MINIMUM_FARE ? cost : NORMAL_MINIMUM_FARE;
		}

	}
	

	/**
	 * Fare for multiple rides
	 * @param rides
	 * @return
	 */
	
	
	public  double calculateTotalFare(List<MultipleRide> rides) 
	{
		double totalFare = 0.0;
		for (MultipleRide ride : rides) 
		{
			totalFare += calculateFare(ride.getRideType(),ride.getDistance(),ride.getTime());
		}
		return totalFare;
	}
	public InvoiceSummary getEnhancedInvoice(List<MultipleRide> rides) {
		double totalFare = calculateTotalFare(rides);
		InvoiceSummary invoice = new InvoiceSummary(rides.size(), totalFare);

		return invoice;

	}
}
