/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.Locale;

public class ConfigurationUtils {
    private ConfigurationUtils() {
        throw new IllegalStateException("Utility class");
    }

    @NonNull
    public static Context createContext(@NonNull Context context, @NonNull Locale newLocale) {
        return
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
                ? createContextNougat(context, newLocale)
                : createContextLegacy(context, newLocale);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @NonNull
    private static Context createContextNougat(@NonNull Context context, @NonNull Locale newLocale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(newLocale);
        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    @NonNull
    private static Context createContextLegacy(@NonNull Context context, @NonNull Locale newLocale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = newLocale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

}
