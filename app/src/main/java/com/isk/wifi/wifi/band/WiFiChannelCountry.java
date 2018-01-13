/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.band;

import android.support.annotation.NonNull;

import com.isk.util.LocaleUtils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;

public class WiFiChannelCountry {
    public static final String UNKNOWN = "-Unknown";

    private static final WiFiChannelCountryGHZ2 WIFI_CHANNEL_GHZ2 = new WiFiChannelCountryGHZ2();
    private static final WiFiChannelCountryGHZ5 WIFI_CHANNEL_GHZ5 = new WiFiChannelCountryGHZ5();

    private final Locale country;

    private WiFiChannelCountry(@NonNull Locale country) {
        this.country = country;
    }

    public static WiFiChannelCountry get(@NonNull String countryCode) {
        return new WiFiChannelCountry(LocaleUtils.findByCountryCode(countryCode));
    }

    public static List<WiFiChannelCountry> getAll() {
        return new ArrayList<>(CollectionUtils.collect(LocaleUtils.getAllCountries(), new ToCountry()));
    }

    @NonNull
    public String getCountryCode() {
        String countryCode = country.getCountry();
        if (countryCode == null) {
            countryCode = StringUtils.EMPTY;
        }
        return countryCode;
    }

    @NonNull
    public String getCountryName(Locale currentLocale) {
        String countryName = country.getDisplayCountry(currentLocale);
        if (countryName == null) {
            countryName = StringUtils.EMPTY;
        }
        return country.getCountry().equals(countryName) ? countryName + UNKNOWN : countryName;
    }

    public SortedSet<Integer> getChannelsGHZ2() {
        return WIFI_CHANNEL_GHZ2.findChannels(country.getCountry());
    }

    public SortedSet<Integer> getChannelsGHZ5() {
        return WIFI_CHANNEL_GHZ5.findChannels(country.getCountry());
    }

    boolean isChannelAvailableGHZ2(int channel) {
        return getChannelsGHZ2().contains(channel);
    }

    boolean isChannelAvailableGHZ5(int channel) {
        return getChannelsGHZ5().contains(channel);
    }

    private static class ToCountry implements Transformer<Locale, WiFiChannelCountry> {
        @Override
        public WiFiChannelCountry transform(Locale input) {
            return new WiFiChannelCountry(input);
        }
    }
}
