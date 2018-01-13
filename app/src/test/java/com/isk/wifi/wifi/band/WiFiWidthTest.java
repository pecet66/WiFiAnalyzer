/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.band;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WiFiWidthTest {

    @Test
    public void testWiFiWidth() throws Exception {
        assertEquals(5, WiFiWidth.values().length);
    }

    @Test
    public void testGetFrequencyWidth() throws Exception {
        assertEquals(20, WiFiWidth.MHZ_20.getFrequencyWidth());
        assertEquals(40, WiFiWidth.MHZ_40.getFrequencyWidth());
        assertEquals(80, WiFiWidth.MHZ_80.getFrequencyWidth());
        assertEquals(160, WiFiWidth.MHZ_160.getFrequencyWidth());
        assertEquals(80, WiFiWidth.MHZ_80_PLUS.getFrequencyWidth());
    }

    @Test
    public void testGetFrequencyHalfWidth() throws Exception {
        assertEquals(10, WiFiWidth.MHZ_20.getFrequencyWidthHalf());
        assertEquals(20, WiFiWidth.MHZ_40.getFrequencyWidthHalf());
        assertEquals(40, WiFiWidth.MHZ_80.getFrequencyWidthHalf());
        assertEquals(80, WiFiWidth.MHZ_160.getFrequencyWidthHalf());
        assertEquals(40, WiFiWidth.MHZ_80_PLUS.getFrequencyWidthHalf());
    }

}