/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.predicate;

import com.isk.util.EnumUtils;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.band.WiFiWidth;
import com.isk.wifi.wifi.model.Security;
import com.isk.wifi.wifi.model.Strength;
import com.isk.wifi.wifi.model.WiFiAdditional;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.model.WiFiSignal;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.AllPredicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilterPredicateTest {

    private static final String SSID = "SSID";
    private static final String WPA2 = "WPA2";

    @Mock
    private Settings settings;

    private Predicate<WiFiDetail> fixture;

    @Before
    public void setUp() {
        when(settings.getWiFiBand()).thenReturn(WiFiBand.GHZ5);
        when(settings.getSSIDs()).thenReturn(new HashSet<>(Arrays.asList(SSID, SSID)));
        when(settings.getWiFiBands()).thenReturn(Collections.singleton(WiFiBand.GHZ2));
        when(settings.getStrengths()).thenReturn(new HashSet<>(Arrays.asList(Strength.TWO, Strength.FOUR)));
        when(settings.getSecurities()).thenReturn(new HashSet<>(Arrays.asList(Security.WEP, Security.WPA2)));
    }

    @Test
    public void testMakeAccessPointsPredicate() throws Exception {
        // execute
        fixture = FilterPredicate.makeAccessPointsPredicate(settings);
        // validate
        assertNotNull(fixture);
        verify(settings).getSSIDs();
        verify(settings).getWiFiBands();
        verify(settings).getStrengths();
        verify(settings).getSecurities();
    }

    @Test
    public void testMakeOtherPredicate() throws Exception {
        // execute
        fixture = FilterPredicate.makeOtherPredicate(settings);
        // validate
        assertNotNull(fixture);
        verify(settings).getSSIDs();
        verify(settings).getWiFiBand();
        verify(settings).getStrengths();
        verify(settings).getSecurities();
    }

    @Test
    public void testEvaluateToTrue() throws Exception {
        // setup
        fixture = FilterPredicate.makeAccessPointsPredicate(settings);
        WiFiDetail wiFiDetail = makeWiFiDetail(SSID, WPA2);
        // execute
        boolean actual = fixture.evaluate(wiFiDetail);
        // validate
        assertTrue(actual);
    }

    @Test
    public void testEvaluateWithSecurityToFalse() throws Exception {
        // setup
        fixture = FilterPredicate.makeAccessPointsPredicate(settings);
        WiFiDetail wiFiDetail = makeWiFiDetail(SSID, "WPA");
        // execute
        boolean actual = fixture.evaluate(wiFiDetail);
        // validate
        assertFalse(actual);
    }

    @Test
    public void testEvaluateWithSSIDToFalse() throws Exception {
        // setup
        fixture = FilterPredicate.makeAccessPointsPredicate(settings);
        WiFiDetail wiFiDetail = makeWiFiDetail("WIFI", WPA2);
        // execute
        boolean actual = fixture.evaluate(wiFiDetail);
        // validate
        assertFalse(actual);
    }

    @Test
    public void testGetPredicateWithSomeValuesIsAnyPredicate() throws Exception {
        // setup
        fixture = FilterPredicate.makeAccessPointsPredicate(settings);
        // execute
        Predicate<WiFiDetail> actual = ((FilterPredicate) fixture).getPredicate();
        // validate
        assertTrue(actual instanceof AllPredicate);
    }

    @Test
    public void testGetPredicateWithAllValuesIsTruePredicate() throws Exception {
        // setup
        when(settings.getSSIDs()).thenReturn(Collections.<String>emptySet());
        when(settings.getWiFiBands()).thenReturn(EnumUtils.values(WiFiBand.class));
        when(settings.getStrengths()).thenReturn(EnumUtils.values(Strength.class));
        when(settings.getSecurities()).thenReturn(EnumUtils.values(Security.class));

        fixture = FilterPredicate.makeAccessPointsPredicate(settings);
        // execute
        Predicate<WiFiDetail> actual = ((FilterPredicate) fixture).getPredicate();
        // validate
        assertTrue(actual instanceof TruePredicate);
    }

    private WiFiDetail makeWiFiDetail(String ssid, String security) {
        WiFiSignal wiFiSignal = new WiFiSignal(2445, 2445, WiFiWidth.MHZ_20, -40);
        return new WiFiDetail(ssid, "BSSID", security, wiFiSignal, WiFiAdditional.EMPTY);
    }

}
