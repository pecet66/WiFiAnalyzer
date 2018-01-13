/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.predicate;

import com.isk.wifi.wifi.model.WiFiAdditional;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.model.WiFiSignal;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SSIDPredicateTest {

    private static final String SSID = "ssid";

    @Test
    public void testSSIDPredicate() throws Exception {
        // setup
        WiFiDetail wiFiDetail = new WiFiDetail(SSID, "bssid", "wpa", WiFiSignal.EMPTY, WiFiAdditional.EMPTY);
        // execute & validate
        assertTrue(new SSIDPredicate(SSID).evaluate(wiFiDetail));
        assertTrue(new SSIDPredicate("id").evaluate(wiFiDetail));
        assertTrue(new SSIDPredicate("ss").evaluate(wiFiDetail));
        assertTrue(new SSIDPredicate("s").evaluate(wiFiDetail));
        assertTrue(new SSIDPredicate(StringUtils.EMPTY).evaluate(wiFiDetail));

        assertFalse(new SSIDPredicate("SSID").evaluate(wiFiDetail));
        assertFalse(new SSIDPredicate("B").evaluate(wiFiDetail));
    }

}