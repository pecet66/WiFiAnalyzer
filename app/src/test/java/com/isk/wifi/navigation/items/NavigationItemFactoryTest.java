/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.items;

import com.isk.wifi.about.AboutActivity;
import com.isk.wifi.settings.SettingActivity;
import com.isk.wifi.vendor.VendorFragment;
import com.isk.wifi.wifi.accesspoint.AccessPointsFragment;
import com.isk.wifi.wifi.channelavailable.ChannelAvailableFragment;
import com.isk.wifi.wifi.channelgraph.ChannelGraphFragment;
import com.isk.wifi.wifi.channelrating.ChannelRatingFragment;
import com.isk.wifi.wifi.timegraph.TimeGraphFragment;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NavigationItemFactoryTest {

    @Test
    public void testFragmentItem() throws Exception {
        assertTrue(((FragmentItem) NavigationItemFactory.ACCESS_POINTS).getFragment() instanceof AccessPointsFragment);
        assertTrue(((FragmentItem) NavigationItemFactory.CHANNEL_RATING).getFragment() instanceof ChannelRatingFragment);
        assertTrue(((FragmentItem) NavigationItemFactory.CHANNEL_GRAPH).getFragment() instanceof ChannelGraphFragment);
        assertTrue(((FragmentItem) NavigationItemFactory.TIME_GRAPH).getFragment() instanceof TimeGraphFragment);
        assertTrue(((FragmentItem) NavigationItemFactory.CHANNEL_AVAILABLE).getFragment() instanceof ChannelAvailableFragment);
        assertTrue(((FragmentItem) NavigationItemFactory.VENDOR_LIST).getFragment() instanceof VendorFragment);
    }

    @Test
    public void testIsRegisteredTrue() throws Exception {
        assertTrue(NavigationItemFactory.ACCESS_POINTS.isRegistered());
        assertTrue(NavigationItemFactory.CHANNEL_RATING.isRegistered());
        assertTrue(NavigationItemFactory.CHANNEL_GRAPH.isRegistered());
        assertTrue(NavigationItemFactory.TIME_GRAPH.isRegistered());
    }

    @Test
    public void testIsRegisteredFalse() throws Exception {
        assertFalse(NavigationItemFactory.EXPORT.isRegistered());
        assertFalse(NavigationItemFactory.CHANNEL_AVAILABLE.isRegistered());
        assertFalse(NavigationItemFactory.VENDOR_LIST.isRegistered());
        assertFalse(NavigationItemFactory.SETTINGS.isRegistered());
        assertFalse(NavigationItemFactory.ABOUT.isRegistered());
    }

    @Test
    public void testActivityItem() throws Exception {
        assertEquals(SettingActivity.class, ((ActivityItem) NavigationItemFactory.SETTINGS).getActivity());
        assertEquals(AboutActivity.class, ((ActivityItem) NavigationItemFactory.ABOUT).getActivity());
    }

    @Test
    public void testExportItem() throws Exception {
        assertTrue(NavigationItemFactory.EXPORT instanceof ExportItem);
    }

}