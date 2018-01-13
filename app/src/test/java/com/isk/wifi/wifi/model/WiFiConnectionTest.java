/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.model;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

public class WiFiConnectionTest {
    private static final String SSID = "SSID-123";
    private static final String BSSID = "BSSID-123";
    private static final String IP_ADDRESS = "21.205.91.7";
    private static final int LINK_SPEED = 21;

    private WiFiConnection fixture;

    @Before
    public void setUp() {
        fixture = new WiFiConnection(SSID, BSSID, IP_ADDRESS, LINK_SPEED);
    }

    @Test
    public void testWiFiConnectionEmpty() throws Exception {
        // validate
        assertEquals(StringUtils.EMPTY, WiFiConnection.EMPTY.getSSID());
        assertEquals(StringUtils.EMPTY, WiFiConnection.EMPTY.getBSSID());
        assertEquals(StringUtils.EMPTY, WiFiConnection.EMPTY.getIpAddress());
        assertEquals(WiFiConnection.LINK_SPEED_INVALID, WiFiConnection.EMPTY.getLinkSpeed());
        assertFalse(WiFiConnection.EMPTY.isConnected());
    }

    @Test
    public void testWiFiConnection() throws Exception {
        // validate
        assertEquals(SSID, fixture.getSSID());
        assertEquals(BSSID, fixture.getBSSID());
        assertEquals(IP_ADDRESS, fixture.getIpAddress());
        assertEquals(LINK_SPEED, fixture.getLinkSpeed());
        assertTrue(fixture.isConnected());
    }

    @Test
    public void testEquals() throws Exception {
        // setup
        WiFiConnection other = new WiFiConnection(SSID, BSSID, StringUtils.EMPTY, WiFiConnection.LINK_SPEED_INVALID);
        // execute & validate
        assertEquals(fixture, other);
        assertNotSame(fixture, other);
    }

    @Test
    public void testHashCode() throws Exception {
        // setup
        WiFiConnection other = new WiFiConnection(SSID, BSSID, StringUtils.EMPTY, WiFiConnection.LINK_SPEED_INVALID);
        // execute & validate
        assertEquals(fixture.hashCode(), other.hashCode());
    }

}