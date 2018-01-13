/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.isk.util.LocaleUtils;
import com.isk.wifi.MainContext;
import com.isk.wifi.wifi.band.WiFiChannelCountry;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CountryPreference extends CustomPreference {
    public CountryPreference(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs, getData(), LocaleUtils.getDefaultCountryCode());
    }

    @NonNull
    private static List<Data> getData() {
        List<Data> results = new ArrayList<>(CollectionUtils.collect(WiFiChannelCountry.getAll(), new ToData()));
        Collections.sort(results);
        return results;
    }

    private static class ToData implements Transformer<WiFiChannelCountry, Data> {
        private final Locale currentLocale;

        private ToData() {
            this.currentLocale = MainContext.INSTANCE.getSettings().getLanguageLocale();
        }

        @Override
        public Data transform(WiFiChannelCountry input) {
            return new Data(input.getCountryCode(), input.getCountryName(currentLocale));
        }
    }

}
