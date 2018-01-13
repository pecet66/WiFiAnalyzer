/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter.adapter;

import android.support.annotation.NonNull;

import com.isk.wifi.R;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.model.Security;

import java.util.Set;

public class SecurityAdapter extends EnumFilterAdapter<Security> {

    SecurityAdapter(@NonNull Set<Security> values) {
        super(Security.class, values);
    }

    @Override
    public int getColor(@NonNull Security object) {
        return contains(object) ? R.color.connected : R.color.icons_color;
    }

    @Override
    public void save(@NonNull Settings settings) {
        settings.saveSecurities(getValues());
    }

}
