package com.invoicegeneratortest;

import org.junit.Assert;
import org.junit.Test;

import com.invoicegenerator.InvoiceGenerator;
import com.invoicegenerator.MultipleRide;

public class InvoiceGeneratorTest {

	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare() {
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		double distance = 2.0;
		double time = 5.0;
		double fare = invoiceGenerator.calculateTotalFare(distance, time);
		Assert.assertEquals(25.0, fare, 0.0);

	}

	@Test
	public void givenDistanceAndTime_ShouldReturnFalse() {
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		double distance = 2.0;
		double time = 5.0;
		double fare = invoiceGenerator.calculateTotalFare(distance, time);
		Assert.assertNotEquals(20.0, fare, 0.0);

	}
	@Test
	public void givenLessDistanceAndTime_ShouldReturnMinFare()
	{
		InvoiceGenerator invoiceGenerator =  new InvoiceGenerator();
		double distance = 0.1;
		double time = 1.0;
		double fare = invoiceGenerator.calculateTotalFare(distance, time);
		Assert.assertEquals(5.0, fare, 0.0);
	}
	@Test
	public void givenMultipleRides_ShouldReturnTotalFare()
	{
		InvoiceGenerator invoiceGenerator =  new InvoiceGenerator();
		MultipleRide[] rides = { new MultipleRide(2.0, 5.0),
				         new MultipleRide(0.1, 1.0)
				       };
		double fare = invoiceGenerator.calculateTotalFare(rides);
		Assert.assertEquals(30.0, fare, 0.0);
	}

}
