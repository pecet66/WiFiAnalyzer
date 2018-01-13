/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.model;

import android.support.annotation.NonNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class WiFiAdditional {
    public static final WiFiAdditional EMPTY = new WiFiAdditional(StringUtils.EMPTY, false);

    private final String vendorName;
    private final boolean configuredNetwork;
    private final WiFiConnection wiFiConnection;

    private WiFiAdditional(@NonNull String vendorName, @NonNull WiFiConnection wiFiConnection, boolean configuredNetwork) {
        this.vendorName = vendorName;
        this.wiFiConnection = wiFiConnection;
        this.configuredNetwork = configuredNetwork;
    }

    public WiFiAdditional(@NonNull String vendorName, boolean configuredNetwork) {
        this(vendorName, WiFiConnection.EMPTY, configuredNetwork);
    }

    public WiFiAdditional(@NonNull String vendorName, @NonNull WiFiConnection wiFiConnection) {
        this(vendorName, wiFiConnection, true);
    }

    public String getVendorName() {
        return vendorName;
    }

    public WiFiConnection getWiFiConnection() {
        return wiFiConnection;
    }

    public boolean isConfiguredNetwork() {
        return configuredNetwork;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}