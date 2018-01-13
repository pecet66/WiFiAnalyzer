/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter.adapter;

import android.support.annotation.NonNull;

import com.isk.wifi.settings.Settings;

import java.util.Set;

public abstract class BasicFilterAdapter<T> {
    private Set<T> values;

    BasicFilterAdapter(@NonNull Set<T> values) {
        setValues(values);
    }

    public Set<T> getValues() {
        return values;
    }

    public void setValues(@NonNull Set<T> values) {
        this.values = values;
    }

    abstract boolean isActive();

    abstract void reset();

    abstract void save(@NonNull Settings settings);
}
