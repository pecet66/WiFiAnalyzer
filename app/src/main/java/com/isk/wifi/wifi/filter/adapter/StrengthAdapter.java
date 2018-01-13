/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter.adapter;

import android.support.annotation.NonNull;

import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.model.Strength;

import java.util.Set;

public class StrengthAdapter extends EnumFilterAdapter<Strength> {
    StrengthAdapter(@NonNull Set<Strength> values) {
        super(Strength.class, values);
    }

    @Override
    public int getColor(@NonNull Strength object) {
        return contains(object) ? object.colorResource() : object.colorResourceDefault();
    }

    @Override
    public void save(@NonNull Settings settings) {
        settings.saveStrengths(getValues());
    }
}
