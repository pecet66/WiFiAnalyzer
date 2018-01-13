/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.isk.wifi.MainActivity;
import com.isk.wifi.R;
import com.isk.wifi.navigation.items.NavigationItem;
import com.isk.wifi.navigation.items.NavigationItemFactory;
import com.isk.wifi.navigation.options.NavigationOption;
import com.isk.wifi.navigation.options.NavigationOptionFactory;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;

import java.util.List;

public enum NavigationMenu {
    ACCESS_POINTS(R.drawable.ic_network_wifi_grey_500_48dp, R.string.action_access_points, NavigationItemFactory.ACCESS_POINTS, NavigationOptionFactory.AP),
    CHANNEL_RATING(R.drawable.ic_wifi_tethering_grey_500_48dp, R.string.action_channel_rating, NavigationItemFactory.CHANNEL_RATING, NavigationOptionFactory.RATING);
    //CHANNEL_GRAPH(R.drawable.ic_insert_chart_grey_500_48dp, R.string.action_channel_graph, NavigationItemFactory.CHANNEL_GRAPH, NavigationOptionFactory.OTHER),
    //TIME_GRAPH(R.drawable.ic_show_chart_grey_500_48dp, R.string.action_time_graph, NavigationItemFactory.TIME_GRAPH, NavigationOptionFactory.OTHER),
    //EXPORT(R.drawable.ic_import_export_grey_500_48dp, R.string.action_export, NavigationItemFactory.EXPORT),
    //CHANNEL_AVAILABLE(R.drawable.ic_location_on_grey_500_48dp, R.string.action_channel_available, NavigationItemFactory.CHANNEL_AVAILABLE),
    //VENDOR_LIST(R.drawable.ic_list_grey_500_48dp, R.string.action_vendors, NavigationItemFactory.VENDOR_LIST),
    //SETTINGS(R.drawable.ic_settings_grey_500_48dp, R.string.action_settings, NavigationItemFactory.SETTINGS),
    //ABOUT(R.drawable.ic_info_outline_grey_500_48dp, R.string.action_about, NavigationItemFactory.ABOUT);

    private final int icon;
    private final int title;
    private final List<NavigationOption> navigationOptions;
    private final NavigationItem navigationItem;

    NavigationMenu(int icon, int title, @NonNull NavigationItem navigationItem, @NonNull List<NavigationOption> navigationOptions) {
        this.icon = icon;
        this.title = title;
        this.navigationItem = navigationItem;
        this.navigationOptions = navigationOptions;
    }

    NavigationMenu(int icon, int title, @NonNull NavigationItem navigationItem) {
        this(icon, title, navigationItem, NavigationOptionFactory.OFF);
    }

    public int getTitle() {
        return title;
    }

    public void activateNavigationMenu(@NonNull MainActivity mainActivity, @NonNull MenuItem menuItem) {
        navigationItem.activate(mainActivity, menuItem, this);
    }

    public void activateOptions(@NonNull MainActivity mainActivity) {
        IterableUtils.forEach(navigationOptions, new ActivateClosure(mainActivity));
    }

    public boolean isWiFiBandSwitchable() {
        return navigationOptions.contains(NavigationOptionFactory.WIFI_SWITCH_ON);
    }

    public boolean isRegistered() {
        return navigationItem.isRegistered();
    }

    int getIcon() {
        return icon;
    }

    NavigationItem getNavigationItem() {
        return navigationItem;
    }

    List<NavigationOption> getNavigationOptions() {
        return navigationOptions;
    }

    private class ActivateClosure implements Closure<NavigationOption> {
        private final MainActivity mainActivity;

        private ActivateClosure(@NonNull MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        public void execute(NavigationOption input) {
            input.apply(mainActivity);
        }
    }
}
