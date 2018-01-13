/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.settings;

import com.isk.wifi.MainActivity;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.navigation.NavigationGroup;
import com.isk.wifi.navigation.NavigationMenu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class StartMenuPreferenceTest {

    private MainActivity mainActivity;
    private StartMenuPreference fixture;

    @Before
    public void setUp() {
        mainActivity = RobolectricUtil.INSTANCE.getActivity();
        fixture = new StartMenuPreference(mainActivity, Robolectric.buildAttributeSet().build());
    }

    @Test
    public void testGetEntries() throws Exception {
        // setup
        List<NavigationMenu> expected = NavigationGroup.GROUP_FEATURE.getNavigationMenus();
        // execute
        CharSequence[] actual = fixture.getEntries();
        // validate
        assertEquals(expected.size(), actual.length);
        assertEquals(mainActivity.getResources().getString(expected.get(0).getTitle()), actual[0]);
        int index = expected.size() - 1;
        assertEquals(mainActivity.getResources().getString(expected.get(index).getTitle()), actual[index]);
    }

    @Test
    public void testGetEntryValues() throws Exception {
        // setup
        List<NavigationMenu> expected = NavigationGroup.GROUP_FEATURE.getNavigationMenus();
        // execute
        CharSequence[] actual = fixture.getEntryValues();
        // validate
        assertEquals(expected.size(), actual.length);
        assertEquals("" + expected.get(0).ordinal(), actual[0]);
        int index = expected.size() - 1;
        assertEquals("" + expected.get(index).ordinal(), actual[index]);
    }
}