/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.band;

import org.junit.Test;

import java.util.Locale;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class WiFiChannelCountryTest {

    @Test
    public void testIsChannelAvailableWithTrue() throws Exception {
        assertTrue(WiFiChannelCountry.get(Locale.US.getCountry()).isChannelAvailableGHZ2(1));
        assertTrue(WiFiChannelCountry.get(Locale.US.getCountry()).isChannelAvailableGHZ2(11));

        assertTrue(WiFiChannelCountry.get(Locale.US.getCountry()).isChannelAvailableGHZ5(36));
        assertTrue(WiFiChannelCountry.get(Locale.US.getCountry()).isChannelAvailableGHZ5(165));

        assertTrue(WiFiChannelCountry.get(Locale.UK.getCountry()).isChannelAvailableGHZ2(1));
        assertTrue(WiFiChannelCountry.get(Locale.UK.getCountry()).isChannelAvailableGHZ2(13));

        assertTrue(WiFiChannelCountry.get(Locale.UK.getCountry()).isChannelAvailableGHZ5(36));
        assertTrue(WiFiChannelCountry.get(Locale.UK.getCountry()).isChannelAvailableGHZ5(140));
    }

    @Test
    public void testIsChannelAvailableWithGHZ2() throws Exception {
        assertFalse(WiFiChannelCountry.get(Locale.US.getCountry()).isChannelAvailableGHZ2(0));
        assertFalse(WiFiChannelCountry.get(Locale.US.getCountry()).isChannelAvailableGHZ2(12));

        assertFalse(WiFiChannelCountry.get(Locale.UK.getCountry()).isChannelAvailableGHZ2(0));
        assertFalse(WiFiChannelCountry.get(Locale.UK.getCountry()).isChannelAvailableGHZ2(14));
    }

    @Test
    public void testIsChannelAvailableWithGHZ5() throws Exception {
        assertTrue(WiFiChannelCountry.get(Locale.US.getCountry()).isChannelAvailableGHZ5(36));
        assertTrue(WiFiChannelCountry.get(Locale.US.getCountry()).isChannelAvailableGHZ5(165));

        assertTrue(WiFiChannelCountry.get(Locale.UK.getCountry()).isChannelAvailableGHZ5(36));
        assertTrue(WiFiChannelCountry.get(Locale.UK.getCountry()).isChannelAvailableGHZ5(140));

        assertTrue(WiFiChannelCountry.get("AE").isChannelAvailableGHZ5(36));
        assertTrue(WiFiChannelCountry.get("AE").isChannelAvailableGHZ5(64));
    }

    @Test
    public void testGetCorrectlyPopulatesGHZ() throws Exception {
        // setup
        String expectedCountryCode = Locale.US.getCountry();
        Set<Integer> expectedGHZ2 = new WiFiChannelCountryGHZ2().findChannels(expectedCountryCode);
        Set<Integer> expectedGHZ5 = new WiFiChannelCountryGHZ5().findChannels(expectedCountryCode);
        // execute
        WiFiChannelCountry actual = WiFiChannelCountry.get(expectedCountryCode);
        // validate
        assertEquals(expectedCountryCode, actual.getCountryCode());
        assertArrayEquals(expectedGHZ2.toArray(), actual.getChannelsGHZ2().toArray());
        assertArrayEquals(expectedGHZ5.toArray(), actual.getChannelsGHZ5().toArray());
    }

    @Test
    public void testGetCorrectlyPopulatesCountryCodeAndName() throws Exception {
        // setup
        Locale expected = Locale.SIMPLIFIED_CHINESE;
        String expectedCountryCode = expected.getCountry();
        // execute
        WiFiChannelCountry actual = WiFiChannelCountry.get(expectedCountryCode);
        // validate
        assertEquals(expectedCountryCode, actual.getCountryCode());
        assertNotEquals(expected.getDisplayCountry(), actual.getCountryName(expected));
        assertEquals(expected.getDisplayCountry(expected), actual.getCountryName(expected));
    }

}