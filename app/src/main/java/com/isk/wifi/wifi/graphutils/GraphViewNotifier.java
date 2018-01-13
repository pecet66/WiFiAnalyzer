/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.graphutils;

import android.support.annotation.NonNull;

import com.jjoe64.graphview.GraphView;
import com.isk.wifi.wifi.model.WiFiData;

public interface GraphViewNotifier {
    GraphView getGraphView();

    void update(@NonNull WiFiData wiFiData);
}
