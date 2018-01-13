/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.predicate;

import android.support.annotation.NonNull;

import com.isk.wifi.wifi.model.WiFiDetail;

import org.apache.commons.collections4.Predicate;

class SSIDPredicate implements Predicate<WiFiDetail> {
    private final String ssid;

    SSIDPredicate(@NonNull String ssid) {
        this.ssid = ssid;
    }

    @Override
    public boolean evaluate(WiFiDetail object) {
        return object.getSSID().contains(ssid);
    }
}
