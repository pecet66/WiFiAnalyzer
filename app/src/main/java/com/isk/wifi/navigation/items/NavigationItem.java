/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.items;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.isk.wifi.MainActivity;
import com.isk.wifi.navigation.NavigationMenu;

public interface NavigationItem {
    void activate(@NonNull MainActivity mainActivity, @NonNull MenuItem menuItem, @NonNull NavigationMenu navigationMenu);

    boolean isRegistered();
}
