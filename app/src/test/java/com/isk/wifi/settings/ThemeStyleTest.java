/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.settings;

import com.isk.wifi.R;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ThemeStyleTest {

    @Test
    public void testThemeStyle() throws Exception {
        assertEquals(2, ThemeStyle.values().length);
    }

    @Test
    public void testThemeAppCompatStyle() throws Exception {
        assertEquals(R.style.ThemeAppCompatLight, ThemeStyle.LIGHT.themeAppCompatStyle());
        assertEquals(R.style.ThemeAppCompatDark, ThemeStyle.DARK.themeAppCompatStyle());
    }

    @Test
    public void testThemeDeviceDefaultStyle() throws Exception {
        assertEquals(R.style.ThemeDeviceDefaultLight, ThemeStyle.LIGHT.themeDeviceDefaultStyle());
        assertEquals(R.style.ThemeDeviceDefaultDark, ThemeStyle.DARK.themeDeviceDefaultStyle());
    }
}