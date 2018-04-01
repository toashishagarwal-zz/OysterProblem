package com.oyster.loader;

import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class ZoneLoaderTest {

	@Test
	public void testZoneLoader() {
		Map<String, Set<Integer>> stationZones = ZoneLoader.getZones();
		Assert.assertNotNull(stationZones);
		Assert.assertEquals(4, stationZones.size());
		
		Set<String> keys = stationZones.keySet();
		Assert.assertTrue(keys.contains("Holborn"));
		Assert.assertTrue(keys.contains("Earl's Court"));
		Assert.assertTrue(keys.contains("Wimbledon"));
		Assert.assertTrue(keys.contains("Hammersmith"));
	}
}
