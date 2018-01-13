/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.isk.util.LocaleUtils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class LanguagePreference extends CustomPreference {

    public LanguagePreference(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs, getData(), LocaleUtils.getDefaultLanguageTag());
    }

    @NonNull
    private static List<Data> getData() {
        List<Data> results = new ArrayList<>(CollectionUtils.collect(LocaleUtils.getSupportedLanguages(), new ToData()));
        Collections.sort(results);
        return results;
    }

    private static class ToData implements Transformer<Locale, Data> {
        @Override
        public Data transform(Locale input) {
            return new Data(LocaleUtils.toLanguageTag(input), StringUtils.capitalize(input.getDisplayName(input)));
        }
    }
}
