/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.isk.wifi.navigation.NavigationGroup;
import com.isk.wifi.navigation.NavigationMenu;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;

import java.util.ArrayList;
import java.util.List;

public class StartMenuPreference extends CustomPreference {
    public StartMenuPreference(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs, getData(context), getDefault());
    }

    private static List<Data> getData(@NonNull Context context) {
        return new ArrayList<>(CollectionUtils.collect(NavigationGroup.GROUP_FEATURE.getNavigationMenus(), new ToData(context)));
    }

    private static String getDefault() {
        return Integer.toString(NavigationGroup.GROUP_FEATURE.getNavigationMenus().get(0).ordinal());
    }

    private static class ToData implements Transformer<NavigationMenu, Data> {
        private final Context context;

        ToData(@NonNull Context context) {
            this.context = context;
        }

        @Override
        public Data transform(NavigationMenu input) {
            return new Data(Integer.toString(input.ordinal()), context.getString(input.getTitle()));
        }
    }

}
