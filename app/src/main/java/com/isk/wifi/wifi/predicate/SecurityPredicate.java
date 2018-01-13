/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.predicate;

import android.support.annotation.NonNull;

import com.isk.wifi.wifi.model.Security;
import com.isk.wifi.wifi.model.WiFiDetail;

import org.apache.commons.collections4.Predicate;

class SecurityPredicate implements Predicate<WiFiDetail> {
    private final Security security;

    SecurityPredicate(@NonNull Security security) {
        this.security = security;
    }

    @Override
    public boolean evaluate(WiFiDetail object) {
        return object.getSecurity().equals(security);
    }
}
