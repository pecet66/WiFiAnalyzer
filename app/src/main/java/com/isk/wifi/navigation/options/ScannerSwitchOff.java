/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import android.support.annotation.NonNull;

import com.isk.wifi.MainActivity;

class ScannerSwitchOff implements NavigationOption {

    @Override
    public void apply(@NonNull MainActivity mainActivity) {
        /*Menu menu = mainActivity.getOptionMenu().getMenu();
        if (menu != null) {
            menu.findItem(R.id.action_scanner).setVisible(false);
        }*/
    }
}
