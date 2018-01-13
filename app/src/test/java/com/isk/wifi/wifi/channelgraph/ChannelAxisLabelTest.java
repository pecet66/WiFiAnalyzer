/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.channelgraph;

import com.isk.wifi.MainContextHelper;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.band.WiFiChannel;
import com.isk.wifi.wifi.band.WiFiChannels;
import com.isk.wifi.wifi.graphutils.GraphConstants;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChannelAxisLabelTest {
    private Settings settings;
    private ChannelAxisLabel fixture;

    @Before
    public void setUp() {
        settings = MainContextHelper.INSTANCE.getSettings();
        when(this.settings.getCountryCode()).thenReturn(Locale.US.getCountry());

        fixture = new ChannelAxisLabel(WiFiBand.GHZ2, WiFiBand.GHZ2.getWiFiChannels().getWiFiChannelPairs().get(0));
    }

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testYAxis() throws Exception {
        assertEquals(StringUtils.EMPTY, fixture.formatLabel(GraphConstants.MIN_Y, false));
        assertEquals("-99", fixture.formatLabel(GraphConstants.MIN_Y + 1, false));
        assertEquals("0", fixture.formatLabel(GraphConstants.MAX_Y, false));
        assertEquals(StringUtils.EMPTY, fixture.formatLabel(GraphConstants.MAX_Y + 1, false));
    }

    @Test
    public void testXAxis() throws Exception {
        // setup
        WiFiChannel wiFiChannel = WiFiBand.GHZ2.getWiFiChannels().getWiFiChannelFirst();
        // execute
        String actual = fixture.formatLabel(wiFiChannel.getFrequency(), true);
        // validate
        assertEquals("" + wiFiChannel.getChannel(), actual);
        verify(settings).getCountryCode();
    }

    @Test
    public void testXAxisWithFrequencyInRange() throws Exception {
        // setup
        WiFiChannel wiFiChannel = WiFiBand.GHZ2.getWiFiChannels().getWiFiChannelFirst();
        // execute & validate
        assertEquals("" + wiFiChannel.getChannel(), fixture.formatLabel(wiFiChannel.getFrequency() - 2, true));
        assertEquals("" + wiFiChannel.getChannel(), fixture.formatLabel(wiFiChannel.getFrequency() + 2, true));
        verify(settings, times(2)).getCountryCode();
    }

    @Test
    public void testXAxisWithFrequencyNotAllowedInLocale() throws Exception {
        // setup
        WiFiChannel wiFiChannel = WiFiBand.GHZ2.getWiFiChannels().getWiFiChannelLast();
        // execute
        String actual = fixture.formatLabel(wiFiChannel.getFrequency(), true);
        // validate
        assertEquals(StringUtils.EMPTY, actual);
    }

    @Test
    public void testXAxisWithUnknownFrequencyReturnEmptyString() throws Exception {
        // setup
        WiFiChannels wiFiChannels = WiFiBand.GHZ2.getWiFiChannels();
        WiFiChannel wiFiChannel = wiFiChannels.getWiFiChannelFirst();
        // execute
        String actual = fixture.formatLabel(wiFiChannel.getFrequency() - WiFiChannels.FREQUENCY_OFFSET, true);
        // validate
        assertEquals(StringUtils.EMPTY, actual);
    }

}