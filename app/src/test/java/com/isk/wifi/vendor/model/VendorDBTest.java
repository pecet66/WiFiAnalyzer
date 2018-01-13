/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.vendor.model;

import com.isk.wifi.MainActivity;
import com.isk.wifi.RobolectricUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class VendorDBTest {
    private static final String VENDOR_NAME = "CISCO SYSTEMS INC";
    private static final String MAC_ADDRESS = "0023AB";

    private VendorDB fixture;

    @Before
    public void setUp() {
        MainActivity mainActivity = RobolectricUtil.INSTANCE.getActivity();
        fixture = new VendorDB(mainActivity.getResources());
    }

    @Test
    public void testFindVendorName() throws Exception {
        // execute & validate
        assertEquals(VENDOR_NAME, fixture.findVendorName(MAC_ADDRESS));
    }

    @Test
    public void testFindMacAddresses() throws Exception {
        // setup
        int expectedSize = 786;
        // execute
        List<String> actual = fixture.findMacAddresses(VENDOR_NAME);
        // validate
        assertEquals(expectedSize, actual.size());

        assertEquals("00000C", actual.get(0));
        assertEquals("FCFBFB", actual.get(expectedSize - 1));
        assertEquals("006BF1", actual.get(expectedSize / 2));
    }

    @Test
    public void testVendorDataIsValid() throws Exception {
        // execute & validate
        assertEquals(16792, fixture.getVendors().size());
    }

    @Test
    public void testMacDataIsValid() throws Exception {
        // execute & validate
        assertEquals(24164, fixture.getMacs().size());
    }
}