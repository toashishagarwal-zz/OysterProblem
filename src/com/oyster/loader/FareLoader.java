package com.oyster.loader;

import java.util.HashMap;
import java.util.Map;

public class FareLoader {
	public static Map<String, Double> getFares() {
		Map<String, Double> fares = new HashMap<String, Double>();
		fares.put(FareConstants.ONLY_ZONE_1, new Double(2.50d));
		fares.put(FareConstants.ANY_ONE_ZONE_OUTSIDE_ZONE_1, new Double(2.00d));
		fares.put(FareConstants.ANY_TWO_ZONES_INCLUDING_ZONE_1, new Double(3.00d));
		fares.put(FareConstants.ANY_TWO_ZONES_EXCLUDING_ZONE_1, new Double(2.25d));
		fares.put(FareConstants.ANY_THREE_ZONES, new Double(3.20d));
		fares.put(FareConstants.BUS, new Double(1.80d));
		return fares;
	}
}
