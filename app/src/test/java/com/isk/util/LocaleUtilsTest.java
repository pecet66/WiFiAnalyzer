/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class LocaleUtilsTest {

    @Test
    public void testGetAllCountries() throws Exception {
        // execute
        List<Locale> actual = LocaleUtils.getAllCountries();
        // validate
        assertTrue(actual.size() >= 2);
        assertTrue(actual.get(0).getCountry().compareTo(actual.get(actual.size() - 1).getCountry()) < 0);
    }

    @Test
    public void testFindByCountryCode() throws Exception {
        // setup
        Locale expected = LocaleUtils.getAllCountries().get(0);
        // execute
        Locale actual = LocaleUtils.findByCountryCode(expected.getCountry());
        // validate
        assertEquals(expected, actual);
        assertEquals(expected.getCountry(), actual.getCountry());
        assertEquals(expected.getDisplayCountry(), actual.getDisplayCountry());

        assertNotEquals(expected.getCountry(), expected.getDisplayCountry());
        assertNotEquals(actual.getCountry(), actual.getDisplayCountry());
    }

    @Test
    public void testFindByCountryCodeWithUnknownCode() throws Exception {
        // execute
        Locale actual = LocaleUtils.findByCountryCode("WW");
        // validate
        assertEquals(Locale.getDefault(), actual);
    }

    @Test
    public void testToLanguageTag() throws Exception {
        assertEquals(Locale.US.getLanguage() + "_" + Locale.US.getCountry(), LocaleUtils.toLanguageTag(Locale.US));
        assertEquals(Locale.ENGLISH.getLanguage() + "_", LocaleUtils.toLanguageTag(Locale.ENGLISH));
    }

    @Test
    public void testFindByLanguageTagWithUnknownTag() throws Exception {
        Locale defaultLocal = Locale.getDefault();
        assertEquals(defaultLocal, LocaleUtils.findByLanguageTag(StringUtils.EMPTY));
        assertEquals(defaultLocal, LocaleUtils.findByLanguageTag("WW"));
        assertEquals(defaultLocal, LocaleUtils.findByLanguageTag("WW_HH_TT"));
    }

    @Test
    public void testFindByLanguageTag() throws Exception {
        assertEquals(Locale.SIMPLIFIED_CHINESE, LocaleUtils.findByLanguageTag(LocaleUtils.toLanguageTag(Locale.SIMPLIFIED_CHINESE)));
        assertEquals(Locale.TRADITIONAL_CHINESE, LocaleUtils.findByLanguageTag(LocaleUtils.toLanguageTag(Locale.TRADITIONAL_CHINESE)));
        assertEquals(Locale.ENGLISH, LocaleUtils.findByLanguageTag(LocaleUtils.toLanguageTag(Locale.ENGLISH)));
    }

    @Test
    public void testGetSupportedLanguages() throws Exception {
        // setup
        Set<Locale> expected = new HashSet<>(Arrays.asList(
            Locale.GERMAN, Locale.ENGLISH, Locale.FRENCH, Locale.ITALIAN, Locale.SIMPLIFIED_CHINESE, Locale.TRADITIONAL_CHINESE,
            LocaleUtils.SPANISH, LocaleUtils.PORTUGUESE, LocaleUtils.RUSSIAN, Locale.getDefault()));
        // execute
        List<Locale> actual = LocaleUtils.getSupportedLanguages();
        // validate
        assertEquals(expected.size(), actual.size());
        for (Locale locale : expected) {
            assertTrue(actual.contains(locale));
        }
    }

    @Test
    public void testGetDefaultCountryCode() throws Exception {
        assertEquals(Locale.getDefault().getCountry(), LocaleUtils.getDefaultCountryCode());
    }

    @Test
    public void testGetDefaultLanguageTag() throws Exception {
        assertEquals(LocaleUtils.toLanguageTag(Locale.getDefault()), LocaleUtils.getDefaultLanguageTag());
    }

}
