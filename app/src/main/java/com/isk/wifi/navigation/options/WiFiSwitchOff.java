/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;

import com.isk.wifi.MainActivity;

import org.apache.commons.lang3.StringUtils;

class WiFiSwitchOff implements NavigationOption {

    @Override
    public void apply(@NonNull MainActivity mainActivity) {
        ActionBar actionBar = mainActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(StringUtils.EMPTY);
        }
    }
}
