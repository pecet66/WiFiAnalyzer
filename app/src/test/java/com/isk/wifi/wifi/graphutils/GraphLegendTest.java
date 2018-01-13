/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.graphutils;

import com.jjoe64.graphview.LegendRenderer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GraphLegendTest {
    @Mock
    private LegendRenderer legendRenderer;

    @Test
    public void testSortByNumber() throws Exception {
        assertEquals(3, GraphLegend.values().length);
    }

    @Test
    public void testGetDisplay() throws Exception {
        assertTrue(GraphLegend.HIDE.getDisplay() instanceof GraphLegend.DisplayNone);
        assertTrue(GraphLegend.LEFT.getDisplay() instanceof GraphLegend.DisplayLeft);
        assertTrue(GraphLegend.RIGHT.getDisplay() instanceof GraphLegend.DisplayRight);
    }

    @Test
    public void testDisplayHide() throws Exception {
        // execute
        GraphLegend.HIDE.display(legendRenderer);
        // validate
        verify(legendRenderer).setVisible(false);
    }

    @Test
    public void testDisplayLeft() throws Exception {
        // execute
        GraphLegend.LEFT.display(legendRenderer);
        // validate
        verify(legendRenderer).setVisible(true);
        verify(legendRenderer).setFixedPosition(0, 0);
    }

    @Test
    public void testDisplayRight() throws Exception {
        // execute
        GraphLegend.RIGHT.display(legendRenderer);
        // validate
        verify(legendRenderer).setVisible(true);
        verify(legendRenderer).setAlign(LegendRenderer.LegendAlign.TOP);
    }

}