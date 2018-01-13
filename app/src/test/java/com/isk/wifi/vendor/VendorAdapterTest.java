/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.vendor;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.isk.wifi.MainActivity;
import com.isk.wifi.MainContextHelper;
import com.isk.wifi.R;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.vendor.model.VendorService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(RobolectricTestRunner.class)
public class VendorAdapterTest {
    private static final String VENDOR_NAME1 = "N1";
    private static final String VENDOR_NAME2 = "N2";
    private static final String VENDOR_NAME3 = "N3";

    private MainActivity mainActivity;
    private VendorService vendorService;
    private VendorAdapter fixture;

    @Before
    public void setUp() {
        mainActivity = RobolectricUtil.INSTANCE.getActivity();

        vendorService = MainContextHelper.INSTANCE.getVendorService();

        withVendorNames();

        fixture = new VendorAdapter(mainActivity, vendorService);
    }

    @After
    public void tearDown() {
        verify(vendorService).findVendors();
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testGetView() throws Exception {
        // setup
        when(vendorService.findMacAddresses(VENDOR_NAME2)).thenReturn(Arrays.asList("V2M1X1", "V2M2", "V2M3X1"));
        String expected = "V2:M1:X1, *V2M2*, V2:M3:X1";
        ViewGroup viewGroup = mainActivity.findViewById(android.R.id.content);
        // execute
        View actual = fixture.getView(1, null, viewGroup);
        // validate
        assertNotNull(actual);

        assertEquals(VENDOR_NAME2, ((TextView) actual.findViewById(R.id.vendor_name)).getText());
        assertEquals(expected, ((TextView) actual.findViewById(R.id.vendor_macs)).getText());

        verify(vendorService).findMacAddresses(VENDOR_NAME2);

        verify(vendorService, never()).findVendorName(VENDOR_NAME1);
        verify(vendorService, never()).findVendorName(VENDOR_NAME3);
    }

    private void withVendorNames() {
        when(vendorService.findVendors()).thenReturn(Arrays.asList(VENDOR_NAME1, VENDOR_NAME2, VENDOR_NAME3));
    }

}