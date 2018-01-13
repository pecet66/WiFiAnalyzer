/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.items;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.isk.wifi.MainActivity;
import com.isk.wifi.MainContextHelper;
import com.isk.wifi.R;
import com.isk.wifi.navigation.NavigationMenu;
import com.isk.wifi.wifi.band.WiFiWidth;
import com.isk.wifi.wifi.model.WiFiConnection;
import com.isk.wifi.wifi.model.WiFiData;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.model.WiFiSignal;
import com.isk.wifi.wifi.scanner.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExportItemTest {
    @Mock
    private Intent sendIntent;
    @Mock
    private Intent chooserIntent;
    @Mock
    private MainActivity mainActivity;
    @Mock
    private Resources resources;
    @Mock
    private MenuItem menuItem;
    @Mock
    private PackageManager packageManager;
    @Mock
    private ComponentName componentName;

    private ExportItem fixture;
    private String sendTitle;
    private Scanner scanner;
    private WiFiDetail wiFiDetail;

    @Before
    public void setUp() {
        scanner = MainContextHelper.INSTANCE.getScanner();

        sendTitle = "title";
        wiFiDetail = new WiFiDetail("SSID", "BSSID", "capabilities", new WiFiSignal(2412, 2422, WiFiWidth.MHZ_40, -40));

        fixture = new ExportItem() {
            @Override
            Intent createSendIntent() {
                return sendIntent;
            }

            @Override
            Intent createChooserIntent(@NonNull Intent intent, @NonNull String title) {
                assertEquals(sendIntent, intent);
                assertEquals(sendTitle, title);
                return chooserIntent;
            }
        };
    }

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
    }


    @Test
    public void testActivate() throws Exception {
        // setup
        WiFiData wiFiData = withWiFiData();
        when(scanner.getWiFiData()).thenReturn(wiFiData);
        withResources();
        withResolveActivity();
        // execute
        fixture.activate(mainActivity, menuItem, NavigationMenu.EXPORT);
        // validate
        String timestamp = fixture.getTimestamp();
        String sendData = fixture.getData(timestamp, wiFiData.getWiFiDetails());
        verify(scanner).getWiFiData();
        verifyResources();
        verifyResolveActivity();
        verifySendIntentInformation(sendData);
        verify(mainActivity).startActivity(chooserIntent);
    }

    @Test
    public void testGetData() throws Exception {
        // setup
        WiFiData wiFiData = withWiFiData();
        String expected =
            String.format(Locale.ENGLISH,
                "Time Stamp|SSID|BSSID|Strength|Primary Channel|Primary Frequency|Center Channel|Center Frequency|Width (Range)|Distance|Security%n"
                    + "TimeStamp1|SSID|BSSID|-40dBm|1|2412MHz|3|2422MHz|40MHz (2402 - 2442)|1.0m|capabilities%n");
        // execute
        String actual = fixture.getData("TimeStamp1", wiFiData.getWiFiDetails());
        // validate
        assertEquals(expected, actual);
    }

    @Test
    public void testIsRegistered() throws Exception {
        // execute & validate
        assertFalse(fixture.isRegistered());
    }

    @NonNull
    private WiFiData withWiFiData() {
        return new WiFiData(Collections.singletonList(wiFiDetail), WiFiConnection.EMPTY, Collections.<String>emptyList());
    }

    private void verifySendIntentInformation(String sendData) {
        verify(sendIntent).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        verify(sendIntent).setType("text/plain");
        verify(sendIntent).putExtra(Intent.EXTRA_TITLE, sendTitle);
        verify(sendIntent).putExtra(Intent.EXTRA_SUBJECT, sendTitle);
        verify(sendIntent).putExtra(Intent.EXTRA_TEXT, sendData);
    }

    private void withResolveActivity() {
        when(mainActivity.getPackageManager()).thenReturn(packageManager);
        when(chooserIntent.resolveActivity(packageManager)).thenReturn(componentName);
    }

    private void verifyResolveActivity() {
        verify(mainActivity).getPackageManager();
        verify(chooserIntent).resolveActivity(packageManager);
    }

    private void withResources() {
        when(mainActivity.getResources()).thenReturn(resources);
        when(resources.getString(R.string.action_access_points)).thenReturn(sendTitle);
    }

    private void verifyResources() {
        verify(mainActivity).getResources();
        verify(resources).getString(R.string.action_access_points);
    }

}