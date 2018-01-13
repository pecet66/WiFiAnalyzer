/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.graphutils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class GraphColorTest {
    private GraphColor fixture;
    private GraphColor other;

    @Before
    public void setUp() {
        fixture = new GraphColor(1, 2);
        other = new GraphColor(1, 2);
    }

    @Test
    public void testEquals() throws Exception {
        // execute & validate
        assertEquals(fixture, other);
        assertNotSame(fixture, other);
    }

    @Test
    public void testHashCode() throws Exception {
        // execute & validate
        assertEquals(fixture.hashCode(), other.hashCode());
    }
}