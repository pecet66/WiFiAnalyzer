/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import android.support.annotation.NonNull;

import com.isk.wifi.MainActivity;
import com.isk.wifi.R;
import com.isk.wifi.navigation.NavigationSwipeOnTouchListener;

class NextPrevNavigationOn implements NavigationOption {
    @Override
    public void apply(@NonNull MainActivity mainActivity) {
        mainActivity.findViewById(R.id.main_fragment_layout).setOnTouchListener(new NavigationSwipeOnTouchListener(mainActivity));
    }
}
