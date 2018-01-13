/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.predicate;

import android.support.annotation.NonNull;

import com.isk.wifi.wifi.model.Strength;
import com.isk.wifi.wifi.model.WiFiDetail;

import org.apache.commons.collections4.Predicate;

class StrengthPredicate implements Predicate<WiFiDetail> {
    private final Strength strength;

    StrengthPredicate(@NonNull Strength strength) {
        this.strength = strength;
    }

    @Override
    public boolean evaluate(WiFiDetail object) {
        return object.getWiFiSignal().getStrength().equals(strength);
    }

}
