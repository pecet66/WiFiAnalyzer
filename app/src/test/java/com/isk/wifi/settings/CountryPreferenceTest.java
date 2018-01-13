/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.settings;

import com.isk.wifi.MainActivity;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.wifi.band.WiFiChannelCountry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class CountryPreferenceTest {

    private List<WiFiChannelCountry> countries;
    private CountryPreference fixture;
    private Locale currentLocale;

    @Before
    public void setUp() {
        MainActivity mainActivity = RobolectricUtil.INSTANCE.getActivity();
        fixture = new CountryPreference(mainActivity, Robolectric.buildAttributeSet().build());

        currentLocale = Locale.getDefault();
        countries = WiFiChannelCountry.getAll();
    }

    @Test
    public void testGetEntries() throws Exception {
        // execute
        List<CharSequence> actual = Arrays.asList(fixture.getEntries());
        // validate
        assertEquals(countries.size(), actual.size());
        for (WiFiChannelCountry country : countries) {
            String countryName = country.getCountryName(currentLocale);
            assertTrue(countryName, actual.contains(countryName));
        }
    }

    @Test
    public void testGetEntryValues() throws Exception {
        // execute
        List<CharSequence> actual = Arrays.asList(fixture.getEntryValues());
        // validate
        assertEquals(countries.size(), actual.size());
        for (WiFiChannelCountry country : countries) {
            String countryCode = country.getCountryCode();
            assertTrue(countryCode, actual.contains(countryCode));
        }
    }

}