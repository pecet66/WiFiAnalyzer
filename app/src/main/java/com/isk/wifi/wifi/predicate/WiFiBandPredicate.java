/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.predicate;

import android.support.annotation.NonNull;

import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.model.WiFiDetail;

import org.apache.commons.collections4.Predicate;

public class WiFiBandPredicate implements Predicate<WiFiDetail> {
    private final WiFiBand wiFiBand;

    public WiFiBandPredicate(@NonNull WiFiBand wiFiBand) {
        this.wiFiBand = wiFiBand;
    }

    @Override
    public boolean evaluate(WiFiDetail object) {
        return object.getWiFiSignal().getWiFiBand().equals(wiFiBand);
    }

}
