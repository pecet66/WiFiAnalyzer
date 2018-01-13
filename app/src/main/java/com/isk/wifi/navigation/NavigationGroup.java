/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation;

import android.support.annotation.NonNull;

import org.apache.commons.collections4.Predicate;

import java.util.Arrays;
import java.util.List;

public enum NavigationGroup {
    GROUP_FEATURE(NavigationMenu.ACCESS_POINTS,
            NavigationMenu.CHANNEL_RATING);

    private final List<NavigationMenu> navigationMenus;

    NavigationGroup(@NonNull NavigationMenu... navigationMenus) {
        this.navigationMenus = Arrays.asList(navigationMenus);
    }

    @NonNull
    public List<NavigationMenu> getNavigationMenus() {
        return navigationMenus;
    }

    @NonNull
    public NavigationMenu next(@NonNull NavigationMenu navigationMenu) {
        int index = navigationMenus.indexOf(navigationMenu);
        if (index < 0) {
            return navigationMenu;
        }
        index++;
        if (index >= navigationMenus.size()) {
            index = 0;
        }
        return navigationMenus.get(index);
    }

    @NonNull
    public NavigationMenu previous(@NonNull NavigationMenu navigationMenu) {
        int index = navigationMenus.indexOf(navigationMenu);
        if (index < 0) {
            return navigationMenu;
        }
        index--;
        if (index < 0) {
            index = navigationMenus.size() - 1;
        }
        return navigationMenus.get(index);
    }

    static class NavigationPredicate implements Predicate<NavigationGroup> {
        public final NavigationMenu navigationMenu;

        NavigationPredicate(@NonNull NavigationMenu navigationMenu) {
            this.navigationMenu = navigationMenu;
        }

        @Override
        public boolean evaluate(NavigationGroup object) {
            return object.getNavigationMenus().contains(navigationMenu);
        }
    }

}
