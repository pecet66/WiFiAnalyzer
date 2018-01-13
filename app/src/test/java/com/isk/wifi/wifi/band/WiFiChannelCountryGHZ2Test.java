/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.band;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WiFiChannelCountryGHZ2Test {

    private final static SortedSet<Integer> CHANNELS_SET1 = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
    private final static SortedSet<Integer> CHANNELS_SET2 = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));

    private WiFiChannelCountryGHZ2 fixture;

    @Before
    public void setUp() {
        fixture = new WiFiChannelCountryGHZ2();
    }

    @Test
    public void testChannelsForUSAndSimilar() throws Exception {
        List<String> countries = Arrays.asList("AS", "AU", "CA", "FM", "GU", "MP", "PA", "PR", "UM", "US", "VI");
        IterableUtils.forEach(countries, new ChannelUSClosure());
    }

    @Test
    public void testChannelsForWorld() throws Exception {
        List<String> countries = Arrays.asList(null, "GB", "XYZ", "MX", "AE");
        IterableUtils.forEach(countries, new ChannelWorldClosure());
    }

    private void validateChannels(SortedSet<Integer> expected, SortedSet<Integer> actual) {
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
    }

    private class ChannelUSClosure implements Closure<String> {
        @Override
        public void execute(String country) {
            validateChannels(CHANNELS_SET1, fixture.findChannels(country));
        }
    }

    private class ChannelWorldClosure implements Closure<String> {
        @Override
        public void execute(String country) {
            validateChannels(CHANNELS_SET2, fixture.findChannels(country));
        }
    }
}