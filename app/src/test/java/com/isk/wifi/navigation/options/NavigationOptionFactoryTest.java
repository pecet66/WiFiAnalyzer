/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NavigationOptionFactoryTest {

    @Test
    public void testRating() throws Exception {
        List<NavigationOption> options = NavigationOptionFactory.RATING;
        assertEquals(4, options.size());

        assertTrue(options.contains(NavigationOptionFactory.WIFI_SWITCH_ON));
        assertTrue(options.contains(NavigationOptionFactory.SCANNER_SWITCH_ON));
        assertTrue(options.contains(NavigationOptionFactory.FILTER_OFF));
        assertTrue(options.contains(NavigationOptionFactory.NEXT_PREV_ON));

        assertFalse(options.contains(NavigationOptionFactory.WIFI_SWITCH_OFF));
        assertFalse(options.contains(NavigationOptionFactory.SCANNER_SWITCH_OFF));
        assertFalse(options.contains(NavigationOptionFactory.FILTER_ON));
        assertFalse(options.contains(NavigationOptionFactory.NEXT_PREV_OFF));
    }

    @Test
    public void testOther() throws Exception {
        List<NavigationOption> options = NavigationOptionFactory.OTHER;
        assertEquals(4, options.size());

        assertTrue(options.contains(NavigationOptionFactory.WIFI_SWITCH_ON));
        assertTrue(options.contains(NavigationOptionFactory.SCANNER_SWITCH_ON));
        assertTrue(options.contains(NavigationOptionFactory.FILTER_ON));
        assertTrue(options.contains(NavigationOptionFactory.NEXT_PREV_ON));

        assertFalse(options.contains(NavigationOptionFactory.WIFI_SWITCH_OFF));
        assertFalse(options.contains(NavigationOptionFactory.SCANNER_SWITCH_OFF));
        assertFalse(options.contains(NavigationOptionFactory.FILTER_OFF));
        assertFalse(options.contains(NavigationOptionFactory.NEXT_PREV_OFF));
    }

    @Test
    public void testOff() throws Exception {
        List<NavigationOption> options = NavigationOptionFactory.OFF;
        assertEquals(4, options.size());

        assertTrue(options.contains(NavigationOptionFactory.WIFI_SWITCH_OFF));
        assertTrue(options.contains(NavigationOptionFactory.SCANNER_SWITCH_OFF));
        assertTrue(options.contains(NavigationOptionFactory.FILTER_OFF));
        assertTrue(options.contains(NavigationOptionFactory.NEXT_PREV_OFF));

        assertFalse(options.contains(NavigationOptionFactory.WIFI_SWITCH_ON));
        assertFalse(options.contains(NavigationOptionFactory.SCANNER_SWITCH_ON));
        assertFalse(options.contains(NavigationOptionFactory.FILTER_ON));
        assertFalse(options.contains(NavigationOptionFactory.NEXT_PREV_ON));
    }

    @Test
    public void testAccessPoints() throws Exception {
        List<NavigationOption> options = NavigationOptionFactory.AP;
        assertEquals(4, options.size());

        assertTrue(options.contains(NavigationOptionFactory.WIFI_SWITCH_OFF));
        assertTrue(options.contains(NavigationOptionFactory.SCANNER_SWITCH_ON));
        assertTrue(options.contains(NavigationOptionFactory.FILTER_ON));
        assertTrue(options.contains(NavigationOptionFactory.NEXT_PREV_ON));

        assertFalse(options.contains(NavigationOptionFactory.WIFI_SWITCH_ON));
        assertFalse(options.contains(NavigationOptionFactory.SCANNER_SWITCH_OFF));
        assertFalse(options.contains(NavigationOptionFactory.FILTER_OFF));
        assertFalse(options.contains(NavigationOptionFactory.NEXT_PREV_OFF));
    }

}