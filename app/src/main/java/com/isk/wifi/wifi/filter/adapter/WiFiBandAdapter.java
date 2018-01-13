/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter.adapter;

import android.support.annotation.NonNull;

import com.isk.wifi.R;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.band.WiFiBand;

import java.util.Set;

public class WiFiBandAdapter extends EnumFilterAdapter<WiFiBand> {

    WiFiBandAdapter(@NonNull Set<WiFiBand> values) {
        super(WiFiBand.class, values);
    }

    @Override
    public int getColor(@NonNull WiFiBand object) {
        return contains(object) ? R.color.connected : R.color.icons_color;
    }

    @Override
    public void save(@NonNull Settings settings) {
        settings.saveWiFiBands(getValues());
    }
}
