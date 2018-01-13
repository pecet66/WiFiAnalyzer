/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.channelavailable;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.isk.wifi.MainActivity;
import com.isk.wifi.MainContextHelper;
import com.isk.wifi.R;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.band.WiFiChannelCountry;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Collections;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class ChannelAvailableAdapterTest {
    private Settings settings;
    private MainActivity mainActivity;
    private WiFiChannelCountry wiFiChannelCountry;
    private Locale currentLocale;
    private ChannelAvailableAdapter fixture;

    @Before
    public void setUp() {
        currentLocale = Locale.getDefault();

        mainActivity = RobolectricUtil.INSTANCE.getActivity();

        settings = MainContextHelper.INSTANCE.getSettings();
        when(settings.getLanguageLocale()).thenReturn(currentLocale);

        wiFiChannelCountry = WiFiChannelCountry.get(currentLocale.getCountry());
        fixture = new ChannelAvailableAdapter(mainActivity, Collections.singletonList(wiFiChannelCountry));
    }

    @After
    public void tearDown() {
        verify(settings, atLeastOnce()).getLanguageLocale();
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testGetView() throws Exception {
        // setup
        Resources resources = mainActivity.getResources();
        String wiFiBand2 = resources.getString(WiFiBand.GHZ2.getTextResource());
        String wiFiBand5 = resources.getString(WiFiBand.GHZ5.getTextResource());
        String expected = wiFiChannelCountry.getCountryCode() + " - " + wiFiChannelCountry.getCountryName(currentLocale);
        String expected_GHZ_2 = StringUtils.join(wiFiChannelCountry.getChannelsGHZ2().toArray(), ",");
        String expected_GHZ_5 = StringUtils.join(wiFiChannelCountry.getChannelsGHZ5().toArray(), ",");
        ViewGroup viewGroup = mainActivity.findViewById(android.R.id.content);
        // execute
        View actual = fixture.getView(0, null, viewGroup);
        // validate
        assertNotNull(actual);

        assertEquals(expected, ((TextView) actual.findViewById(R.id.channel_available_country)).getText());
        assertEquals(wiFiBand2 + " : ", ((TextView) actual.findViewById(R.id.channel_available_title_ghz_2)).getText());
        assertEquals(expected_GHZ_2, ((TextView) actual.findViewById(R.id.channel_available_ghz_2)).getText());
        assertEquals(wiFiBand5 + " : ", ((TextView) actual.findViewById(R.id.channel_available_title_ghz_5)).getText());
        assertEquals(expected_GHZ_5, ((TextView) actual.findViewById(R.id.channel_available_ghz_5)).getText());
    }

}