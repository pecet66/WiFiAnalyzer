/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import android.support.annotation.NonNull;

import com.isk.wifi.MainActivity;

class ScannerSwitchOn implements NavigationOption {

    @Override
    public void apply(@NonNull MainActivity mainActivity) {
        /*Menu menu = mainActivity.getOptionMenu().getMenu();
        if (menu != null) {
            MenuItem menuItem = menu.findItem(R.id.action_scanner);
            menuItem.setVisible(true);
            if (MainContext.INSTANCE.getScanner().isRunning()) {
                menuItem.setTitle(R.string.action_pause);
                menuItem.setIcon(R.drawable.ic_pause_grey_500_48dp);
            } else {
                menuItem.setTitle(R.string.action_resume);
                menuItem.setIcon(R.drawable.ic_play_arrow_grey_500_48dp);
            }
        }*/
    }

}
