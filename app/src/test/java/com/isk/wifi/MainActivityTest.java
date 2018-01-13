/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi;

import android.view.Menu;
import android.view.MenuItem;

import com.isk.wifi.menu.OptionMenu;
import com.isk.wifi.navigation.NavigationMenu;
import com.isk.wifi.navigation.NavigationMenuView;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.scanner.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity fixture;

    @Before
    public void setUp() {
        fixture = Robolectric.setupActivity(MainActivity.class);
    }

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testMainActivity() throws Exception {
        assertTrue(MainContext.INSTANCE.getScanner().isRunning());
    }

    @Test
    public void testClickingOnToolbarTogglesWiFiBand() throws Exception {
        // setup
        NavigationMenuView navigationMenuView = fixture.getNavigationMenuView();
        navigationMenuView.setCurrentNavigationMenu(NavigationMenu.CHANNEL_RATING);
        // execute and validate
        assertEquals(WiFiBand.GHZ2, MainContext.INSTANCE.getSettings().getWiFiBand());
        fixture.findViewById(R.id.toolbar).performClick();
        assertEquals(WiFiBand.GHZ5, MainContext.INSTANCE.getSettings().getWiFiBand());
        fixture.findViewById(R.id.toolbar).performClick();
        assertEquals(WiFiBand.GHZ2, MainContext.INSTANCE.getSettings().getWiFiBand());
    }

    @Test
    public void testClickingOnToolbarDoesNotTogglesWiFiBand() throws Exception {
        // setup
        NavigationMenuView navigationMenuView = fixture.getNavigationMenuView();
        navigationMenuView.setCurrentNavigationMenu(NavigationMenu.VENDOR_LIST);
        // execute and validate
        assertEquals(WiFiBand.GHZ2, MainContext.INSTANCE.getSettings().getWiFiBand());
        fixture.findViewById(R.id.toolbar).performClick();
        assertEquals(WiFiBand.GHZ2, MainContext.INSTANCE.getSettings().getWiFiBand());
    }

    @Test
    public void testOnPauseCallsOptionMenuPause() throws Exception {
        // setup
        OptionMenu optionMenu = mock(OptionMenu.class);
        fixture.setOptionMenu(optionMenu);
        // execute
        fixture.onPause();
        // validate
        verify(optionMenu).pause();
    }

    @Test
    public void testOnResumeCallsOptionMenuResume() throws Exception {
        // setup
        OptionMenu optionMenu = mock(OptionMenu.class);
        fixture.setOptionMenu(optionMenu);
        // execute
        fixture.onResume();
        // validate
        verify(optionMenu).resume();
    }

    @Test
    public void testOnCreateOptionsMenu() throws Exception {
        // setup
        Menu menu = mock(Menu.class);
        OptionMenu optionMenu = mock(OptionMenu.class);
        fixture.setOptionMenu(optionMenu);
        // execute
        boolean actual = fixture.onCreateOptionsMenu(menu);
        // validate
        assertTrue(actual);
        verify(optionMenu).create(fixture, menu);
    }

    @Test
    public void testOnOptionsItemSelected() throws Exception {
        // setup
        MenuItem menuItem = mock(MenuItem.class);
        OptionMenu optionMenu = mock(OptionMenu.class);
        fixture.setOptionMenu(optionMenu);
        // execute
        boolean actual = fixture.onOptionsItemSelected(menuItem);
        // validate
        assertTrue(actual);
        verify(optionMenu).select(menuItem);
    }

    @Test
    public void testOnStop() throws Exception {
        // setup
        Scanner scanner = MainContextHelper.INSTANCE.getScanner();
        // execute
        fixture.onStop();
        // validate
        verify(scanner).setWiFiOnExit();
    }

    @Test
    public void testOptionMenu() throws Exception {
        // setup
        OptionMenu optionMenu = fixture.getOptionMenu();
        // execute
        Menu actual = optionMenu.getMenu();
        // validate
        assertNotNull(actual.findItem(R.id.action_filter));
        assertNotNull(actual.findItem(R.id.action_scanner));
    }

}