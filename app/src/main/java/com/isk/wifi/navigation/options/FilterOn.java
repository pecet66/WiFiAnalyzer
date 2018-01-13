/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import android.support.annotation.NonNull;

import com.isk.wifi.MainActivity;

class FilterOn implements NavigationOption {

    @Override
    public void apply(@NonNull MainActivity mainActivity) {
        /*Menu menu = mainActivity.getOptionMenu().getMenu();
        if (menu != null) {
            MenuItem menuItem = menu.findItem(R.id.action_filter);
            menuItem.setVisible(true);
            menuItem.setIcon(MainContext.INSTANCE.getFilterAdapter().isActive()
                ? R.drawable.ic_filter_list_blue_500_48dp
                : R.drawable.ic_filter_list_grey_500_48dp);
        }*/
    }

}
