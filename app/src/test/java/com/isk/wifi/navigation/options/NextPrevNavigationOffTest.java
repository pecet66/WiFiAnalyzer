/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import android.view.View;

import com.isk.wifi.MainActivity;
import com.isk.wifi.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NextPrevNavigationOffTest {
    @Mock
    private MainActivity mainActivity;
    @Mock
    private View view;

    @Test
    public void testApplySwitchesOffOnTouchListener() throws Exception {
        // setup
        NextPrevNavigationOff fixture = new NextPrevNavigationOff();
        when(mainActivity.findViewById(R.id.main_fragment_layout)).thenReturn(view);
        // execute
        fixture.apply(mainActivity);
        // validate
        verify(mainActivity).findViewById(R.id.main_fragment_layout);
        verify(view).setOnTouchListener(NextPrevNavigationOff.ON_TOUCH_LISTENER_EMPTY);
    }


    @Test
    public void testOnTouchListenerEmptyDoesNotDoAnyEvents() throws Exception {
        assertFalse(NextPrevNavigationOff.ON_TOUCH_LISTENER_EMPTY.onTouch(null, null));
    }
}