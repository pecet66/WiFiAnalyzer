/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.channelrating;

import com.isk.wifi.MainContextHelper;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.wifi.scanner.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class ChannelRatingFragmentTest {

    private Scanner scanner;
    private ChannelRatingFragment fixture;

    @Before
    public void setUp() {
        RobolectricUtil.INSTANCE.getActivity();
        scanner = MainContextHelper.INSTANCE.getScanner();
        fixture = new ChannelRatingFragment();
    }

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testOnCreateView() throws Exception {
        // execute
        SupportFragmentTestUtil.startFragment(fixture);
        // validate
        assertNotNull(fixture);
        verify(scanner).update();
        verify(scanner).register(fixture.getChannelRatingAdapter());
    }

    @Test
    public void testOnResume() throws Exception {
        // setup
        SupportFragmentTestUtil.startFragment(fixture);
        // execute
        fixture.onResume();
        // validate
        verify(scanner, times(2)).update();
    }

    @Test
    public void testOnDestroy() throws Exception {
        // setup
        SupportFragmentTestUtil.startFragment(fixture);
        // execute
        fixture.onDestroy();
        // validate
        verify(scanner).unregister(fixture.getChannelRatingAdapter());
    }

}