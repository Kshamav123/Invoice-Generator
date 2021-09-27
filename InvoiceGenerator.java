package com.invoicegenerator;

/**
 * This class is used to generate invoices to cabs.
 * 
 * @author kshamavidyananda
 *
 */
public class InvoiceGenerator {
	// Constructor
	public InvoiceGenerator() {

	}

	/**
	 * The method calculateTotalFare calculates the total fare of the passenger.
	 * 
	 * @param distance is the distance travelled by the passenger
	 * @param time is the time taken to reach the destination
	 * @return total Fare calculated.
	 */
	public double calculateTotalFare(double distance, double time) {
		double totalFare;
		totalFare = (10 * distance) + (time * 1);
		if(totalFare <= 5)
			totalFare = 5.0;
		return totalFare;
	}
	
	public InvoiceSummary calculateTotalFare(MultipleRide[] rides) 
	{
		double totalFare = 0.0;
		for (MultipleRide ride : rides) 
		{
			totalFare += this.calculateTotalFare(ride.distance, ride.time);
		}
		return new InvoiceSummary(rides.length, totalFare);
	}
	
}
