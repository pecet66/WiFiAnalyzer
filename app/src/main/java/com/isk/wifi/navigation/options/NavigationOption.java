/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import android.support.annotation.NonNull;

import com.isk.wifi.MainActivity;

public interface NavigationOption {
    void apply(@NonNull MainActivity mainActivity);
}
