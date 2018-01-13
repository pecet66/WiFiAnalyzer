/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.timegraph;

import com.isk.util.EnumUtils;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.graphutils.GraphAdapter;
import com.isk.wifi.wifi.graphutils.GraphViewNotifier;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;

import java.util.ArrayList;
import java.util.List;

class TimeGraphAdapter extends GraphAdapter {
    TimeGraphAdapter() {
        super(makeGraphViewNotifiers());
    }

    private static List<GraphViewNotifier> makeGraphViewNotifiers() {
        return new ArrayList<>(CollectionUtils.collect(EnumUtils.values(WiFiBand.class), new ToGraphViewNotifier()));
    }

    private static class ToGraphViewNotifier implements Transformer<WiFiBand, GraphViewNotifier> {
        @Override
        public GraphViewNotifier transform(WiFiBand input) {
            return new TimeGraphView(input);
        }
    }

}
