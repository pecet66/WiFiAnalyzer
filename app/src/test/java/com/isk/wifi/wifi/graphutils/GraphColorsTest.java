/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.graphutils;

import android.content.Context;
import android.content.res.Resources;

import com.isk.wifi.MainActivity;
import com.isk.wifi.MainContextHelper;
import com.isk.wifi.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GraphColorsTest {
    private final String[] colors = new String[]{"#FB1554", "#33FB1554", "#74FF89", "#3374FF89", "#8B1EFC", "#338B1EFC"};
    private final GraphColor[] graphColors = new GraphColor[]{
        new GraphColor(0xFB1554, 0x33FB1554),
        new GraphColor(0x74FF89, 0x3374FF89),
        new GraphColor(0x8B1EFC, 0x338B1EFC)
    };

    @Mock
    private Resources resources;
    @Mock
    private Context context;

    private GraphColors fixture;

    @Before
    public void setUp() {
        MainActivity mainActivity = MainContextHelper.INSTANCE.getMainActivity();
        when(mainActivity.getApplicationContext()).thenReturn(context);
        when(context.getResources()).thenReturn(resources);
        when(resources.getStringArray(R.array.graph_colors)).thenReturn(colors);

        fixture = new GraphColors();
    }

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testGetColorStartsOverWhenEndIsReached() throws Exception {
        assertEquals(graphColors[2], fixture.getColor());
        assertEquals(graphColors[1], fixture.getColor());
        assertEquals(graphColors[0], fixture.getColor());
        assertEquals(graphColors[2], fixture.getColor());
    }

    @Test
    public void testAddColorAddsColorToAvailablePool() throws Exception {
        GraphColor expected = graphColors[2];
        assertEquals(expected, fixture.getColor());
        fixture.addColor(expected.getPrimary());
        assertEquals(expected, fixture.getColor());
    }

    @Test
    public void testAddColorDoesNotAddNonExistingColor() throws Exception {
        GraphColor expected = graphColors[1];
        GraphColor graphColor = graphColors[2];
        assertEquals(graphColor, fixture.getColor());
        fixture.addColor(graphColor.getBackground());
        assertEquals(expected, fixture.getColor());
    }
}
