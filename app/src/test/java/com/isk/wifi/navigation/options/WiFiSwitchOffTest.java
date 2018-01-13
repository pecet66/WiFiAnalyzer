/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import android.support.v7.app.ActionBar;

import com.isk.wifi.MainActivity;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WiFiSwitchOffTest {

    @Mock
    private MainActivity mainActivity;
    @Mock
    private ActionBar actionBar;

    private WiFiSwitchOff fixture;

    @Before
    public void setUp() {
        fixture = new WiFiSwitchOff();
    }

    @Test
    public void testApplySetEmptySubtitle() throws Exception {
        // setup
        when(mainActivity.getSupportActionBar()).thenReturn(actionBar);
        // execute
        fixture.apply(mainActivity);
        // validate
        verify(mainActivity).getSupportActionBar();
        verify(actionBar).setSubtitle(StringUtils.EMPTY);
    }

    @Test
    public void testApplyWithNoActionBarDoesNotSetSubtitle() throws Exception {
        // setup
        when(mainActivity.getSupportActionBar()).thenReturn(null);
        // execute
        fixture.apply(mainActivity);
        // validate
        verify(mainActivity).getSupportActionBar();
        verify(actionBar, never()).setSubtitle(anyString());
    }

}