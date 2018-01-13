/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.model;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WiFiAdditionalTest {
    private static final String VENDOR_NAME = "VendorName";

    @Test
    public void testWiFiAdditionalWithWiFiConnection() throws Exception {
        // setup
        WiFiConnection wiFiConnection = new WiFiConnection("SSID", "BSSID", "192.168.1.10", 22);
        // execute
        WiFiAdditional fixture = new WiFiAdditional(VENDOR_NAME, wiFiConnection);
        // validate
        assertEquals(VENDOR_NAME, fixture.getVendorName());
        assertEquals(wiFiConnection, fixture.getWiFiConnection());
    }

    @Test
    public void testWiFiAdditional() throws Exception {
        // execute
        WiFiAdditional fixture = new WiFiAdditional(VENDOR_NAME, false);
        // validate
        assertEquals(VENDOR_NAME, fixture.getVendorName());
        assertFalse(fixture.isConfiguredNetwork());
    }

    @Test
    public void testWiFiAdditionalWithConfiguredNetwork() throws Exception {
        // execute
        WiFiAdditional fixture = new WiFiAdditional(VENDOR_NAME, true);
        // validate
        assertEquals(VENDOR_NAME, fixture.getVendorName());
        assertTrue(fixture.isConfiguredNetwork());
    }

    @Test
    public void testWiFiAdditionalEmpty() throws Exception {
        // validate
        assertEquals(StringUtils.EMPTY, WiFiAdditional.EMPTY.getVendorName());
        assertFalse(WiFiAdditional.EMPTY.isConfiguredNetwork());
    }

}