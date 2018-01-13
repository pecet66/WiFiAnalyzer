/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.isk.wifi.wifi.band.WiFiChannel;
import com.isk.wifi.wifi.band.WiFiChannels;

public class Configuration {
    public static final int SIZE_MIN = 1024;
    public static final int SIZE_MAX = 4096;

    private final boolean largeScreen;
    private int size;
    private Pair<WiFiChannel, WiFiChannel> wiFiChannelPair;

    public Configuration(boolean largeScreen) {
        this.largeScreen = largeScreen;
        setSize(SIZE_MAX);
        setWiFiChannelPair(WiFiChannels.UNKNOWN);
    }

    public boolean isLargeScreen() {
        return largeScreen;
    }

    public Pair<WiFiChannel, WiFiChannel> getWiFiChannelPair() {
        return wiFiChannelPair;
    }

    public void setWiFiChannelPair(@NonNull Pair<WiFiChannel, WiFiChannel> wiFiChannelPair) {
        this.wiFiChannelPair = wiFiChannelPair;
    }

    public boolean isSizeAvailable() {
        return size == SIZE_MAX;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
