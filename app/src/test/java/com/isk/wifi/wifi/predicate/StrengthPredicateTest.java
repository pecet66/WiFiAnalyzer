/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.predicate;

import android.support.annotation.NonNull;

import com.isk.wifi.wifi.band.WiFiWidth;
import com.isk.wifi.wifi.model.Strength;
import com.isk.wifi.wifi.model.WiFiAdditional;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.model.WiFiSignal;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StrengthPredicateTest {

    @Test
    public void testStrengthPredicate() throws Exception {
        // setup
        WiFiDetail wiFiDetail = makeWiFiDetail(-60);
        // execute & validate
        assertTrue(new StrengthPredicate(Strength.THREE).evaluate(wiFiDetail));
        assertFalse(new StrengthPredicate(Strength.FOUR).evaluate(wiFiDetail));
    }

    @NonNull
    private WiFiDetail makeWiFiDetail(int level) {
        WiFiSignal wiFiSignal = new WiFiSignal(2445, 2445, WiFiWidth.MHZ_20, level);
        return new WiFiDetail("ssid", "bssid", "wpa", wiFiSignal, WiFiAdditional.EMPTY);
    }

}