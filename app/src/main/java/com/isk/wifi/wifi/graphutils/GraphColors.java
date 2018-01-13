/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.graphutils;

import android.content.res.Resources;

import com.isk.wifi.MainContext;
import com.isk.wifi.R;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class GraphColors {
    private final List<GraphColor> availableGraphColors;
    private final Deque<GraphColor> currentGraphColors;

    GraphColors() {
        currentGraphColors = new ArrayDeque<>();
        availableGraphColors = new ArrayList<>();
    }

    private List<GraphColor> getAvailableGraphColors() {
        if (availableGraphColors.isEmpty()) {
            Resources resources = MainContext.INSTANCE.getResources();
            String[] colorsAsStrings = resources.getStringArray(R.array.graph_colors);
            for (int i = 0; i < colorsAsStrings.length; i += 2) {
                GraphColor graphColor = new GraphColor(Long.parseLong(colorsAsStrings[i].substring(1), 16), Long.parseLong(colorsAsStrings[i + 1].substring(1), 16));
                availableGraphColors.add(graphColor);
            }
        }
        return availableGraphColors;
    }

    GraphColor getColor() {
        if (currentGraphColors.isEmpty()) {
            for (GraphColor graphColor : getAvailableGraphColors()) {
                currentGraphColors.push(graphColor);
            }
        }
        return currentGraphColors.pop();
    }

    void addColor(long primaryColor) {
        GraphColor graphColor = findColor(primaryColor);
        if (graphColor == null || currentGraphColors.contains(graphColor)) {
            return;
        }
        currentGraphColors.push(graphColor);
    }

    private GraphColor findColor(long primaryColor) {
        return IterableUtils.find(getAvailableGraphColors(), new ColorPredicate(primaryColor));
    }

    private class ColorPredicate implements Predicate<GraphColor> {
        private final long primaryColor;

        private ColorPredicate(long primaryColor) {
            this.primaryColor = primaryColor;
        }

        @Override
        public boolean evaluate(GraphColor graphColor) {
            return primaryColor == graphColor.getPrimary();
        }
    }

}
