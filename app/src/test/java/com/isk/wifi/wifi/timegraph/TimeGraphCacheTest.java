/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.timegraph;

import com.isk.wifi.wifi.band.WiFiWidth;
import com.isk.wifi.wifi.graphutils.GraphConstants;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.model.WiFiSignal;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimeGraphCacheTest {

    private TimeGraphCache fixture;

    @Before
    public void setUp() {
        fixture = new TimeGraphCache();
    }

    @Test
    public void testAll() throws Exception {
        // setup
        List<WiFiDetail> expected = withWiFiDetails();
        // execute
        Set<WiFiDetail> actual = fixture.getWiFiDetails();
        // validate
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testActive() throws Exception {
        // setup
        List<WiFiDetail> expected = withWiFiDetails();
        // execute
        Set<WiFiDetail> actual = fixture.active();
        // validate
        assertEquals(expected.size() - 1, actual.size());
        assertFalse(actual.contains(expected.get(0)));
    }

    @Test
    public void testClear() throws Exception {
        // setup
        List<WiFiDetail> expected = withWiFiDetails();
        // execute
        fixture.clear();
        // validate
        Set<WiFiDetail> actual = fixture.getWiFiDetails();
        assertEquals(expected.size() - 1, actual.size());
        assertFalse(actual.contains(expected.get(0)));
    }

    @Test
    public void testReset() throws Exception {
        // setup
        List<WiFiDetail> expected = withWiFiDetails();
        // execute
        fixture.reset(expected.get(0));
        // validate
        Set<WiFiDetail> actual = fixture.getWiFiDetails();
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.contains(expected.get(0)));
    }

    private WiFiDetail withWiFiDetail(String SSID) {
        return new WiFiDetail(SSID, "BSSID", StringUtils.EMPTY, new WiFiSignal(100, 100, WiFiWidth.MHZ_20, 5));
    }

    private List<WiFiDetail> withWiFiDetails() {
        List<WiFiDetail> results = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            WiFiDetail wiFiDetail = withWiFiDetail("SSID" + i);
            results.add(wiFiDetail);
        }
        IterableUtils.forEach(results, new AddClosure());
        for (int i = 0; i < GraphConstants.MAX_NOTSEEN_COUNT; i++) {
            fixture.add(results.get(0));
        }
        return results;
    }

    private class AddClosure implements Closure<WiFiDetail> {
        @Override
        public void execute(WiFiDetail wiFiDetail) {
            fixture.add(wiFiDetail);
        }
    }
}