package com.invoicegeneratortest;

import org.junit.Assert;

import java.util.stream.Collectors;


import org.junit.Test;

import com.invoicegenerator.InvoiceGenerator;
import com.invoicegenerator.InvoiceSummary;
import com.invoicegenerator.MultipleRide;
import com.invoicegenerator.RideRepo;

public class InvoiceGeneratorTest {

	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare() {
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		double distance = 2.0;
		double time = 5.0;
		double fare = invoiceGenerator.calculateFare("Normal",distance, time);
		Assert.assertEquals(25.0, fare, 0.0);

	}

	@Test
	public void givenDistanceAndTime_ShouldReturnFalse() {
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		double distance = 2.0;
		double time = 5.0;
		double fare = invoiceGenerator.calculateFare("Normal",distance, time);
		Assert.assertNotEquals(20.0, fare, 0.0);

	}
	@Test
	public void givenLessDistanceAndTime_ShouldReturnMinFare()
	{
		InvoiceGenerator invoiceGenerator =  new InvoiceGenerator();
		double distance = 0.1;
		double time = 1.0;
		double fare = invoiceGenerator.calculateFare("Minimum",distance, time);
		Assert.assertEquals(5.0, fare, 0.0);
	}
	@Test
	public void givenMultipleRides_ShouldReturnInvoiceSummary()
	{
		InvoiceGenerator invoiceGenerator =  new InvoiceGenerator();
        RideRepo rideRepo = new RideRepo();
        rideRepo.addRide(new MultipleRide(10,8,1,"Normal"));
        rideRepo.addRide(new MultipleRide(1,3,2,"Normal"));
        rideRepo.addRide(new MultipleRide(10,3,1,"Normal"));
        double expectedTotalFare=224;
        double epsilon=1e-15;
    	Assert.assertEquals(expectedTotalFare, invoiceGenerator.calculateTotalFare(rideRepo.getRides()), epsilon);
	}
	@Test
	public void givenMultipleRide_matchingWithTotalNumberOfRide_returnsTrue() {

		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		RideRepo rideRepo = new RideRepo();
		rideRepo.addRide(new MultipleRide(10, 8, 1,"Normal"));
		rideRepo.addRide(new MultipleRide(1, 3, 2,"Normal"));
		rideRepo.addRide(new MultipleRide(10, 4, 1,"Normal"));
		InvoiceSummary invoice = invoiceGenerator.getEnhancedInvoice(rideRepo.getRides());
		int expectedAvg = 3;
        double epsilon = 1e-15;
		Assert.assertEquals(expectedAvg, invoice.getNumberOfRides());
		
	}
	@Test
	public void givenMultipleRideOfDifferentCustomer_matchingWithNUmberOfRidesOfCustomer1_returnsTrue() {
		InvoiceGenerator cabInvoiceGenerator = new InvoiceGenerator();
		RideRepo rideRepository = new RideRepo();
		rideRepository.addRide(new MultipleRide(10, 8, 1,"Normal"));
		rideRepository.addRide(new MultipleRide(1, 3, 2,"Normal"));
		rideRepository.addRide(new MultipleRide(10, 4, 1,"Normal"));
		InvoiceSummary invoice = cabInvoiceGenerator.getEnhancedInvoice(
				rideRepository.getRides().stream().filter(ride -> ride.getUserId() == 1).collect(Collectors.toList()));

		int expectedTotalRideofUser1 = 2;
		double epsilon = 1e-15;
		Assert.assertEquals(expectedTotalRideofUser1, invoice.getNumberOfRides(), epsilon);

	}

	/*
	 * This test passes when user id matches and on Correct matching of TotalFare
	 */
	@Test
	public void givenMultipleRideOfDifferentCustomer_matchingWithTotalFareOfCustomer1_returnsTrue() {
		InvoiceGenerator cabInvoiceGenerator = new InvoiceGenerator();
		RideRepo rideRepository = new RideRepo();
		rideRepository.addRide(new MultipleRide(10, 8, 1,"Normal"));
		rideRepository.addRide(new MultipleRide(1, 3, 2,"Normal"));
		rideRepository.addRide(new MultipleRide(10, 4, 1,"Normal"));

		InvoiceSummary invoice = cabInvoiceGenerator.getEnhancedInvoice(
				rideRepository.getRides().stream().filter(ride -> ride.getUserId() == 1).collect(Collectors.toList()));

		double expectedTotalFareofUser1 = 212;
		double epsilon = 1e-15;
		Assert.assertEquals(expectedTotalFareofUser1, invoice.getTotalFare(), epsilon);

	}

	/*
	 * This test passes when user id matches and on Correct matching of average fare
	 */
	@Test
	public void givenMultipleRideOfDifferentCustomer_matchingWithAverageFareOfCustomer1_returnsTrue() {
		InvoiceGenerator cabInvoiceGenerator = new InvoiceGenerator();
		RideRepo rideRepository = new RideRepo();
		rideRepository.addRide(new MultipleRide(10, 8, 1,"Normal"));
		rideRepository.addRide(new MultipleRide(1, 3, 2,"Normal"));
		rideRepository.addRide(new MultipleRide(10, 4, 1,"Normal"));
		InvoiceSummary invoice = cabInvoiceGenerator.getEnhancedInvoice(
				rideRepository.getRides().stream().filter(ride -> ride.getUserId() == 1).collect(Collectors.toList()));

		double expectedAvgFareofUser1 = 106;
		double epsilon = 1e-15;
		Assert.assertEquals(expectedAvgFareofUser1, invoice.getAverageFare(), epsilon);

	}
	/**
	 * Test for the total fare of customer over different ride type
	 */
	@Test
	public void givenMultipleRideOfSingleCustomerOfDiffereentType_matchingWithTotalFareOfCustomer_returnsTrue() {
	InvoiceGenerator cabInvoiceGenerator = new InvoiceGenerator();
		RideRepo rideRepository = new RideRepo();
		rideRepository.addRide(new MultipleRide(10, 8, 1,"Premium"));
		rideRepository.addRide(new MultipleRide(1, 3, 2,"Normal"));
		rideRepository.addRide(new MultipleRide(10, 4, 1,"Normal"));

		InvoiceSummary invoice = cabInvoiceGenerator.getEnhancedInvoice(
				rideRepository.getRides().stream().filter(ride -> ride.getUserId() == 1).collect(Collectors.toList()));

		double expectedTotalFareofUser1 = 270;
		double epsilon = 1e-15;
		Assert.assertEquals(expectedTotalFareofUser1, invoice.getTotalFare(), epsilon);

	}

	/**
	 * Test for the average fare of customer over different ride type
	 */
	@Test
	public void givenMultipleRideOfSingleCustomerOfDiffereentType_matchingWithAverageFareOfCustomer_returnsTrue() {
		InvoiceGenerator cabInvoiceGenerator = new InvoiceGenerator();
		RideRepo rideRepository = new RideRepo();

		rideRepository.addRide(new MultipleRide(10, 8, 1,"Premium"));
		rideRepository.addRide(new MultipleRide(1, 3, 2,"Normal"));
		rideRepository.addRide(new MultipleRide(10, 4, 1,"Normal"));
		InvoiceSummary invoice = cabInvoiceGenerator.getEnhancedInvoice(
				rideRepository.getRides().stream().filter(ride -> ride.getUserId() == 1).collect(Collectors.toList()));

		double expectedTotalFareofUser1 = 135;
		double epsilon = 1e-15;
		Assert.assertEquals(expectedTotalFareofUser1, invoice.getAverageFare(), epsilon);

	}

}

