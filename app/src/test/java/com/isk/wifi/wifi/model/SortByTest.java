/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SortByTest {

    @Test
    public void testSortByNumber() throws Exception {
        assertEquals(3, SortBy.values().length);
    }

    @Test
    public void testComparator() throws Exception {
        assertTrue(SortBy.STRENGTH.comparator() instanceof SortBy.StrengthComparator);
        assertTrue(SortBy.SSID.comparator() instanceof SortBy.SSIDComparator);
        assertTrue(SortBy.CHANNEL.comparator() instanceof SortBy.ChannelComparator);
    }

}