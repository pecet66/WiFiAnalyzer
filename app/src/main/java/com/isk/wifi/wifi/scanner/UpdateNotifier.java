/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.scanner;

import android.support.annotation.NonNull;

import com.isk.wifi.wifi.model.WiFiData;

public interface UpdateNotifier {
    void update(@NonNull WiFiData wiFiData);
}
