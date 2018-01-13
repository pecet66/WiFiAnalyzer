/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.accesspoint;

import com.isk.wifi.R;

public enum AccessPointViewType {
    COMPLETE(R.layout.access_point_view_complete),
    COMPACT(R.layout.access_point_view_compact);

    private final int layout;

    AccessPointViewType(int layout) {
        this.layout = layout;
    }

    int getLayout() {
        return layout;
    }

    boolean isCompact() {
        return COMPACT.equals(this);
    }

    boolean isFull() {
        return COMPLETE.equals(this);
    }
}
