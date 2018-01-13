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

public class NavigationItemFactory {
    public static final NavigationItem ACCESS_POINTS = new FragmentItem(new AccessPointsFragment(), true);
    public static final NavigationItem CHANNEL_RATING = new FragmentItem(new ChannelRatingFragment(), true);
    public static final NavigationItem CHANNEL_GRAPH = new FragmentItem(new ChannelGraphFragment(), true);
    public static final NavigationItem TIME_GRAPH = new FragmentItem(new TimeGraphFragment(), true);
    public static final NavigationItem EXPORT = new ExportItem();
    public static final NavigationItem CHANNEL_AVAILABLE = new FragmentItem(new ChannelAvailableFragment());
    public static final NavigationItem VENDOR_LIST = new FragmentItem(new VendorFragment());
    public static final NavigationItem SETTINGS = new ActivityItem(SettingActivity.class);
    public static final NavigationItem ABOUT = new ActivityItem(AboutActivity.class);

    private NavigationItemFactory() {
        throw new IllegalStateException("Utility class");
    }
}
