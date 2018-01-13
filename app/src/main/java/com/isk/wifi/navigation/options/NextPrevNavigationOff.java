/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.isk.wifi.MainActivity;
import com.isk.wifi.R;

class NextPrevNavigationOff implements NavigationOption {
    static final OnTouchListener ON_TOUCH_LISTENER_EMPTY = new NavigationOnTouchListener(false);

    @Override
    public void apply(@NonNull MainActivity mainActivity) {
        mainActivity.findViewById(R.id.main_fragment_layout).setOnTouchListener(ON_TOUCH_LISTENER_EMPTY);
    }

    private static class NavigationOnTouchListener implements OnTouchListener {
        private final boolean result;

        private NavigationOnTouchListener(boolean result) {
            this.result = result;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return result;
        }
    }

}
