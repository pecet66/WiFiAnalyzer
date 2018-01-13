/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter.adapter;

import android.support.annotation.NonNull;

import com.isk.wifi.settings.Settings;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class SSIDAdapter extends BasicFilterAdapter<String> {
    SSIDAdapter(@NonNull Set<String> values) {
        super(values);
    }

    @Override
    public void setValues(@NonNull Set<String> values) {
        super.setValues(new HashSet<>(CollectionUtils.select(values, new SSIDPredicate())));
    }

    @Override
    public boolean isActive() {
        return !getValues().isEmpty();
    }

    @Override
    public void reset() {
        setValues(new HashSet<String>());
    }

    @Override
    public void save(@NonNull Settings settings) {
        settings.saveSSIDs(getValues());
    }

    private class SSIDPredicate implements Predicate<String> {
        @Override
        public boolean evaluate(String object) {
            return StringUtils.isNotBlank(StringUtils.trim(object));
        }
    }

}
