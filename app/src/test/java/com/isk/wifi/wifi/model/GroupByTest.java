/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.model;

import com.isk.wifi.wifi.band.WiFiWidth;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GroupByTest {
    private WiFiDetail wiFiDetail1;
    private WiFiDetail wiFiDetail2;

    @Before
    public void setUp() {
        wiFiDetail1 = new WiFiDetail("SSID1", "BSSID1", StringUtils.EMPTY,
            new WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -35), WiFiAdditional.EMPTY);
        wiFiDetail2 = new WiFiDetail("SSID2", "BSSID2", StringUtils.EMPTY,
            new WiFiSignal(2432, 2432, WiFiWidth.MHZ_20, -55), WiFiAdditional.EMPTY);
    }


    @Test
    public void testGroupByNumber() throws Exception {
        assertEquals(3, GroupBy.values().length);
    }

    @Test
    public void testGroupBy() throws Exception {
        assertTrue(GroupBy.NONE.groupByComparator() instanceof GroupBy.None);
        assertTrue(GroupBy.SSID.groupByComparator() instanceof GroupBy.SSIDGroupBy);
        assertTrue(GroupBy.CHANNEL.groupByComparator() instanceof GroupBy.ChannelGroupBy);
    }

    @Test
    public void testSortOrder() throws Exception {
        assertTrue(GroupBy.NONE.sortOrderComparator() instanceof GroupBy.None);
        assertTrue(GroupBy.SSID.sortOrderComparator() instanceof GroupBy.SSIDSortOrder);
        assertTrue(GroupBy.CHANNEL.sortOrderComparator() instanceof GroupBy.ChannelSortOrder);
    }

    @Test
    public void testNoneComparator() throws Exception {
        // setup
        GroupBy.None comparator = new GroupBy.None();
        // execute & validate
        assertEquals(0, comparator.compare(wiFiDetail1, wiFiDetail1));
        assertEquals(0, comparator.compare(wiFiDetail2, wiFiDetail2));
        assertEquals(1, comparator.compare(wiFiDetail1, wiFiDetail2));
        assertEquals(1, comparator.compare(wiFiDetail2, wiFiDetail1));
    }

    @Test
    public void testChannelGroupByComparator() throws Exception {
        // setup
        GroupBy.ChannelGroupBy comparator = new GroupBy.ChannelGroupBy();
        // execute & validate
        assertEquals(0, comparator.compare(wiFiDetail1, wiFiDetail1));
        assertEquals(1, comparator.compare(wiFiDetail1, wiFiDetail2));
        assertEquals(-1, comparator.compare(wiFiDetail2, wiFiDetail1));
    }

    @Test
    public void testChannelSortOrder() throws Exception {
        // setup
        GroupBy.ChannelSortOrder comparator = new GroupBy.ChannelSortOrder();
        // execute & validate
        assertEquals(0, comparator.compare(wiFiDetail1, wiFiDetail1));
        assertEquals(1, comparator.compare(wiFiDetail1, wiFiDetail2));
        assertEquals(-1, comparator.compare(wiFiDetail2, wiFiDetail1));
    }

    @Test
    public void testSSIDGroupByComparator() throws Exception {
        // setup
        GroupBy.SSIDGroupBy comparator = new GroupBy.SSIDGroupBy();
        // execute & validate
        assertEquals(0, comparator.compare(wiFiDetail1, wiFiDetail1));
        assertEquals(-1, comparator.compare(wiFiDetail1, wiFiDetail2));
        assertEquals(1, comparator.compare(wiFiDetail2, wiFiDetail1));
    }

    @Test
    public void testSSIDSortOrderComparatorEquals() throws Exception {
        GroupBy.SSIDSortOrder comparator = new GroupBy.SSIDSortOrder();
        // execute & validate
        assertEquals(0, comparator.compare(wiFiDetail1, wiFiDetail1));
        assertEquals(-1, comparator.compare(wiFiDetail1, wiFiDetail2));
        assertEquals(1, comparator.compare(wiFiDetail2, wiFiDetail1));
    }

}