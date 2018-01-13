/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.settings;

import com.isk.util.LocaleUtils;
import com.isk.wifi.MainActivity;
import com.isk.wifi.RobolectricUtil;

import org.apache.commons.lang3.StringUtils;
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
public class LanguagePreferenceTest {

    private List<Locale> languages;
    private LanguagePreference fixture;

    @Before
    public void setUp() {
        MainActivity mainActivity = RobolectricUtil.INSTANCE.getActivity();
        fixture = new LanguagePreference(mainActivity, Robolectric.buildAttributeSet().build());

        languages = LocaleUtils.getSupportedLanguages();
    }

    @Test
    public void testGetEntries() throws Exception {
        // execute
        List<CharSequence> actual = Arrays.asList(fixture.getEntries());
        // validate
        assertEquals(languages.size(), actual.size());
        for (Locale language : languages) {
            String getDisplayName = StringUtils.capitalize(language.getDisplayName(language));
            assertTrue(getDisplayName, actual.contains(getDisplayName));
        }
    }

    @Test
    public void testGetEntryValues() throws Exception {
        // execute
        List<CharSequence> actual = Arrays.asList(fixture.getEntryValues());
        // validate
        assertEquals(languages.size(), actual.size());
        for (Locale language : languages) {
            String languageTag = LocaleUtils.toLanguageTag(language);
            assertTrue(languageTag, actual.contains(languageTag));
        }
    }

}