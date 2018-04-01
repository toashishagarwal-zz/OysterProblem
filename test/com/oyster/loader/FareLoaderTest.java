package com.oyster.loader;

import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class FareLoaderTest {

	@Test
	public void testFaresLoader() {
		Map<String, Double> fares = FareLoader.getFares();
		Assert.assertNotNull(fares);
		Assert.assertEquals(6, fares.size());
		
		Set<String> keys = fares.keySet();
		Assert.assertTrue(keys.contains("ONLY_ZONE_1"));
		Assert.assertTrue(keys.contains("ANY_ONE_ZONE_OUTSIDE_ZONE_1"));
		Assert.assertTrue(keys.contains("ANY_TWO_ZONES_INCLUDING_ZONE_1"));
		Assert.assertTrue(keys.contains("ANY_TWO_ZONES_EXCLUDING_ZONE_1"));
		Assert.assertTrue(keys.contains("ANY_THREE_ZONES"));
		Assert.assertTrue(keys.contains("BUS"));
	}
}
