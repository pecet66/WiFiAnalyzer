/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation;

import android.support.annotation.NonNull;

import com.isk.wifi.MainActivity;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.gestures.SwipeDirection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class NavigationSwipeTest {
    private MainActivity mainActivity;
    private NavigationSwipe fixture;

    @Before
    public void setUp() {
        mainActivity = RobolectricUtil.INSTANCE.getActivity();
        fixture = new NavigationSwipe(mainActivity);
    }

    @Test
    public void testSwipe() throws Exception {
        // swipe left expect channel rating
        fixture.swipe(SwipeDirection.LEFT);
        validateSwipeRight(NavigationMenu.CHANNEL_RATING);
        // swipe right expect access point
        fixture.swipe(SwipeDirection.RIGHT);
        validateSwipeRight(NavigationMenu.ACCESS_POINTS);
    }

    private void validateSwipeRight(@NonNull NavigationMenu expected) {
        assertEquals(expected, mainActivity.getNavigationMenuView().getCurrentNavigationMenu());
        assertEquals(expected.ordinal(), mainActivity.getNavigationMenuView().getCurrentMenuItem().getItemId());
    }
}