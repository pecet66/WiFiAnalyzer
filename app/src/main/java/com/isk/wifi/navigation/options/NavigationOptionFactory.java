/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import java.util.Arrays;
import java.util.List;

public class NavigationOptionFactory {
    public static final NavigationOption WIFI_SWITCH_ON = new WiFiSwitchOn();
    public static final NavigationOption SCANNER_SWITCH_ON = new ScannerSwitchOn();
    public static final NavigationOption FILTER_ON = new FilterOn();
    public static final NavigationOption NEXT_PREV_ON = new NextPrevNavigationOn();
    public static final NavigationOption WIFI_SWITCH_OFF = new WiFiSwitchOff();
    public static final NavigationOption SCANNER_SWITCH_OFF = new ScannerSwitchOff();
    public static final NavigationOption FILTER_OFF = new FilterOff();
    public static final NavigationOption NEXT_PREV_OFF = new NextPrevNavigationOff();
    public static final List<NavigationOption> AP = Arrays.asList(WIFI_SWITCH_OFF, SCANNER_SWITCH_ON, FILTER_ON, NEXT_PREV_ON);
    public static final List<NavigationOption> RATING = Arrays.asList(WIFI_SWITCH_ON, SCANNER_SWITCH_ON, FILTER_OFF, NEXT_PREV_ON);
    public static final List<NavigationOption> OTHER = Arrays.asList(WIFI_SWITCH_ON, SCANNER_SWITCH_ON, FILTER_ON, NEXT_PREV_ON);
    public static final List<NavigationOption> OFF = Arrays.asList(WIFI_SWITCH_OFF, SCANNER_SWITCH_OFF, FILTER_OFF, NEXT_PREV_OFF);

    private NavigationOptionFactory() {
        throw new IllegalStateException("Utility class");
    }
}
