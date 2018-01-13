/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.items;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.isk.wifi.MainActivity;
import com.isk.wifi.navigation.NavigationMenu;

class ActivityItem implements NavigationItem {
    private final Class<? extends Activity> activity;

    ActivityItem(@NonNull Class<? extends Activity> activity) {
        this.activity = activity;
    }

    @Override
    public void activate(@NonNull MainActivity mainActivity, @NonNull MenuItem menuItem, @NonNull NavigationMenu navigationMenu) {
        mainActivity.startActivity(createIntent(mainActivity));
    }

    @Override
    public boolean isRegistered() {
        return false;
    }

    Intent createIntent(@NonNull MainActivity mainActivity) {
        return new Intent(mainActivity, activity);
    }

    Class<? extends Activity> getActivity() {
        return activity;
    }
}
