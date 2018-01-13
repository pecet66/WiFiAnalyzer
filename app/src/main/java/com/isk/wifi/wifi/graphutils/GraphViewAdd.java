/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.graphutils;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;

import org.apache.commons.collections4.Closure;

public class GraphViewAdd implements Closure<GraphView> {
    private final ViewGroup viewGroup;

    public GraphViewAdd(@NonNull ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override
    public void execute(GraphView graphView) {
        viewGroup.addView(graphView);
    }
}

