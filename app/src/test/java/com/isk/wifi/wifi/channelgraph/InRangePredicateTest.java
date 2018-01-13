/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.channelgraph;

import android.support.v4.util.Pair;

import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.band.WiFiChannel;
import com.isk.wifi.wifi.band.WiFiWidth;
import com.isk.wifi.wifi.model.WiFiAdditional;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.model.WiFiSignal;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InRangePredicateTest {
    private Pair<WiFiChannel, WiFiChannel> wiFiChannelPair;
    private InRangePredicate fixture;

    @Before
    public void setUp() throws Exception {
        wiFiChannelPair = WiFiBand.GHZ2.getWiFiChannels().getWiFiChannelPairs().get(0);
        fixture = new InRangePredicate(wiFiChannelPair);
    }

    @Test
    public void testInRangePredicateWithValidFrequency() throws Exception {
        // execute & validate
        assertTrue(fixture.evaluate(makeWiFiDetail(wiFiChannelPair.first.getFrequency())));
        assertTrue(fixture.evaluate(makeWiFiDetail(wiFiChannelPair.second.getFrequency())));
        assertTrue(fixture.evaluate(makeWiFiDetail((wiFiChannelPair.first.getFrequency() + wiFiChannelPair.second.getFrequency()) / 2)));
    }

    @Test
    public void testInRangePredicateWithInvalidValidFrequency() throws Exception {
        // execute & validate
        assertFalse(fixture.evaluate(makeWiFiDetail(wiFiChannelPair.first.getFrequency() - 1)));
        assertFalse(fixture.evaluate(makeWiFiDetail(wiFiChannelPair.second.getFrequency() + 1)));
    }

    private WiFiDetail makeWiFiDetail(int frequency) {
        WiFiSignal wiFiSignal = new WiFiSignal(frequency + 20, frequency, WiFiWidth.MHZ_20, -10);
        return new WiFiDetail("SSID", "BSSID", StringUtils.EMPTY, wiFiSignal, WiFiAdditional.EMPTY);
    }

}