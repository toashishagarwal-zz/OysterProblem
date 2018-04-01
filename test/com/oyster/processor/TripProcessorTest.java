package com.oyster.processor;

import org.junit.Assert;
import org.junit.Test;

import com.oyster.model.Mode;
import com.oyster.model.Trip;

public class TripProcessorTest {
	
	@Test
	public void testLoaders() {
		Assert.assertNotNull(TripProcessor.fares);
		Assert.assertNotNull(TripProcessor.stationZones);
	}

	@Test
	public void shouldReturnOnePointEight_BusTrip() {
		Trip trip = new Trip(Mode.BUS, "source", "dest", Double.valueOf(3.20d));
		Double expected = 1.80d;
		
		Double actual = TripProcessor.calculateFare(trip, "Y", "Y");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnZero_InvalidTrip() {
		Trip trip = new Trip(Mode.TUBE, "source", "dest", Double.valueOf(3.20d));
		Double expected = 0d;
		
		Double actual = TripProcessor.calculateFare(trip, "N", "Y");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnZero_InvalidTrip2() {
		Trip trip = new Trip(Mode.TUBE, "source", "dest", Double.valueOf(3.20d));
		Double expected = 0d;
		
		Double actual = TripProcessor.calculateFare(trip, "N", "N");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnTwoPointFive_AnywhereZone1Trip() {
		Trip trip = new Trip(Mode.TUBE, "Holborn", "Earl's Court", Double.valueOf(3.20d));
		Double expected = 2.50d;
		
		Double actual = TripProcessor.calculateFare(trip, "Y", "Y");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnTwo_AnyOneZoneOutsideZone1Trip() {
		Trip trip = new Trip(Mode.TUBE, "Hammersmith", "Earl's Court", Double.valueOf(3.20d));
		Double expected = 2.00d;
		
		Double actual = TripProcessor.calculateFare(trip, "Y", "Y");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnThree_AnyTwoZonesIncludingZone1Trip() {
		Trip trip = new Trip(Mode.TUBE, "Holborn", "Wimbledon", Double.valueOf(3.20d));
		Double expected = 3.00d;
		
		Double actual = TripProcessor.calculateFare(trip, "Y", "Y");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnTwoPointTwoFive_AnyTwoZonesExcludingZone1Trip() {
		Trip trip = new Trip(Mode.TUBE, "Hammersmith", "Wimbledon", Double.valueOf(3.20d));
		Double expected = 2.25d;
		
		Double actual = TripProcessor.calculateFare(trip, "Y", "Y");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnMaxFare() {
		Trip trip = new Trip(Mode.TUBE, "Hammersmith", "Wimbledon", Double.valueOf(3.20d));
		Double expected = 3.20d;
		
		Double actual = TripProcessor.calculateFare(trip, "Y", "N");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnMinFare_MultipleRoutes() {
		Trip trip = new Trip(Mode.TUBE, "Earl's Court", "Hammersmith", Double.valueOf(3.20d));
		Double expected = 2.00d;
		
		Double actual = TripProcessor.calculateFare(trip, "Y", "Y");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnMaxFare_StationNotFound() {
		Trip trip = new Trip(Mode.TUBE, "Earl's Court", "Chelsea", Double.valueOf(3.20d));
		Double expected = 3.20d;
		
		Double actual = TripProcessor.calculateFare(trip, "Y", "Y");
		Assert.assertEquals(expected, actual);
	}
}
