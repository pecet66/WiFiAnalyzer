/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi;

import android.support.annotation.NonNull;

import com.isk.wifi.settings.Settings;
import com.isk.wifi.settings.ThemeStyle;
import com.isk.wifi.wifi.accesspoint.AccessPointViewType;
import com.isk.wifi.wifi.accesspoint.ConnectionViewType;

import java.util.Locale;

class MainReload {
    private ThemeStyle themeStyle;
    private AccessPointViewType accessPointViewType;
    private ConnectionViewType connectionViewType;
    private int graphMaximumY;
    private Locale languageLocale;

    MainReload(@NonNull Settings settings) {
        setThemeStyle(settings.getThemeStyle());
        setAccessPointViewType(settings.getAccessPointView());
        setConnectionViewType(settings.getConnectionViewType());
        setGraphMaximumY(settings.getGraphMaximumY());
        setLanguageLocale(settings.getLanguageLocale());
    }

    boolean shouldReload(@NonNull Settings settings) {
        return isThemeChanged(settings) || isAccessPointViewChanged(settings)
            || isConnectionViewTypeChanged(settings) || isGraphMaximumYChanged(settings)
            || isLanguageChanged(settings);
    }

    private boolean isAccessPointViewChanged(Settings settings) {
        AccessPointViewType settingAccessPointViewType = settings.getAccessPointView();
        boolean accessPointViewChanged = !getAccessPointViewType().equals(settingAccessPointViewType);
        if (accessPointViewChanged) {
            setAccessPointViewType(settingAccessPointViewType);
        }
        return accessPointViewChanged;
    }

    private boolean isConnectionViewTypeChanged(Settings settings) {
        ConnectionViewType currentConnectionViewType = settings.getConnectionViewType();
        boolean connectionViewTypeChanged = !getConnectionViewType().equals(currentConnectionViewType);
        if (connectionViewTypeChanged) {
            setConnectionViewType(currentConnectionViewType);
        }
        return connectionViewTypeChanged;
    }

    private boolean isThemeChanged(Settings settings) {
        ThemeStyle settingThemeStyle = settings.getThemeStyle();
        boolean themeChanged = !getThemeStyle().equals(settingThemeStyle);
        if (themeChanged) {
            setThemeStyle(settingThemeStyle);
        }
        return themeChanged;
    }

    private boolean isGraphMaximumYChanged(Settings settings) {
        int currentGraphMaximumY = settings.getGraphMaximumY();
        boolean graphMaximumYChanged = currentGraphMaximumY != getGraphMaximumY();
        if (graphMaximumYChanged) {
            setGraphMaximumY(currentGraphMaximumY);
        }
        return graphMaximumYChanged;
    }

    private boolean isLanguageChanged(Settings settings) {
        Locale settingLanguageLocale = settings.getLanguageLocale();
        boolean languageLocaleChanged = !getLanguageLocale().equals(settingLanguageLocale);
        if (languageLocaleChanged) {
            setLanguageLocale(settingLanguageLocale);
        }
        return languageLocaleChanged;
    }

    ThemeStyle getThemeStyle() {
        return themeStyle;
    }

    private void setThemeStyle(@NonNull ThemeStyle themeStyle) {
        this.themeStyle = themeStyle;
    }

    AccessPointViewType getAccessPointViewType() {
        return accessPointViewType;
    }

    private void setAccessPointViewType(@NonNull AccessPointViewType accessPointViewType) {
        this.accessPointViewType = accessPointViewType;
    }

    ConnectionViewType getConnectionViewType() {
        return connectionViewType;
    }

    private void setConnectionViewType(@NonNull ConnectionViewType connectionViewType) {
        this.connectionViewType = connectionViewType;
    }

    int getGraphMaximumY() {
        return graphMaximumY;
    }

    private void setGraphMaximumY(int graphMaximumY) {
        this.graphMaximumY = graphMaximumY;
    }

    Locale getLanguageLocale() {
        return languageLocale;
    }

    private void setLanguageLocale(Locale languageLocale) {
        this.languageLocale = languageLocale;
    }

}
