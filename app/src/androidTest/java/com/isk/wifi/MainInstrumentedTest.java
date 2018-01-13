/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.isk.wifi.InstrumentedUtils.pauseShort;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testNavigation() {
        pauseShort();
        new Navigation().run();
        pauseShort();
    }

    @Test
    public void testScanner() {
        pauseShort();
        new Scanner().run();
        pauseShort();
    }

    @Test
    public void testFilter() {
        pauseShort();
        new Filter().run();
        pauseShort();
    }

}