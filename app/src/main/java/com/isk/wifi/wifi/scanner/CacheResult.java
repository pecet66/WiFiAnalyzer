/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.scanner;

import android.net.wifi.ScanResult;
import android.support.annotation.NonNull;

class CacheResult {
    private final ScanResult scanResult;
    private final int levelAverage;

    CacheResult(@NonNull ScanResult scanResult, int levelAverage) {
        this.scanResult = scanResult;
        this.levelAverage = levelAverage;
    }

    ScanResult getScanResult() {
        return scanResult;
    }

    int getLevelAverage() {
        return levelAverage;
    }
}
