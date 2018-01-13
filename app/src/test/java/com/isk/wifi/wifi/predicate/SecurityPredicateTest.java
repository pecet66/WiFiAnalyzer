/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.predicate;

import android.support.annotation.NonNull;

import com.isk.wifi.wifi.band.WiFiWidth;
import com.isk.wifi.wifi.model.Security;
import com.isk.wifi.wifi.model.WiFiAdditional;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.model.WiFiSignal;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SecurityPredicateTest {

    @Test
    public void testSecurityPredicateWithFoundValue() throws Exception {
        // setup
        WiFiDetail wiFiDetail = makeWiFiDetail("wpa");
        SecurityPredicate fixture = new SecurityPredicate(Security.WPA);
        // execute
        boolean actual = fixture.evaluate(wiFiDetail);
        // validate
        assertTrue(actual);
        assertFalse(new SecurityPredicate(Security.WEP).evaluate(wiFiDetail));
    }

    @Test
    public void testSecurityPredicateWithNotFoundValue() throws Exception {
        // setup
        WiFiDetail wiFiDetail = makeWiFiDetail("wep");
        SecurityPredicate fixture = new SecurityPredicate(Security.WPA);
        // execute
        boolean actual = fixture.evaluate(wiFiDetail);
        // validate
        assertFalse(actual);
    }

    @NonNull
    private WiFiDetail makeWiFiDetail(String security) {
        WiFiSignal wiFiSignal = new WiFiSignal(2455, 2455, WiFiWidth.MHZ_20, 1);
        return new WiFiDetail("ssid", "bssid", security, wiFiSignal, WiFiAdditional.EMPTY);
    }

}