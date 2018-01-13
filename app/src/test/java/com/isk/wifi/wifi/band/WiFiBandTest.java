/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.band;

import com.isk.wifi.R;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WiFiBandTest {
    @Test
    public void testWiFiBand() throws Exception {
        assertEquals(2, WiFiBand.values().length);
    }

    @Test
    public void testGetTextResource() throws Exception {
        assertEquals(R.string.wifi_band_2ghz, WiFiBand.GHZ2.getTextResource());
        assertEquals(R.string.wifi_band_5ghz, WiFiBand.GHZ5.getTextResource());
    }

    @Test
    public void testToggle() throws Exception {
        assertEquals(WiFiBand.GHZ5, WiFiBand.GHZ2.toggle());
        assertEquals(WiFiBand.GHZ2, WiFiBand.GHZ5.toggle());
    }

    @Test
    public void testIsGHZ_5() throws Exception {
        assertFalse(WiFiBand.GHZ2.isGHZ5());
        assertTrue(WiFiBand.GHZ5.isGHZ5());
    }

}