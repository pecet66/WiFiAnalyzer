/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.band;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

public class WiFiChannelTest {
    private static final int CHANNEL = 1;
    private static final int FREQUENCY = 200;

    private WiFiChannel fixture;
    private WiFiChannel other;

    @Before
    public void setUp() {
        fixture = new WiFiChannel(CHANNEL, FREQUENCY);
        other = new WiFiChannel(CHANNEL, FREQUENCY);
    }

    @Test
    public void testIsInRange() throws Exception {
        assertTrue(fixture.isInRange(FREQUENCY));
        assertTrue(fixture.isInRange(FREQUENCY - 2));
        assertTrue(fixture.isInRange(FREQUENCY + 2));

        assertFalse(fixture.isInRange(FREQUENCY - 3));
        assertFalse(fixture.isInRange(FREQUENCY + 3));
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(fixture, other);
        assertNotSame(fixture, other);
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(fixture.hashCode(), other.hashCode());
    }

    @Test
    public void testCompareTo() throws Exception {
        assertEquals(0, fixture.compareTo(other));
    }
}