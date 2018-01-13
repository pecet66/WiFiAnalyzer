/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.graphutils;

import com.jjoe64.graphview.series.DataPoint;

import org.mockito.ArgumentMatcher;

public class DataPointsEquals implements ArgumentMatcher<DataPoint[]> {

    private final DataPoint[] expected;

    public DataPointsEquals(DataPoint[] expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(DataPoint[] argument) {
        boolean result = expected.length == argument.length;
        if (result) {
            for (int i = 0; i < argument.length; i++) {
                result = expected[i].getX() == argument[i].getX() && expected[i].getY() == argument[i].getY();
                if (!result) {
                    break;
                }
            }
        }
        return result;
    }
}
