/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.scanner;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;

import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.model.WiFiData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScannerTest {
    @Mock
    private Handler handler;
    @Mock
    private Settings settings;
    @Mock
    private WifiManager wifiManager;
    @Mock
    private UpdateNotifier updateNotifier1;
    @Mock
    private UpdateNotifier updateNotifier2;
    @Mock
    private UpdateNotifier updateNotifier3;
    @Mock
    private WifiInfo wifiInfo;
    @Mock
    private Cache cache;
    @Mock
    private Transformer transformer;
    @Mock
    private WiFiData wiFiData;
    @Mock
    private PeriodicScan periodicScan;

    private List<ScanResult> scanResults;
    private List<CacheResult> cacheResults;
    private List<WifiConfiguration> configuredNetworks;

    private Scanner fixture;

    @Before
    public void setUp() {
        scanResults = Collections.emptyList();
        cacheResults = Collections.emptyList();
        configuredNetworks = Collections.emptyList();

        fixture = new Scanner(wifiManager, handler, settings);
        fixture.setCache(cache);
        fixture.setTransformer(transformer);

        fixture.register(updateNotifier1);
        fixture.register(updateNotifier2);
        fixture.register(updateNotifier3);
    }

    @Test
    public void testPeriodicScanIsSet() throws Exception {
        assertNotNull(fixture.getPeriodicScan());
    }

    @Test
    public void testRegister() throws Exception {
        // setup
        assertEquals(3, fixture.getUpdateNotifiers().size());
        // execute
        fixture.register(updateNotifier2);
        // validate
        assertEquals(4, fixture.getUpdateNotifiers().size());
    }

    @Test
    public void testUnregister() throws Exception {
        // setup
        assertEquals(3, fixture.getUpdateNotifiers().size());
        // execute
        fixture.unregister(updateNotifier2);
        // validate
        assertEquals(2, fixture.getUpdateNotifiers().size());
    }

    @Test
    public void testUpdateWithWiFiData() throws Exception {
        // setup
        withCache();
        withTransformer();
        withWiFiManager();
        // execute
        fixture.update();
        // validate
        assertEquals(wiFiData, fixture.getWiFiData());
        verifyCache();
        verifyTransfomer();
        verifyWiFiManager();
        verify(updateNotifier1).update(wiFiData);
        verify(updateNotifier2).update(wiFiData);
        verify(updateNotifier3).update(wiFiData);
    }

    @Test
    public void testUpdateWithWiFiManager() throws Exception {
        // setup
        withCache();
        withWiFiManager();
        // execute
        fixture.update();
        // validate
        verifyWiFiManager();
    }

    @Test
    public void testUpdateWithCache() throws Exception {
        // setup
        withCache();
        withWiFiManager();
        // execute
        fixture.update();
        // validate
        verifyCache();
    }

    @Test
    public void testSetWiFiOnExitOff() throws Exception {
        // setup
        when(settings.isWiFiOffOnExit()).thenReturn(true);
        // execute
        fixture.setWiFiOnExit();
        // validate
        verify(settings).isWiFiOffOnExit();
        verify(wifiManager).setWifiEnabled(false);
    }

    @Test
    public void testSetWiFiOnExitDoNothing() throws Exception {
        // setup
        when(settings.isWiFiOffOnExit()).thenReturn(false);
        // execute
        fixture.setWiFiOnExit();
        // validate
        verify(settings).isWiFiOffOnExit();
        verify(wifiManager, never()).setWifiEnabled(anyBoolean());
    }

    private void withCache() {
        when(cache.getScanResults()).thenReturn(cacheResults);
    }

    private void withTransformer() {
        when(transformer.transformToWiFiData(cacheResults, wifiInfo, configuredNetworks)).thenReturn(wiFiData);
    }

    private void verifyCache() {
        verify(cache).add(scanResults);
        verify(cache).getScanResults();
    }

    private void verifyWiFiManager() {
        verify(wifiManager).isWifiEnabled();
        verify(wifiManager).setWifiEnabled(true);
        verify(wifiManager).startScan();
        verify(wifiManager).getScanResults();
        verify(wifiManager).getConnectionInfo();
        verify(wifiManager).getConfiguredNetworks();
    }

    private void withWiFiManager() {
        when(wifiManager.isWifiEnabled()).thenReturn(false);
        when(wifiManager.startScan()).thenReturn(true);
        when(wifiManager.getScanResults()).thenReturn(scanResults);
        when(wifiManager.getConnectionInfo()).thenReturn(wifiInfo);
        when(wifiManager.getConfiguredNetworks()).thenReturn(configuredNetworks);
    }

    private void verifyTransfomer() {
        verify(transformer).transformToWiFiData(cacheResults, wifiInfo, configuredNetworks);
    }

    @Test
    public void testPause() throws Exception {
        // setup
        fixture.setPeriodicScan(periodicScan);
        // execute
        fixture.pause();
        // validate
        verify(periodicScan).stop();
    }

    @Test
    public void testResume() throws Exception {
        // setup
        fixture.setPeriodicScan(periodicScan);
        // execute
        fixture.resume();
        // validate
        verify(periodicScan).start();
    }

}