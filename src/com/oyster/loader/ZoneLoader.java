package com.oyster.loader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ZoneLoader {
	public static Map<String, Set<Integer>> getZones() {
		Map<String, Set<Integer>> stationZones = new HashMap<String, Set<Integer>>();
		
		populateZones(stationZones, "Holborn", 1);
		populateZones(stationZones, "Wimbledon", 3);
		populateZones(stationZones, "Earl's Court", 1,2);
		populateZones(stationZones, "Hammersmith", 2);
		
		return stationZones;
	}

	private static void populateZones(Map<String, Set<Integer>> stationZones, String name, Integer... i) {
		Set<Integer> zone = new HashSet<>();
		for(Integer x: i)
			zone.add(x);
		stationZones.put(name, zone);
	}
}
