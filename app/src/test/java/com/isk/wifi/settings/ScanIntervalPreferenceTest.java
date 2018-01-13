/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.settings;

import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;

import com.isk.wifi.MainActivity;
import com.isk.wifi.R;
import com.isk.wifi.RobolectricUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class ScanIntervalPreferenceTest {

    private MainActivity mainActivity;
    private ScanIntervalPreference fixture;
    private int valueDefault;

    @Before
    public void setUp() {
        mainActivity = RobolectricUtil.INSTANCE.getActivity();
        fixture = new ScanIntervalPreference(mainActivity, Robolectric.buildAttributeSet().build());
        valueDefault = mainActivity.getResources().getInteger(R.integer.scan_interval_default);
    }

    @Test
    public void testDialogBehaviour() throws Exception {
        // setup
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.scan_interval_dialog, null);
        // execute
        fixture.onBindDialogView(view);
        fixture.onDialogClosed(true);
        // validate
        assertEquals(0, fixture.getValue());
        assertNull(fixture.getSummary());
    }

    @Test
    public void testOnSetInitialValue() throws Exception {
        // setup
        int otherValue = -valueDefault;
        // execute
        fixture.onSetInitialValue(true, otherValue);
        // validate
        assertEquals(valueDefault, fixture.getValue());
    }

    @Test
    public void testOnSetInitialValueWithOtherValue() throws Exception {
        // setup
        int otherValue = -valueDefault;
        // execute
        fixture.onSetInitialValue(false, otherValue);
        // validate
        assertEquals(otherValue, fixture.getValue());
    }

    @Test
    public void testOnGetDefaultValue() throws Exception {
        // setup
        TypedArray typedArray = mock(TypedArray.class);
        when(typedArray.getInteger(0, valueDefault)).thenReturn(valueDefault);
        // execute
        Object actual = fixture.onGetDefaultValue(typedArray, 0);
        // validate
        assertEquals(valueDefault, actual);
    }

    @Test
    public void testGetSummary() throws Exception {
        assertNull(fixture.getSummary());
    }

    @Test
    public void testSetSummary() throws Exception {
        // setup
        String summary = "This is Summary";
        // execute
        fixture.setSummary(summary);
        // validate
        assertNull(fixture.getSummary());
    }
}