/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.settings;

import android.support.annotation.NonNull;

import com.isk.util.EnumUtils;
import com.isk.util.LocaleUtils;
import com.isk.wifi.R;
import com.isk.wifi.navigation.NavigationMenu;
import com.isk.wifi.wifi.accesspoint.AccessPointViewType;
import com.isk.wifi.wifi.accesspoint.ConnectionViewType;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.graphutils.GraphLegend;
import com.isk.wifi.wifi.model.GroupBy;
import com.isk.wifi.wifi.model.Security;
import com.isk.wifi.wifi.model.SortBy;
import com.isk.wifi.wifi.model.Strength;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static android.content.SharedPreferences.OnSharedPreferenceChangeListener;

public class Settings {
    static final int GRAPH_Y_MULTIPLIER = -10;
    static final int GRAPH_Y_DEFAULT = 2;

    private final Repository repository;

    public Settings(@NonNull Repository repository) {
        this.repository = repository;
    }

    public void initializeDefaultValues() {
        repository.initializeDefaultValues();
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        repository.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public int getScanInterval() {
        return repository.getInteger(R.string.scan_interval_key, repository.getResourceInteger(R.integer.scan_interval_default));
    }

    public int getGraphMaximumY() {
        int defaultValue = repository.getStringAsInteger(R.string.graph_maximum_y_default, GRAPH_Y_DEFAULT);
        int result = repository.getStringAsInteger(R.string.graph_maximum_y_key, defaultValue);
        return result * GRAPH_Y_MULTIPLIER;
    }

    public void toggleWiFiBand() {
        repository.save(R.string.wifi_band_key, getWiFiBand().toggle().ordinal());
    }

    public String getCountryCode() {
        String countryCode = LocaleUtils.getDefaultCountryCode();
        return repository.getString(R.string.country_code_key, countryCode);
    }

    public Locale getLanguageLocale() {
        String defaultLanguageTag = LocaleUtils.getDefaultLanguageTag();
        String languageTag = repository.getString(R.string.language_key, defaultLanguageTag);
        return LocaleUtils.findByLanguageTag(languageTag);
    }

    public SortBy getSortBy() {
        return find(SortBy.class, R.string.sort_by_key, SortBy.STRENGTH);
    }

    public GroupBy getGroupBy() {
        return find(GroupBy.class, R.string.group_by_key, GroupBy.NONE);
    }

    public AccessPointViewType getAccessPointView() {
        return find(AccessPointViewType.class, R.string.ap_view_key, AccessPointViewType.COMPLETE);
    }

    public ConnectionViewType getConnectionViewType() {
        return find(ConnectionViewType.class, R.string.connection_view_key, ConnectionViewType.COMPLETE);
    }

    public GraphLegend getChannelGraphLegend() {
        return find(GraphLegend.class, R.string.channel_graph_legend_key, GraphLegend.HIDE);
    }

    public GraphLegend getTimeGraphLegend() {
        return find(GraphLegend.class, R.string.time_graph_legend_key, GraphLegend.LEFT);
    }

    public WiFiBand getWiFiBand() {
        return find(WiFiBand.class, R.string.wifi_band_key, WiFiBand.GHZ2);
    }

    public boolean isWiFiOffOnExit() {
        return repository.getBoolean(R.string.wifi_off_on_exit_key, repository.getResourceBoolean(R.bool.wifi_off_on_exit_default));
    }

    public ThemeStyle getThemeStyle() {
        return find(ThemeStyle.class, R.string.theme_key, ThemeStyle.DARK);
    }

    public NavigationMenu getStartMenu() {
        return find(NavigationMenu.class, R.string.start_menu_key, NavigationMenu.ACCESS_POINTS);
    }

    public Set<String> getSSIDs() {
        return repository.getStringSet(R.string.filter_ssid_key, new HashSet<String>());
    }

    public void saveSSIDs(@NonNull Set<String> values) {
        repository.saveStringSet(R.string.filter_ssid_key, values);
    }

    public Set<WiFiBand> getWiFiBands() {
        return findSet(WiFiBand.class, R.string.filter_wifi_band_key, WiFiBand.GHZ2);
    }

    public void saveWiFiBands(@NonNull Set<WiFiBand> values) {
        saveSet(R.string.filter_wifi_band_key, values);
    }

    public Set<Strength> getStrengths() {
        return findSet(Strength.class, R.string.filter_strength_key, Strength.FOUR);
    }

    public void saveStrengths(@NonNull Set<Strength> values) {
        saveSet(R.string.filter_strength_key, values);
    }

    public Set<Security> getSecurities() {
        return findSet(Security.class, R.string.filter_security_key, Security.NONE);
    }

    public void saveSecurities(@NonNull Set<Security> values) {
        saveSet(R.string.filter_security_key, values);
    }

    private <T extends Enum> T find(@NonNull Class<T> enumType, int key, @NonNull T defaultValue) {
        int value = repository.getStringAsInteger(key, defaultValue.ordinal());
        return EnumUtils.find(enumType, value, defaultValue);
    }

    private <T extends Enum> Set<T> findSet(@NonNull Class<T> enumType, int key, @NonNull T defaultValue) {
        Set<String> defaultValues = EnumUtils.ordinals(enumType);
        Set<String> values = repository.getStringSet(key, defaultValues);
        return EnumUtils.find(enumType, values, defaultValue);
    }

    private <T extends Enum> void saveSet(int key, @NonNull Set<T> values) {
        repository.saveStringSet(key, EnumUtils.find(values));
    }
}
