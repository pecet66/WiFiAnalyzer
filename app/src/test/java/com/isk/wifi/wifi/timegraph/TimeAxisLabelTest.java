/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.timegraph;

import com.isk.wifi.wifi.graphutils.GraphConstants;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeAxisLabelTest {
    private TimeAxisLabel fixture;

    @Before
    public void setUp() {
        fixture = new TimeAxisLabel();
    }

    @Test
    public void testYAxis() throws Exception {
        assertEquals(StringUtils.EMPTY, fixture.formatLabel(GraphConstants.MIN_Y, false));
        assertEquals("-99", fixture.formatLabel(GraphConstants.MIN_Y + 1, false));
        assertEquals("0", fixture.formatLabel(GraphConstants.MAX_Y, false));
        assertEquals(StringUtils.EMPTY, fixture.formatLabel(GraphConstants.MAX_Y + 1, false));
    }

    @Test
    public void testXAxis() throws Exception {
        assertEquals(StringUtils.EMPTY, fixture.formatLabel(-2, true));
        assertEquals(StringUtils.EMPTY, fixture.formatLabel(-1, true));
        assertEquals(StringUtils.EMPTY, fixture.formatLabel(0, true));
        assertEquals(StringUtils.EMPTY, fixture.formatLabel(1, true));

        assertEquals("2", fixture.formatLabel(2, true));
        assertEquals("10", fixture.formatLabel(10, true));
    }

}