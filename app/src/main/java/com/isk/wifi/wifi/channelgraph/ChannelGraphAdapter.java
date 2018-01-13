/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.channelgraph;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.isk.util.EnumUtils;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.band.WiFiChannel;
import com.isk.wifi.wifi.graphutils.GraphAdapter;
import com.isk.wifi.wifi.graphutils.GraphViewNotifier;
import com.isk.wifi.wifi.model.WiFiData;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;

import java.util.ArrayList;
import java.util.List;

class ChannelGraphAdapter extends GraphAdapter {
    private final ChannelGraphNavigation channelGraphNavigation;

    ChannelGraphAdapter(@NonNull ChannelGraphNavigation channelGraphNavigation) {
        super(makeGraphViewNotifiers());
        this.channelGraphNavigation = channelGraphNavigation;
    }

    private static List<GraphViewNotifier> makeGraphViewNotifiers() {
        List<GraphViewNotifier> graphViewNotifiers = new ArrayList<>();
        IterableUtils.forEach(EnumUtils.values(WiFiBand.class), new WiFiBandClosure(graphViewNotifiers));
        return graphViewNotifiers;
    }

    @Override
    public void update(@NonNull WiFiData wiFiData) {
        super.update(wiFiData);
        channelGraphNavigation.update(wiFiData);
    }

    private static class WiFiBandClosure implements Closure<WiFiBand> {
        private final List<GraphViewNotifier> graphViewNotifiers;

        private WiFiBandClosure(@NonNull List<GraphViewNotifier> graphViewNotifiers) {
            this.graphViewNotifiers = graphViewNotifiers;
        }

        @Override
        public void execute(WiFiBand wiFiBand) {
            IterableUtils.forEach(wiFiBand.getWiFiChannels().getWiFiChannelPairs(), new WiFiChannelClosure(graphViewNotifiers, wiFiBand));
        }
    }

    private static class WiFiChannelClosure implements Closure<Pair<WiFiChannel, WiFiChannel>> {
        private final List<GraphViewNotifier> graphViewNotifiers;
        private final WiFiBand wiFiBand;

        private WiFiChannelClosure(@NonNull List<GraphViewNotifier> graphViewNotifiers, @NonNull WiFiBand wiFiBand) {
            this.graphViewNotifiers = graphViewNotifiers;
            this.wiFiBand = wiFiBand;
        }

        @Override
        public void execute(Pair<WiFiChannel, WiFiChannel> wiFiChannelPair) {
            graphViewNotifiers.add(new ChannelGraphView(wiFiBand, wiFiChannelPair));
        }
    }
}
