/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.scanner;

import android.os.Handler;

import com.isk.wifi.settings.Settings;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PeriodicScanTest {
    @Mock
    private Handler handler;
    @Mock
    private Settings settings;
    @Mock
    private Scanner scanner;

    private PeriodicScan fixture;

    @Before
    public void setUp() {
        fixture = new PeriodicScan(scanner, handler, settings);
    }

    @Test
    public void testScanInitial() throws Exception {
        // validate
        verify(handler).removeCallbacks(fixture);
        verify(handler).postDelayed(fixture, PeriodicScan.DELAY_INITIAL);
    }

    @Test
    public void testRun() throws Exception {
        // setup
        int scanInterval = 15;
        when(settings.getScanInterval()).thenReturn(scanInterval);
        // execute
        fixture.run();
        // validate
        verify(scanner).update();
        verify(handler, times(2)).removeCallbacks(fixture);
        verify(handler).postDelayed(fixture, scanInterval * PeriodicScan.DELAY_INTERVAL);
    }

    @Test
    public void testStop() throws Exception {
        // execute
        fixture.stop();
        // validate
        verify(handler, times(2)).removeCallbacks(fixture);
    }

    @Test
    public void testStart() throws Exception {
        // execute
        fixture.start();
        // validate
        verify(handler, times(2)).removeCallbacks(fixture);
        verify(handler, times(2)).postDelayed(fixture, PeriodicScan.DELAY_INITIAL);
    }

}