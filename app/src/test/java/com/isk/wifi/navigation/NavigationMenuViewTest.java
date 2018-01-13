/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;

import com.isk.util.EnumUtils;
import com.isk.wifi.MainActivity;
import com.isk.wifi.RobolectricUtil;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class NavigationMenuViewTest {
    private MainActivity mainActivity;
    private NavigationMenuView fixture;
    private NavigationView navigationView;

    @Before
    public void setUp() {
        mainActivity = RobolectricUtil.INSTANCE.getActivity();
        fixture = mainActivity.getNavigationMenuView();
        navigationView = fixture.getNavigationView();
    }

    @After
    public void tearDown() {
        fixture.setCurrentNavigationMenu(NavigationMenu.ACCESS_POINTS);
    }

    @Test
    public void testNavigationMenuView() throws Exception {
        // execute
        Menu menu = navigationView.getMenu();
        // validate
        assertEquals(NavigationMenu.values().length, menu.size());
        IterableUtils.forEach(EnumUtils.values(NavigationGroup.class), new NavigationGroupClosure(menu));
    }

    @Test
    public void testGetCurrentMenuItem() throws Exception {
        // setup
        MenuItem expected = getMenuItem(NavigationMenu.ACCESS_POINTS);
        // execute
        MenuItem actual = fixture.getCurrentMenuItem();
        // validate
        assertEquals(expected, actual);
        assertTrue(actual.isCheckable());
        assertTrue(actual.isChecked());
    }

    @Test
    public void testGetCurrentNavigationMenu() throws Exception {
        // execute
        NavigationMenu actual = fixture.getCurrentNavigationMenu();
        // validate
        assertEquals(NavigationMenu.ACCESS_POINTS, actual);
    }

    @Test
    public void testSetCurrentNavigationMenu() throws Exception {
        // setup
        NavigationMenu expected = NavigationMenu.CHANNEL_GRAPH;
        // execute
        fixture.setCurrentNavigationMenu(expected);
        // validate
        assertEquals(expected, fixture.getCurrentNavigationMenu());
        assertTrue(getMenuItem(NavigationMenu.CHANNEL_GRAPH).isCheckable());
        assertTrue(getMenuItem(NavigationMenu.CHANNEL_GRAPH).isChecked());
        assertFalse(getMenuItem(NavigationMenu.ACCESS_POINTS).isCheckable());
        assertFalse(getMenuItem(NavigationMenu.ACCESS_POINTS).isChecked());
    }

    private MenuItem getMenuItem(NavigationMenu navigationMenu) {
        return navigationView.getMenu().getItem(navigationMenu.ordinal());
    }

    private class NavigationGroupClosure implements Closure<NavigationGroup> {
        private final Menu menu;

        private NavigationGroupClosure(@NonNull Menu menu) {
            this.menu = menu;
        }

        @Override
        public void execute(NavigationGroup navigationGroup) {
            IterableUtils.forEach(navigationGroup.getNavigationMenus(), new NavigationMenuClosure(menu, navigationGroup));
        }
    }

    private class NavigationMenuClosure implements Closure<NavigationMenu> {
        private final Menu menu;
        private final NavigationGroup navigationGroup;

        private NavigationMenuClosure(@NonNull Menu menu, @NonNull NavigationGroup navigationGroup) {
            this.menu = menu;
            this.navigationGroup = navigationGroup;
        }

        @Override
        public void execute(NavigationMenu navigationMenu) {
            MenuItem actual = menu.getItem(navigationMenu.ordinal());
            assertEquals(navigationGroup.ordinal(), actual.getGroupId());
            assertEquals(mainActivity.getResources().getString(navigationMenu.getTitle()), actual.getTitle());
            assertEquals(navigationMenu.ordinal(), actual.getItemId());
            assertEquals(navigationMenu.ordinal(), actual.getOrder());
        }
    }
}