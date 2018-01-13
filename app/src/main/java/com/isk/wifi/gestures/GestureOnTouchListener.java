/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.gestures;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import static android.view.GestureDetector.OnGestureListener;
import static android.view.View.OnTouchListener;

public class GestureOnTouchListener implements OnTouchListener {
    private final GestureDetector gestureDetector;

    public GestureOnTouchListener(@NonNull Context context, @NonNull OnGestureListener onGestureListener) {
        this.gestureDetector = new GestureDetector(context, onGestureListener);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}