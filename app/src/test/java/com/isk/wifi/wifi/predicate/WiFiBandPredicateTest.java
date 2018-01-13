/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.predicate;

import android.support.annotation.NonNull;

import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.band.WiFiWidth;
import com.isk.wifi.wifi.model.WiFiAdditional;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.model.WiFiSignal;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WiFiBandPredicateTest {

    @Test
    public void testWiFiBandPredicateWith2GHzFrequency() throws Exception {
        // setup
        WiFiDetail wiFiDetail = makeWiFiDetail(2455);
        // execute & validate
        assertTrue(new WiFiBandPredicate(WiFiBand.GHZ2).evaluate(wiFiDetail));
        assertFalse(new WiFiBandPredicate(WiFiBand.GHZ5).evaluate(wiFiDetail));
    }

    @Test
    public void testWiFiBandPredicateWith5GHzFrequency() throws Exception {
        // setup
        WiFiDetail wiFiDetail = makeWiFiDetail(5455);
        // execute & validate
        assertFalse(new WiFiBandPredicate(WiFiBand.GHZ2).evaluate(wiFiDetail));
        assertTrue(new WiFiBandPredicate(WiFiBand.GHZ5).evaluate(wiFiDetail));
    }

    @NonNull
    private WiFiDetail makeWiFiDetail(int frequency) {
        WiFiSignal wiFiSignal = new WiFiSignal(frequency, frequency, WiFiWidth.MHZ_20, 1);
        return new WiFiDetail("ssid", "bssid", "wpa", wiFiSignal, WiFiAdditional.EMPTY);
    }

}