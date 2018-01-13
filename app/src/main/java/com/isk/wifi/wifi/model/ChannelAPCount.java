/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.model;

import android.support.annotation.NonNull;

import com.isk.wifi.wifi.band.WiFiChannel;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ChannelAPCount {
    private final WiFiChannel wiFiChannel;
    private final int count;

    public ChannelAPCount(@NonNull WiFiChannel wiFiChannel, int count) {
        this.wiFiChannel = wiFiChannel;
        this.count = count;
    }

    public WiFiChannel getWiFiChannel() {
        return wiFiChannel;
    }

    int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
