/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.graphutils;

import android.support.annotation.NonNull;

import com.jjoe64.graphview.series.DataPoint;

import org.mockito.ArgumentMatcher;

public class DataPointEquals implements ArgumentMatcher<DataPoint> {

    private final DataPoint expected;

    public DataPointEquals(@NonNull DataPoint expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(DataPoint argument) {
        return expected.getX() == argument.getX() && expected.getY() == argument.getY();
    }
}
