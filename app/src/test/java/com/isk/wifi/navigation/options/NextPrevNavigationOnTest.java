/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import android.view.View;

import com.isk.wifi.MainActivity;
import com.isk.wifi.R;
import com.isk.wifi.navigation.NavigationSwipeOnTouchListener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NextPrevNavigationOnTest {
    @Mock
    private MainActivity mainActivity;
    @Mock
    private View view;

    @Test
    public void testApplySetsSwipeOnTouchListener() throws Exception {
        // setup
        NextPrevNavigationOn fixture = new NextPrevNavigationOn();
        when(mainActivity.findViewById(R.id.main_fragment_layout)).thenReturn(view);
        // execute
        fixture.apply(mainActivity);
        // validate
        verify(mainActivity).findViewById(R.id.main_fragment_layout);
        verify(view).setOnTouchListener(any(NavigationSwipeOnTouchListener.class));
    }

}