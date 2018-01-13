/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter;

import android.app.Dialog;
import android.support.annotation.NonNull;

import com.isk.wifi.R;
import com.isk.wifi.wifi.filter.adapter.StrengthAdapter;
import com.isk.wifi.wifi.model.Strength;

import java.util.HashMap;
import java.util.Map;

class StrengthFilter extends EnumFilter<Strength, StrengthAdapter> {
    static final Map<Strength, Integer> ids = new HashMap<>();

    static {
        ids.put(Strength.ZERO, R.id.filterStrength0);
        ids.put(Strength.ONE, R.id.filterStrength1);
        ids.put(Strength.TWO, R.id.filterStrength2);
        ids.put(Strength.THREE, R.id.filterStrength3);
        ids.put(Strength.FOUR, R.id.filterStrength4);
    }

    StrengthFilter(@NonNull StrengthAdapter strengthAdapter, @NonNull Dialog dialog) {
        super(ids, strengthAdapter, dialog, R.id.filterStrength);
    }
}
