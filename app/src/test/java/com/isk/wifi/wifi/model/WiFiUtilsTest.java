/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.model;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;

public class WiFiUtilsTest {

    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Test
    public void testCalculateDistance() throws Exception {
        validate(2437, -36, "0.62");
        validate(2437, -42, "1.23");
        validate(2432, -88, "246.34");
        validate(2412, -91, "350.85");
    }

    private void validate(int frequency, int level, String expected) {
        assertEquals(expected, decimalFormat.format(WiFiUtils.calculateDistance(frequency, level)));
    }

    @Test
    public void testCalculateSignalLevel() throws Exception {
        assertEquals(0, WiFiUtils.calculateSignalLevel(-110, 5));
        assertEquals(0, WiFiUtils.calculateSignalLevel(-89, 5));

        assertEquals(1, WiFiUtils.calculateSignalLevel(-88, 5));
        assertEquals(1, WiFiUtils.calculateSignalLevel(-78, 5));

        assertEquals(2, WiFiUtils.calculateSignalLevel(-77, 5));
        assertEquals(2, WiFiUtils.calculateSignalLevel(-67, 5));

        assertEquals(3, WiFiUtils.calculateSignalLevel(-66, 5));
        assertEquals(3, WiFiUtils.calculateSignalLevel(-56, 5));

        assertEquals(4, WiFiUtils.calculateSignalLevel(-55, 5));
        assertEquals(4, WiFiUtils.calculateSignalLevel(0, 5));
    }

    @Test
    public void testConvertIpAddress() throws Exception {
        assertEquals("21.205.91.7", WiFiUtils.convertIpAddress(123456789));
        assertEquals("1.0.0.0", WiFiUtils.convertIpAddress(1));
        assertEquals(StringUtils.EMPTY, WiFiUtils.convertIpAddress(0));
        assertEquals(StringUtils.EMPTY, WiFiUtils.convertIpAddress(-1));
    }

    @Test
    public void testConvertSSID() throws Exception {
        assertEquals("SSID", WiFiUtils.convertSSID("\"SSID\""));
        assertEquals("SSID", WiFiUtils.convertSSID("SSID"));
    }

}