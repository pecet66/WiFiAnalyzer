/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;

import com.isk.util.TextUtils;
import com.isk.wifi.MainActivity;
import com.isk.wifi.MainContext;
import com.isk.wifi.R;
import com.isk.wifi.wifi.band.WiFiBand;

class WiFiSwitchOn implements NavigationOption {

    static final String SPACER = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

    @Override
    public void apply(@NonNull MainActivity mainActivity) {
        ActionBar actionBar = mainActivity.getSupportActionBar();
        if (actionBar != null) {
            int colorSelected = ContextCompat.getColor(mainActivity, R.color.connected);
            int colorNotSelected = ContextCompat.getColor(mainActivity, R.color.icons_color);
            Resources resources = mainActivity.getResources();
            String wiFiBand2 = resources.getString(WiFiBand.GHZ2.getTextResource());
            String wiFiBand5 = resources.getString(WiFiBand.GHZ5.getTextResource());
            WiFiBand wiFiBand = MainContext.INSTANCE.getSettings().getWiFiBand();
            String subtitle = makeSubtitle(WiFiBand.GHZ2.equals(wiFiBand), wiFiBand2, wiFiBand5, colorSelected, colorNotSelected);
            //actionBar.setSubtitle(TextUtils.fromHtml(subtitle));
        }
    }

    @NonNull
    String makeSubtitle(boolean wiFiBand2Selected, String wiFiBand2, String wiFiBand5, int colorSelected, int colorNotSelected) {
        StringBuilder stringBuilder = new StringBuilder();
        if (wiFiBand2Selected) {
            stringBuilder.append(TextUtils.textToHtml(wiFiBand2, colorSelected, false));
        } else {
            stringBuilder.append(TextUtils.textToHtml(wiFiBand2, colorNotSelected, true));
        }
        stringBuilder.append(SPACER);
        if (wiFiBand2Selected) {
            stringBuilder.append(TextUtils.textToHtml(wiFiBand5, colorNotSelected, true));
        } else {
            stringBuilder.append(TextUtils.textToHtml(wiFiBand5, colorSelected, false));
        }
        return stringBuilder.toString();
    }

}
