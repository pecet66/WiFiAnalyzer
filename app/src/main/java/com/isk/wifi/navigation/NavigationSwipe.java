/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.isk.util.EnumUtils;
import com.isk.wifi.MainActivity;
import com.isk.wifi.gestures.SwipeAction;
import com.isk.wifi.gestures.SwipeDirection;
import com.isk.wifi.navigation.NavigationGroup.NavigationPredicate;

class NavigationSwipe implements SwipeAction {

    private final MainActivity mainActivity;

    NavigationSwipe(@NonNull MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void swipe(@NonNull SwipeDirection swipeDirection) {
        if (SwipeDirection.LEFT.equals(swipeDirection)) {
            NavigationMenu nextNavigationMenu = getNextNavigationMenu();
            activateNewMenuItem(nextNavigationMenu);
        } else if (SwipeDirection.RIGHT.equals(swipeDirection)) {
            NavigationMenu previousNavigationMenu = getPreviousNavigationMenu();
            activateNewMenuItem(previousNavigationMenu);
        }
    }

    private NavigationMenu getNextNavigationMenu() {
        NavigationMenu currentNavigationMenu = mainActivity.getNavigationMenuView().getCurrentNavigationMenu();
        return getNavigationGroup(currentNavigationMenu).next(currentNavigationMenu);
    }

    private NavigationMenu getPreviousNavigationMenu() {
        NavigationMenu currentNavigationMenu = mainActivity.getNavigationMenuView().getCurrentNavigationMenu();
        return getNavigationGroup(currentNavigationMenu).previous(currentNavigationMenu);
    }

    private NavigationGroup getNavigationGroup(NavigationMenu currentNavigationMenu) {
        NavigationPredicate navigationPredicate = new NavigationPredicate(currentNavigationMenu);
        return EnumUtils.find(NavigationGroup.class, navigationPredicate, NavigationGroup.GROUP_FEATURE);
    }

    private void activateNewMenuItem(@NonNull NavigationMenu navigationMenu) {
        NavigationMenuView navigationMenuView = mainActivity.getNavigationMenuView();
        NavigationView navigationView = navigationMenuView.getNavigationView();
        MenuItem newMenuItem = navigationView.getMenu().findItem(navigationMenu.ordinal());
        MenuItem currentMenuItem = navigationMenuView.getCurrentMenuItem();
        if (!currentMenuItem.equals(newMenuItem)) {
            mainActivity.onNavigationItemSelected(newMenuItem);
        }
    }


}
