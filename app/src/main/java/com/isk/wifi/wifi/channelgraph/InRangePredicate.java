/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.channelgraph;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.isk.wifi.wifi.band.WiFiChannel;
import com.isk.wifi.wifi.model.WiFiDetail;

import org.apache.commons.collections4.Predicate;

class InRangePredicate implements Predicate<WiFiDetail> {
    private final Pair<WiFiChannel, WiFiChannel> wiFiChannelPair;

    InRangePredicate(@NonNull Pair<WiFiChannel, WiFiChannel> wiFiChannelPair) {
        this.wiFiChannelPair = wiFiChannelPair;
    }

    @Override
    public boolean evaluate(WiFiDetail object) {
        int frequency = object.getWiFiSignal().getCenterFrequency();
        return frequency >= wiFiChannelPair.first.getFrequency() && frequency <= wiFiChannelPair.second.getFrequency();
    }
}
