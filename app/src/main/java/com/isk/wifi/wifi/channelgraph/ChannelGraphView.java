/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.channelgraph;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.TitleLineGraphSeries;
import com.isk.wifi.Configuration;
import com.isk.wifi.MainContext;
import com.isk.wifi.R;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.band.WiFiChannel;
import com.isk.wifi.wifi.band.WiFiChannels;
import com.isk.wifi.wifi.graphutils.GraphColor;
import com.isk.wifi.wifi.graphutils.GraphConstants;
import com.isk.wifi.wifi.graphutils.GraphViewBuilder;
import com.isk.wifi.wifi.graphutils.GraphViewNotifier;
import com.isk.wifi.wifi.graphutils.GraphViewWrapper;
import com.isk.wifi.wifi.model.WiFiData;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.predicate.FilterPredicate;

import org.apache.commons.collections4.Predicate;

import java.util.List;
import java.util.Set;

class ChannelGraphView implements GraphViewNotifier {
    private final WiFiBand wiFiBand;
    private final Pair<WiFiChannel, WiFiChannel> wiFiChannelPair;
    private GraphViewWrapper graphViewWrapper;
    private DataManager dataManager;

    ChannelGraphView(@NonNull WiFiBand wiFiBand, @NonNull Pair<WiFiChannel, WiFiChannel> wiFiChannelPair) {
        this.wiFiBand = wiFiBand;
        this.wiFiChannelPair = wiFiChannelPair;
        this.graphViewWrapper = makeGraphViewWrapper();
        this.dataManager = new DataManager();
    }

    @Override
    public void update(@NonNull WiFiData wiFiData) {
        Settings settings = MainContext.INSTANCE.getSettings();
        Predicate<WiFiDetail> predicate = FilterPredicate.makeOtherPredicate(settings);
        List<WiFiDetail> wiFiDetails = wiFiData.getWiFiDetails(predicate, settings.getSortBy());
        Set<WiFiDetail> newSeries = dataManager.getNewSeries(wiFiDetails, wiFiChannelPair);
        dataManager.addSeriesData(graphViewWrapper, newSeries, settings.getGraphMaximumY());
        graphViewWrapper.removeSeries(newSeries);
        graphViewWrapper.updateLegend(settings.getChannelGraphLegend());
        graphViewWrapper.setVisibility(isSelected() ? View.VISIBLE : View.GONE);
    }

    private boolean isSelected() {
        Settings settings = MainContext.INSTANCE.getSettings();
        WiFiBand currentWiFiBand = settings.getWiFiBand();
        Configuration configuration = MainContext.INSTANCE.getConfiguration();
        Pair<WiFiChannel, WiFiChannel> currentWiFiChannelPair = configuration.getWiFiChannelPair();
        return wiFiBand.equals(currentWiFiBand) && (WiFiBand.GHZ2.equals(wiFiBand) || wiFiChannelPair.equals(currentWiFiChannelPair));
    }

    @Override
    public GraphView getGraphView() {
        return graphViewWrapper.getGraphView();
    }

    private int getNumX() {
        int channelFirst = wiFiChannelPair.first.getChannel() - WiFiChannels.CHANNEL_OFFSET;
        int channelLast = wiFiChannelPair.second.getChannel() + WiFiChannels.CHANNEL_OFFSET;
        return Math.min(GraphConstants.NUM_X_CHANNEL, channelLast - channelFirst + 1);
    }

    private GraphView makeGraphView(@NonNull MainContext mainContext, @NonNull Settings settings) {
        Resources resources = mainContext.getResources();
        return new GraphViewBuilder(mainContext.getContext(), getNumX(), settings.getGraphMaximumY(), settings.getThemeStyle())
            .setLabelFormatter(new ChannelAxisLabel(wiFiBand, wiFiChannelPair))
            .setVerticalTitle(resources.getString(R.string.graph_axis_y))
            .setHorizontalTitle(resources.getString(R.string.graph_channel_axis_x))
            .build();
    }

    private GraphViewWrapper makeGraphViewWrapper() {
        MainContext mainContext = MainContext.INSTANCE;
        Settings settings = mainContext.getSettings();
        Configuration configuration = mainContext.getConfiguration();
        GraphView graphView = makeGraphView(mainContext, settings);
        graphViewWrapper = new GraphViewWrapper(graphView, settings.getChannelGraphLegend());
        configuration.setSize(graphViewWrapper.getSize(graphViewWrapper.calculateGraphType()));
        int minX = wiFiChannelPair.first.getFrequency() - WiFiChannels.FREQUENCY_OFFSET;
        int maxX = minX + (graphViewWrapper.getViewportCntX() * WiFiChannels.FREQUENCY_SPREAD);
        graphViewWrapper.setViewport(minX, maxX);
        graphViewWrapper.addSeries(makeDefaultSeries(wiFiChannelPair.second.getFrequency(), minX));
        return graphViewWrapper;
    }

    private TitleLineGraphSeries<DataPoint> makeDefaultSeries(int frequencyEnd, int minX) {
        DataPoint[] dataPoints = new DataPoint[]{
            new DataPoint(minX, GraphConstants.MIN_Y),
            new DataPoint(frequencyEnd + WiFiChannels.FREQUENCY_OFFSET, GraphConstants.MIN_Y)
        };

        TitleLineGraphSeries<DataPoint> series = new TitleLineGraphSeries<>(dataPoints);
        series.setColor((int) GraphColor.TRANSPARENT.getPrimary());
        series.setThickness(GraphConstants.THICKNESS_INVISIBLE);
        return series;
    }

    void setGraphViewWrapper(@NonNull GraphViewWrapper graphViewWrapper) {
        this.graphViewWrapper = graphViewWrapper;
    }

    void setDataManager(@NonNull DataManager dataManager) {
        this.dataManager = dataManager;
    }

}
