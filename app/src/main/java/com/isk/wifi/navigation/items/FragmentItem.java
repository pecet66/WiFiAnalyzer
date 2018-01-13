/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.items;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.isk.wifi.MainActivity;
import com.isk.wifi.R;
import com.isk.wifi.navigation.NavigationMenu;
import com.isk.wifi.navigation.NavigationMenuView;

class FragmentItem implements NavigationItem {
    private final Fragment fragment;
    private final boolean registered;

    FragmentItem(@NonNull Fragment fragment, boolean registered) {
        this.fragment = fragment;
        this.registered = registered;
    }

    FragmentItem(@NonNull Fragment fragment) {
        this(fragment, false);
    }

    @Override
    public void activate(@NonNull MainActivity mainActivity, @NonNull MenuItem menuItem, @NonNull NavigationMenu navigationMenu) {
        NavigationMenuView navigationMenuView = mainActivity.getNavigationMenuView();
        navigationMenuView.setCurrentNavigationMenu(navigationMenu);
        startFragment(mainActivity);
        mainActivity.setTitle(menuItem.getTitle());
        mainActivity.updateActionBar();
    }

    @Override
    public boolean isRegistered() {
        return registered;
    }

    private void startFragment(@NonNull MainActivity mainActivity) {
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment).commit();
    }

    Fragment getFragment() {
        return fragment;
    }
}
