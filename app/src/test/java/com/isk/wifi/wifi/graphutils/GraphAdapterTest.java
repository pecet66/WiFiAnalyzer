/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.graphutils;

import com.jjoe64.graphview.GraphView;
import com.isk.wifi.MainContextHelper;
import com.isk.wifi.wifi.model.WiFiData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GraphAdapterTest {
    @Mock
    private GraphViewNotifier graphViewNotifier;
    @Mock
    private GraphView graphView;
    @Mock
    private WiFiData wifiData;

    private GraphAdapter fixture;

    @Before
    public void setUp() {
        fixture = new GraphAdapter(Collections.singletonList(graphViewNotifier));
    }

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testUpdate() throws Exception {
        // execute
        fixture.update(wifiData);
        // validate
        verify(graphViewNotifier).update(wifiData);
    }

    @Test
    public void testGetGraphViews() throws Exception {
        // setup
        when(graphViewNotifier.getGraphView()).thenReturn(graphView);
        // execute
        List<GraphView> actual = fixture.getGraphViews();
        // validate
        assertEquals(1, actual.size());
        assertEquals(graphView, actual.get(0));
        verify(graphViewNotifier).getGraphView();
    }

}