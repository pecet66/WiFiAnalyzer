/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.accesspoint;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.view.View;

import com.isk.wifi.MainActivity;
import com.isk.wifi.R;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.wifi.band.WiFiWidth;
import com.isk.wifi.wifi.model.WiFiAdditional;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.model.WiFiSignal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class AccessPointPopupTest {
    private MainActivity mainActivity;
    private AccessPointPopup fixture;

    @Before
    public void setUp() {
        mainActivity = RobolectricUtil.INSTANCE.getActivity();
        fixture = new AccessPointPopup();
    }

    @Test
    public void testShowOpensPopup() throws Exception {
        // setup
        View view = mainActivity.getLayoutInflater().inflate(R.layout.access_point_view_popup, null);
        // execute
        Dialog actual = fixture.show(view);
        // validate
        assertNotNull(actual);
        assertTrue(actual.isShowing());
    }

    @Test
    public void testPopupIsClosedOnCloseButtonClick() throws Exception {
        // setup
        View view = mainActivity.getLayoutInflater().inflate(R.layout.access_point_view_popup, null);
        Dialog dialog = fixture.show(view);
        View closeButton = dialog.findViewById(R.id.popupButtonClose);
        // execute
        closeButton.performClick();
        // validate
        assertFalse(dialog.isShowing());
    }

    @Test
    public void testAttach() throws Exception {
        // setup
        WiFiDetail wiFiDetail = withWiFiDetail();
        View view = mainActivity.getLayoutInflater().inflate(R.layout.access_point_view_compact, null);
        // execute
        fixture.attach(view, wiFiDetail);
        // validate
        assertTrue(view.performClick());
    }

    @NonNull
    private WiFiDetail withWiFiDetail() {
        return new WiFiDetail("SSID", "BSSID", "capabilities", new WiFiSignal(1, 1, WiFiWidth.MHZ_40, 2), WiFiAdditional.EMPTY);
    }

}