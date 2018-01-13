/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.channelgraph;

import android.support.v4.util.Pair;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.isk.wifi.MainContextHelper;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.settings.ThemeStyle;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.band.WiFiChannel;
import com.isk.wifi.wifi.graphutils.GraphConstants;
import com.isk.wifi.wifi.graphutils.GraphLegend;
import com.isk.wifi.wifi.graphutils.GraphViewWrapper;
import com.isk.wifi.wifi.model.SortBy;
import com.isk.wifi.wifi.model.WiFiConnection;
import com.isk.wifi.wifi.model.WiFiData;
import com.isk.wifi.wifi.model.WiFiDetail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class ChannelGraphViewTest {
    private Pair<WiFiChannel, WiFiChannel> wiFiChannelPair;
    private Settings settings;
    private GraphViewWrapper graphViewWrapper;
    private DataManager dataManager;
    private ChannelGraphView fixture;

    @Before
    public void setUp() {
        RobolectricUtil.INSTANCE.getActivity();

        graphViewWrapper = mock(GraphViewWrapper.class);
        dataManager = mock(DataManager.class);

        settings = MainContextHelper.INSTANCE.getSettings();

        wiFiChannelPair = new Pair<>(WiFiChannel.UNKNOWN, WiFiChannel.UNKNOWN);
        fixture = new ChannelGraphView(WiFiBand.GHZ2, wiFiChannelPair);
        fixture.setGraphViewWrapper(graphViewWrapper);
        fixture.setDataManager(dataManager);
    }

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testUpdate() throws Exception {
        // setup
        Set<WiFiDetail> newSeries = Collections.emptySet();
        List<WiFiDetail> wiFiDetails = Collections.emptyList();
        WiFiData wiFiData = new WiFiData(wiFiDetails, WiFiConnection.EMPTY, Collections.<String>emptyList());
        when(dataManager.getNewSeries(wiFiDetails, wiFiChannelPair)).thenReturn(newSeries);
        withSettings();
        // execute
        fixture.update(wiFiData);
        // validate
        verify(dataManager).getNewSeries(wiFiDetails, wiFiChannelPair);
        verify(dataManager).addSeriesData(graphViewWrapper, newSeries, GraphConstants.MAX_Y);
        verify(graphViewWrapper).removeSeries(newSeries);
        verify(graphViewWrapper).updateLegend(GraphLegend.RIGHT);
        verify(graphViewWrapper).setVisibility(View.VISIBLE);
        verifySettings();
    }

    private void verifySettings() {
        verify(settings).getSortBy();
        verify(settings, times(2)).getChannelGraphLegend();
        verify(settings, times(2)).getWiFiBand();
        verify(settings, times(2)).getGraphMaximumY();
        verify(settings).getThemeStyle();
    }

    private void withSettings() {
        when(settings.getChannelGraphLegend()).thenReturn(GraphLegend.RIGHT);
        when(settings.getSortBy()).thenReturn(SortBy.CHANNEL);
        when(settings.getWiFiBand()).thenReturn(WiFiBand.GHZ2);
        when(settings.getGraphMaximumY()).thenReturn(GraphConstants.MAX_Y);
        when(settings.getThemeStyle()).thenReturn(ThemeStyle.DARK);
    }

    @Test
    public void testGetGraphView() throws Exception {
        // setup
        GraphView expected = mock(GraphView.class);
        when(graphViewWrapper.getGraphView()).thenReturn(expected);
        // execute
        GraphView actual = fixture.getGraphView();
        // validate
        assertEquals(expected, actual);
        verify(graphViewWrapper).getGraphView();
    }
}