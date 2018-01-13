/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter.adapter;

import com.isk.util.EnumUtils;
import com.isk.wifi.MainContextHelper;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.model.Security;
import com.isk.wifi.wifi.model.Strength;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(RobolectricTestRunner.class)
public class FilterAdapterTest {
    private Set<String> ssids;
    private Set<WiFiBand> wiFiBands;
    private Set<Strength> strengths;
    private Set<Security> securities;

    private Settings settings;
    private FilterAdapter fixture;

    @Before
    public void setUp() {
        ssids = Collections.emptySet();
        wiFiBands = EnumUtils.values(WiFiBand.class);
        strengths = EnumUtils.values(Strength.class);
        securities = EnumUtils.values(Security.class);

        RobolectricUtil.INSTANCE.getActivity();
        settings = MainContextHelper.INSTANCE.getSettings();
        when(settings.getSSIDs()).thenReturn(ssids);
        when(settings.getWiFiBands()).thenReturn(wiFiBands);
        when(settings.getStrengths()).thenReturn(strengths);
        when(settings.getSecurities()).thenReturn(securities);

        fixture = new FilterAdapter(settings);

        verify(settings).getSSIDs();
        verify(settings).getWiFiBands();
        verify(settings).getStrengths();
        verify(settings).getSecurities();
    }

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testIsActive() throws Exception {
        // execute & validate
        assertFalse(fixture.isActive());
    }

    @Test
    public void testGetFilterAdapters() throws Exception {
        // execute
        List<? extends BasicFilterAdapter<? extends Serializable>> actual = fixture.getFilterAdapters(true);
        // validate
        assertEquals(4, actual.size());
    }

    @Test
    public void testGetFilterAdaptersWithNptAccessPoints() throws Exception {
        // execute
        List<? extends BasicFilterAdapter<? extends Serializable>> actual = fixture.getFilterAdapters(false);
        // validate
        assertEquals(3, actual.size());
    }

    @Test
    public void testIsActiveWhenStrengthFilterIsChanged() throws Exception {
        // setup
        fixture.getStrengthAdapter().toggle(Strength.THREE);
        // execute & validate
        assertTrue(fixture.isActive());
    }

    @Test
    public void testIsActiveWhenWiFiBandFilterIsChanged() throws Exception {
        // setup
        fixture.getWiFiBandAdapter().toggle(WiFiBand.GHZ2);
        // execute & validate
        assertTrue(fixture.isActive());
    }

    @Test
    public void testReset() {
        // execute
        fixture.reset();
        // validate
        verify(settings).saveSSIDs(ssids);
        verify(settings).saveWiFiBands(wiFiBands);
        verify(settings).saveStrengths(strengths);
        verify(settings).saveSecurities(securities);
    }

    @Test
    public void testReload() {
        // execute
        fixture.reload();
        // validate
        verify(settings, times(2)).getSSIDs();
        verify(settings, times(2)).getWiFiBands();
        verify(settings, times(2)).getStrengths();
        verify(settings, times(2)).getSecurities();
    }

    @Test
    public void testSave() {
        // execute
        fixture.save();
        // validate
        verify(settings).saveSSIDs(ssids);
        verify(settings).saveWiFiBands(wiFiBands);
        verify(settings).saveStrengths(strengths);
        verify(settings).saveSecurities(securities);
    }

}