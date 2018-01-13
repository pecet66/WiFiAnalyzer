/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class NavigationGroupTest {

    @Test
    public void testNavigationGroup() throws Exception {
        assertEquals(3, NavigationGroup.values().length);
    }

    @Test
    public void testNavigationGroupOrder() throws Exception {
        assertArrayEquals(new NavigationGroup[]{
                NavigationGroup.GROUP_FEATURE,
                NavigationGroup.GROUP_OTHER,
                NavigationGroup.GROUP_SETTINGS,
            },
            NavigationGroup.values());
    }

    @Test
    public void testGetNavigationMenus() throws Exception {
        assertArrayEquals(new NavigationMenu[]{
                NavigationMenu.ACCESS_POINTS,
                NavigationMenu.CHANNEL_RATING,
                NavigationMenu.CHANNEL_GRAPH,
                NavigationMenu.TIME_GRAPH
            },
            NavigationGroup.GROUP_FEATURE.getNavigationMenus().toArray());
        assertArrayEquals(new NavigationMenu[]{
                NavigationMenu.EXPORT,
                NavigationMenu.CHANNEL_AVAILABLE,
                NavigationMenu.VENDOR_LIST
            },
            NavigationGroup.GROUP_OTHER.getNavigationMenus().toArray());
        assertArrayEquals(new NavigationMenu[]{
                NavigationMenu.SETTINGS,
                NavigationMenu.ABOUT
            },
            NavigationGroup.GROUP_SETTINGS.getNavigationMenus().toArray());
    }

    @Test
    public void testNext() throws Exception {
        assertEquals(NavigationMenu.CHANNEL_GRAPH, NavigationGroup.GROUP_FEATURE.next(NavigationMenu.CHANNEL_RATING));
        assertEquals(NavigationMenu.ACCESS_POINTS, NavigationGroup.GROUP_FEATURE.next(NavigationMenu.TIME_GRAPH));
        assertEquals(NavigationMenu.EXPORT, NavigationGroup.GROUP_FEATURE.next(NavigationMenu.EXPORT));
    }

    @Test
    public void testPrevious() throws Exception {
        assertEquals(NavigationMenu.ACCESS_POINTS, NavigationGroup.GROUP_FEATURE.previous(NavigationMenu.CHANNEL_RATING));
        assertEquals(NavigationMenu.TIME_GRAPH, NavigationGroup.GROUP_FEATURE.previous(NavigationMenu.ACCESS_POINTS));
        assertEquals(NavigationMenu.EXPORT, NavigationGroup.GROUP_FEATURE.next(NavigationMenu.EXPORT));
    }

}