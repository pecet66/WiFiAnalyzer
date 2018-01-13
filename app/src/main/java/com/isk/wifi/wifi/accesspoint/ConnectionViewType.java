/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.accesspoint;

public enum ConnectionViewType {
    COMPLETE(AccessPointViewType.COMPLETE),
    COMPACT(AccessPointViewType.COMPACT),
    HIDE(null);

    private final AccessPointViewType accessPointViewType;

    ConnectionViewType(AccessPointViewType accessPointViewType) {
        this.accessPointViewType = accessPointViewType;
    }

    AccessPointViewType getAccessPointViewType() {
        return accessPointViewType;
    }

    public boolean isHide() {
        return HIDE.equals(this);
    }
}
