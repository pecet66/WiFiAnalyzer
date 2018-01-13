/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.timegraph;

import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.isk.wifi.MainContextHelper;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.settings.ThemeStyle;
import com.isk.wifi.wifi.band.WiFiBand;
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
public class TimeGraphViewTest {
    private GraphViewWrapper graphViewWrapper;
    private DataManager dataManager;
    private Settings settings;
    private TimeGraphView fixture;

    @Before
    public void setUp() {
        RobolectricUtil.INSTANCE.getActivity();

        graphViewWrapper = mock(GraphViewWrapper.class);
        dataManager = mock(DataManager.class);

        settings = MainContextHelper.INSTANCE.getSettings();

        fixture = new TimeGraphView(WiFiBand.GHZ2);
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
        List<WiFiDetail> wiFiDetails = Collections.emptyList();
        Set<WiFiDetail> newSeries = Collections.emptySet();
        WiFiData wiFiData = new WiFiData(wiFiDetails, WiFiConnection.EMPTY, Collections.<String>emptyList());
        withSettings();
        when(dataManager.addSeriesData(graphViewWrapper, wiFiDetails, GraphConstants.MAX_Y)).thenReturn(newSeries);
        // execute
        fixture.update(wiFiData);
        // validate
        verify(graphViewWrapper).removeSeries(newSeries);
        verify(graphViewWrapper).updateLegend(GraphLegend.LEFT);
        verify(graphViewWrapper).setVisibility(View.VISIBLE);
        verifySettings();
    }

    private void verifySettings() {
        verify(settings).getSortBy();
        verify(settings, times(2)).getTimeGraphLegend();
        verify(settings, times(2)).getWiFiBand();
        verify(settings, times(2)).getGraphMaximumY();
        verify(settings).getThemeStyle();
    }

    private void withSettings() {
        when(settings.getSortBy()).thenReturn(SortBy.SSID);
        when(settings.getTimeGraphLegend()).thenReturn(GraphLegend.LEFT);
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