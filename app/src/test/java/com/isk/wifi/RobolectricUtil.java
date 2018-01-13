/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi;

import org.robolectric.Robolectric;

public enum RobolectricUtil {
    INSTANCE;

    private final MainActivity mainActivity;

    RobolectricUtil() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    public MainActivity getActivity() {
        return mainActivity;
    }
}