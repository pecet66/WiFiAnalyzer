/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation;

import android.support.annotation.NonNull;

import com.isk.wifi.MainActivity;
import com.isk.wifi.gestures.GestureOnTouchListener;
import com.isk.wifi.gestures.SwipeOnGestureListener;

public class NavigationSwipeOnTouchListener extends GestureOnTouchListener {

    public NavigationSwipeOnTouchListener(@NonNull MainActivity mainActivity) {
        super(mainActivity, new SwipeOnGestureListener(new NavigationSwipe(mainActivity)));
    }
}
