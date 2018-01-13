/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.graphutils;

import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GraphViewAddTest {

    @Mock
    private GraphView graphView;
    @Mock
    private ViewGroup viewGroup;

    @Test
    public void testSetGraphView() throws Exception {
        // setup
        GraphViewAdd fixture = new GraphViewAdd(viewGroup);
        // execute
        fixture.execute(graphView);
        // validate
        verify(viewGroup).addView(graphView);
    }

}