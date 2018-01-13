/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi;

import com.isk.wifi.settings.Settings;
import com.isk.wifi.settings.ThemeStyle;
import com.isk.wifi.wifi.accesspoint.AccessPointViewType;
import com.isk.wifi.wifi.accesspoint.ConnectionViewType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainReloadTest {
    private static final int GRAPH_MAXIMUM_Y = 10;
    private static final Locale TEST_LOCALE = Locale.UK;
    private Settings settings;
    private MainReload fixture;

    @Before
    public void setUp() {
        settings = MainContextHelper.INSTANCE.getSettings();

        when(settings.getThemeStyle()).thenReturn(ThemeStyle.DARK);
        when(settings.getAccessPointView()).thenReturn(AccessPointViewType.COMPLETE);
        when(settings.getConnectionViewType()).thenReturn(ConnectionViewType.COMPLETE);
        when(settings.getGraphMaximumY()).thenReturn(GRAPH_MAXIMUM_Y);
        when(settings.getLanguageLocale()).thenReturn(TEST_LOCALE);

        fixture = new MainReload(settings);
    }

    @After
    public void tearDown() {
        verify(settings, atLeastOnce()).getThemeStyle();
        verify(settings, atLeastOnce()).getAccessPointView();
        verify(settings, atLeastOnce()).getConnectionViewType();
        verify(settings, atLeastOnce()).getGraphMaximumY();
        verify(settings, atLeastOnce()).getLanguageLocale();

        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testShouldNotReloadWithNoThemeChanges() throws Exception {
        // execute
        boolean actual = fixture.shouldReload(settings);
        // validate
        assertFalse(actual);
        assertEquals(ThemeStyle.DARK, fixture.getThemeStyle());
    }

    @Test
    public void testShouldReloadWithThemeChange() throws Exception {
        // setup
        ThemeStyle expected = ThemeStyle.LIGHT;
        when(settings.getThemeStyle()).thenReturn(expected);
        // execute
        boolean actual = fixture.shouldReload(settings);
        // validate
        assertTrue(actual);
        assertEquals(expected, fixture.getThemeStyle());
    }

    @Test
    public void testShouldNotReloadWithNoAccessPointViewChanges() throws Exception {
        // execute
        boolean actual = fixture.shouldReload(settings);
        // validate
        assertFalse(actual);
        assertEquals(AccessPointViewType.COMPLETE, fixture.getAccessPointViewType());
    }

    @Test
    public void testShouldReloadWithAccessPointViewChange() throws Exception {
        // setup
        AccessPointViewType expected = AccessPointViewType.COMPACT;
        when(settings.getAccessPointView()).thenReturn(expected);
        // execute
        boolean actual = fixture.shouldReload(settings);
        // validate
        assertTrue(actual);
        assertEquals(expected, fixture.getAccessPointViewType());
    }

    @Test
    public void testShouldNotReloadWithNoConnectionViewTypeChanges() throws Exception {
        // execute
        boolean actual = fixture.shouldReload(settings);
        // validate
        assertFalse(actual);
        assertEquals(ConnectionViewType.COMPLETE, fixture.getConnectionViewType());
    }

    @Test
    public void testShouldReloadWithConnectionViewTypeChange() throws Exception {
        // setup
        ConnectionViewType expected = ConnectionViewType.COMPACT;
        when(settings.getConnectionViewType()).thenReturn(expected);
        // execute
        boolean actual = fixture.shouldReload(settings);
        // validate
        assertTrue(actual);
        assertEquals(expected, fixture.getConnectionViewType());
    }

    @Test
    public void testShouldNotReloadWithNoGraphMaximumYChanges() throws Exception {
        // execute
        boolean actual = fixture.shouldReload(settings);
        // validate
        assertFalse(actual);
        assertEquals(GRAPH_MAXIMUM_Y, fixture.getGraphMaximumY());
    }

    @Test
    public void testShouldReloadWithGraphMaximumYChange() throws Exception {
        // setup
        int expected = -GRAPH_MAXIMUM_Y;
        when(settings.getGraphMaximumY()).thenReturn(expected);
        // execute
        boolean actual = fixture.shouldReload(settings);
        // validate
        assertTrue(actual);
        assertEquals(expected, fixture.getGraphMaximumY());
    }

    @Test
    public void testShouldNotReloadWithNoLanguageLocaleChanges() throws Exception {
        // execute
        boolean actual = fixture.shouldReload(settings);
        // validate
        assertFalse(actual);
        assertEquals(Locale.UK, fixture.getLanguageLocale());
    }

    @Test
    public void testShouldReloadWithLanguageLocaleChange() throws Exception {
        // setup
        Locale expected = Locale.US;
        when(settings.getLanguageLocale()).thenReturn(expected);
        // execute
        boolean actual = fixture.shouldReload(settings);
        // validate
        assertTrue(actual);
        assertEquals(expected, fixture.getLanguageLocale());
    }

}
