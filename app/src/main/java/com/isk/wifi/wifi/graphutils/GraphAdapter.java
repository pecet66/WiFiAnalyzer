/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.graphutils;

import android.support.annotation.NonNull;

import com.jjoe64.graphview.GraphView;
import com.isk.wifi.wifi.model.WiFiData;
import com.isk.wifi.wifi.scanner.UpdateNotifier;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Transformer;

import java.util.ArrayList;
import java.util.List;

public class GraphAdapter implements UpdateNotifier {
    private final List<GraphViewNotifier> graphViewNotifiers;

    public GraphAdapter(@NonNull List<GraphViewNotifier> graphViewNotifiers) {
        this.graphViewNotifiers = graphViewNotifiers;
    }

    public List<GraphView> getGraphViews() {
        return new ArrayList<>(CollectionUtils.collect(graphViewNotifiers, new ToGraphView()));
    }

    @Override
    public void update(@NonNull WiFiData wiFiData) {
        IterableUtils.forEach(graphViewNotifiers, new UpdateClosure(wiFiData));
    }

    public List<GraphViewNotifier> getGraphViewNotifiers() {
        return graphViewNotifiers;
    }

    private class UpdateClosure implements Closure<GraphViewNotifier> {
        private final WiFiData wiFiData;

        private UpdateClosure(@NonNull WiFiData wiFiData) {
            this.wiFiData = wiFiData;
        }

        @Override
        public void execute(GraphViewNotifier graphViewNotifier) {
            graphViewNotifier.update(wiFiData);
        }
    }

    private class ToGraphView implements Transformer<GraphViewNotifier, GraphView> {
        @Override
        public GraphView transform(GraphViewNotifier input) {
            return input.getGraphView();
        }
    }
}
