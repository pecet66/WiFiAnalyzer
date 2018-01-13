/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.model;

import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.band.WiFiWidth;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

public class WiFiSignalTest {
    private static final int PRIMARY_FREQUENCY = 2432;
    private static final int PRIMARY_CHANNEL = 5;
    private static final int CENTER_FREQUENCY = 2437;
    private static final int CENTER_CHANNEL = 6;
    private static final int LEVEL = -65;

    private WiFiSignal fixture;

    @Before
    public void setUp() {
        fixture = new WiFiSignal(PRIMARY_FREQUENCY, CENTER_FREQUENCY, WiFiWidth.MHZ_40, LEVEL);
    }

    @Test
    public void testWiFiFrequency() throws Exception {
        // validate
        assertEquals(PRIMARY_FREQUENCY, fixture.getPrimaryFrequency());
        assertEquals(CENTER_FREQUENCY, fixture.getCenterFrequency());
        assertEquals(LEVEL, fixture.getLevel());
        assertEquals(WiFiBand.GHZ2, fixture.getWiFiBand());
        assertEquals(WiFiWidth.MHZ_40, fixture.getWiFiWidth());
    }

    @Test
    public void testWiFiFrequencyWithFrequencyAndWiFiWidth() throws Exception {
        // execute
        fixture = new WiFiSignal(PRIMARY_FREQUENCY, CENTER_FREQUENCY, WiFiWidth.MHZ_80, LEVEL);
        // validate
        assertEquals(PRIMARY_FREQUENCY, fixture.getPrimaryFrequency());
        assertEquals(PRIMARY_CHANNEL, fixture.getPrimaryWiFiChannel().getChannel());
        assertEquals(CENTER_FREQUENCY, fixture.getCenterFrequency());
        assertEquals(CENTER_CHANNEL, fixture.getCenterWiFiChannel().getChannel());
        assertEquals(LEVEL, fixture.getLevel());
        assertEquals(WiFiBand.GHZ2, fixture.getWiFiBand());
        assertEquals(WiFiWidth.MHZ_80, fixture.getWiFiWidth());
    }

    @Test
    public void testGetCenterFrequency() throws Exception {
        assertEquals(CENTER_FREQUENCY, fixture.getCenterFrequency());
        assertEquals(CENTER_FREQUENCY - WiFiWidth.MHZ_40.getFrequencyWidthHalf(), fixture.getFrequencyStart());
        assertEquals(CENTER_FREQUENCY + WiFiWidth.MHZ_40.getFrequencyWidthHalf(), fixture.getFrequencyEnd());
    }

    @Test
    public void testGetInRange() throws Exception {
        assertTrue(fixture.isInRange(CENTER_FREQUENCY));
        assertTrue(fixture.isInRange(CENTER_FREQUENCY - WiFiWidth.MHZ_40.getFrequencyWidthHalf()));
        assertTrue(fixture.isInRange(CENTER_FREQUENCY + WiFiWidth.MHZ_40.getFrequencyWidthHalf()));

        assertFalse(fixture.isInRange(CENTER_FREQUENCY - WiFiWidth.MHZ_40.getFrequencyWidthHalf() - 1));
        assertFalse(fixture.isInRange(CENTER_FREQUENCY + WiFiWidth.MHZ_40.getFrequencyWidthHalf() + 1));
    }

    @Test
    public void testGetPrimaryWiFiChannel() throws Exception {
        assertEquals(PRIMARY_CHANNEL, fixture.getPrimaryWiFiChannel().getChannel());
    }

    @Test
    public void testGetCenterWiFiChannel() throws Exception {
        assertEquals(CENTER_CHANNEL, fixture.getCenterWiFiChannel().getChannel());
    }

    @Test
    public void testGetStrength() throws Exception {
        assertEquals(Strength.THREE, fixture.getStrength());
    }

    @Test
    public void testGetDistance() throws Exception {
        assertEquals(WiFiUtils.calculateDistance(PRIMARY_FREQUENCY, LEVEL), fixture.getDistance(), 0.0);
    }

    @Test
    public void testEquals() throws Exception {
        // setup
        WiFiSignal other = new WiFiSignal(PRIMARY_FREQUENCY, CENTER_FREQUENCY, WiFiWidth.MHZ_40, LEVEL);
        // execute & validate
        assertEquals(fixture, other);
        assertNotSame(fixture, other);
    }

    @Test
    public void testHashCode() throws Exception {
        // setup
        WiFiSignal other = new WiFiSignal(PRIMARY_FREQUENCY, CENTER_FREQUENCY, WiFiWidth.MHZ_40, LEVEL);
        // execute & validate
        assertEquals(fixture.hashCode(), other.hashCode());
    }

    @Test
    public void testGetChannelDisplayWhenPrimaryAndCenterSame() throws Exception {
        // setup
        fixture = new WiFiSignal(PRIMARY_FREQUENCY, PRIMARY_FREQUENCY, WiFiWidth.MHZ_40, LEVEL);
        // execute & validate
        assertEquals("5", fixture.getChannelDisplay());
    }

    @Test
    public void testGetChannelDisplayWhenPrimaryAndCenterDifferent() throws Exception {
        // setup
        fixture = new WiFiSignal(PRIMARY_FREQUENCY, CENTER_FREQUENCY, WiFiWidth.MHZ_40, LEVEL);
        // execute & validate
        assertEquals("5(6)", fixture.getChannelDisplay());
    }

}