package com.oyster.processor;

import java.util.Map;
import java.util.Set;

import com.oyster.loader.FareConstants;
import com.oyster.loader.FareLoader;
import com.oyster.loader.ZoneLoader;
import com.oyster.model.Mode;
import com.oyster.model.Trip;

public class TripProcessor {
	static Map<String, Double> fares;
	static Map<String, Set<Integer>> stationZones;
	
	static {
		fares = FareLoader.getFares();
		stationZones = ZoneLoader.getZones();
	}

	public static Double calculateFare(Trip trip, String isEnter, String isExit) {
		boolean isEnterBoolean = (isEnter.equalsIgnoreCase("Y"))? true : false;
		boolean isExitBoolean = (isExit.equalsIgnoreCase("Y"))? true : false;
		
		if(trip.getMode() == Mode.BUS) 
			return fares.get(FareConstants.BUS);
		
		if((!isEnterBoolean && isExitBoolean) || (!isEnterBoolean && !isExitBoolean))	// N,Y OR N,N
			return 0d;
		
		if(isEnterBoolean && isExitBoolean){											// Y,Y
			Set<Integer> sourceZone = stationZones.get(trip.getSource());
			Set<Integer> destinationZone = stationZones.get(trip.getDestination());
			
			if(sourceZone != null && destinationZone != null) {
				if(sourceZone.size() == 1 && destinationZone.size()==1) {
					int sZone = getElement(sourceZone);
					int dZone = getElement(destinationZone);
					
					return calculateFareBetweenZones(sZone, dZone);
				} else if(sourceZone.size() > 1) {
					double min = Double.MAX_VALUE;
					int dZone = getElement(destinationZone);
					
					for(Integer sZone : sourceZone) 
						min = Math.min(min, calculateFareBetweenZones(sZone, dZone));
					
					return min;
				} else if(destinationZone.size() > 1) {
					double min = Double.MAX_VALUE;
					int sZone = getElement(sourceZone);
					
					for(Integer dZone : destinationZone) 
						min = Math.min(min, calculateFareBetweenZones(sZone, dZone));
					
					return min;
				}
			}
		}
		
		return trip.getCharge();
	}

	private static Double calculateFareBetweenZones(int sZone, int dZone) {
		if(isBothZoneOne(sZone, dZone)) 
			return fares.get(FareConstants.ONLY_ZONE_1);
		
		if(isAnyOneOutsideZoneOne(sZone, dZone))
			return fares.get(FareConstants.ANY_ONE_ZONE_OUTSIDE_ZONE_1);
		
		if(isAnyTwoZonesInclZoneOne(sZone, dZone))
			return fares.get(FareConstants.ANY_TWO_ZONES_INCLUDING_ZONE_1);
		
		if(isAnyTwoZonesExclZoneOne(sZone, dZone)) 
			return fares.get(FareConstants.ANY_TWO_ZONES_EXCLUDING_ZONE_1);
		return 0D;
	}

	private static boolean isAnyTwoZonesExclZoneOne(int sZone, int dZone) {
		return sZone != 1 && dZone != 1;
	}

	private static boolean isAnyTwoZonesInclZoneOne(int sZone, int dZone) {
		return (sZone == 1 && (dZone == 2 || dZone == 3)) 
				|| 
				((sZone == 2 || sZone == 3) && dZone == 1);
	}

	private static boolean isAnyOneOutsideZoneOne(int sZone, int dZone) {
		return (sZone == dZone) && sZone != 1;
	}

	private static boolean isBothZoneOne(int sZone, int dZone) {
		return sZone == 1 && dZone == 1;
	}
	
	private static int getElement(Set<Integer> s) {
		for (Integer i : s) 
			return i;
		return -1;
	}
}
