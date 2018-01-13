/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.timegraph;

import com.jjoe64.graphview.GraphView;
import com.isk.wifi.MainContextHelper;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.graphutils.GraphViewNotifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class TimeGraphAdapterTest {

    private TimeGraphAdapter fixture;

    @Before
    public void setUp() {
        RobolectricUtil.INSTANCE.getActivity();

        fixture = new TimeGraphAdapter();
    }

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testGetGraphViewNotifiers() throws Exception {
        // execute
        List<GraphViewNotifier> graphViewNotifiers = fixture.getGraphViewNotifiers();
        // validate
        assertEquals(WiFiBand.values().length, graphViewNotifiers.size());
    }

    @Test
    public void testGetGraphViews() throws Exception {
        // execute
        List<GraphView> graphViews = fixture.getGraphViews();
        // validate
        assertEquals(WiFiBand.values().length, graphViews.size());
    }

}