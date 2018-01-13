/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.band;

import android.support.annotation.NonNull;

import com.isk.wifi.R;

public enum WiFiBand {
    GHZ2(R.string.wifi_band_2ghz, new WiFiChannelsGHZ2()),
    GHZ5(R.string.wifi_band_5ghz, new WiFiChannelsGHZ5());

    private final int textResource;
    private final WiFiChannels wiFiChannels;

    WiFiBand(int textResource, @NonNull WiFiChannels wiFiChannels) {
        this.textResource = textResource;
        this.wiFiChannels = wiFiChannels;
    }

    public int getTextResource() {
        return textResource;
    }

    public WiFiBand toggle() {
        return isGHZ5() ? WiFiBand.GHZ2 : WiFiBand.GHZ5;
    }

    public boolean isGHZ5() {
        return WiFiBand.GHZ5.equals(this);
    }

    public WiFiChannels getWiFiChannels() {
        return wiFiChannels;
    }
}
