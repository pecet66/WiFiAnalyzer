/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.accesspoint;

import android.net.wifi.WifiInfo;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.isk.wifi.MainActivity;
import com.isk.wifi.MainContextHelper;
import com.isk.wifi.R;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.navigation.NavigationMenu;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.band.WiFiWidth;
import com.isk.wifi.wifi.model.WiFiAdditional;
import com.isk.wifi.wifi.model.WiFiConnection;
import com.isk.wifi.wifi.model.WiFiData;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.model.WiFiSignal;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class ConnectionViewTest {
    private static final String SSID = "SSID";
    private static final String BSSID = "BSSID";
    private static final String IP_ADDRESS = "IPADDRESS";

    private MainActivity mainActivity;
    private ConnectionView fixture;

    private Settings settings;
    private WiFiData wiFiData;
    private AccessPointDetail accessPointDetail;
    private AccessPointPopup accessPointPopup;

    @Before
    public void setUp() {
        mainActivity = RobolectricUtil.INSTANCE.getActivity();

        accessPointDetail = mock(AccessPointDetail.class);
        accessPointPopup = mock(AccessPointPopup.class);

        wiFiData = mock(WiFiData.class);
        settings = MainContextHelper.INSTANCE.getSettings();

        fixture = new ConnectionView(mainActivity);
        fixture.setAccessPointDetail(accessPointDetail);
        fixture.setAccessPointPopup(accessPointPopup);
    }

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
        mainActivity.getNavigationMenuView().setCurrentNavigationMenu(NavigationMenu.ACCESS_POINTS);
    }

    @Test
    public void testConnectionGoneWithNoConnectionInformation() throws Exception {
        // setup
        when(settings.getConnectionViewType()).thenReturn(ConnectionViewType.COMPLETE);
        withConnectionInformation(withConnection(WiFiAdditional.EMPTY));
        // execute
        fixture.update(wiFiData);
        // validate
        assertEquals(View.GONE, mainActivity.findViewById(R.id.connection).getVisibility());
        verifyConnectionInformation();
    }

    @Test
    public void testConnectionGoneWithConnectionInformationAndHideType() throws Exception {
        // setup
        WiFiDetail connection = withConnection(withWiFiAdditional());
        when(settings.getConnectionViewType()).thenReturn(ConnectionViewType.HIDE);
        withConnectionInformation(connection);
        withAccessPointDetailView(connection, ConnectionViewType.COMPLETE.getAccessPointViewType());
        // execute
        fixture.update(wiFiData);
        // validate
        assertEquals(View.GONE, mainActivity.findViewById(R.id.connection).getVisibility());
        verifyConnectionInformation();
    }

    @Test
    public void testConnectionVisibleWithConnectionInformation() throws Exception {
        // setup
        WiFiDetail connection = withConnection(withWiFiAdditional());
        when(settings.getConnectionViewType()).thenReturn(ConnectionViewType.COMPLETE);
        withConnectionInformation(connection);
        withAccessPointDetailView(connection, ConnectionViewType.COMPLETE.getAccessPointViewType());
        // execute
        fixture.update(wiFiData);
        // validate
        assertEquals(View.VISIBLE, mainActivity.findViewById(R.id.connection).getVisibility());
        verifyConnectionInformation();
    }

    @Test
    public void testConnectionWithConnectionInformation() throws Exception {
        // setup
        WiFiAdditional wiFiAdditional = withWiFiAdditional();
        WiFiDetail connection = withConnection(wiFiAdditional);
        when(settings.getConnectionViewType()).thenReturn(ConnectionViewType.COMPLETE);
        withConnectionInformation(connection);
        withAccessPointDetailView(connection, ConnectionViewType.COMPLETE.getAccessPointViewType());
        // execute
        fixture.update(wiFiData);
        // validate
        WiFiConnection wiFiConnection = wiFiAdditional.getWiFiConnection();
        View view = mainActivity.findViewById(R.id.connection);
        TextView ipAddressView = view.findViewById(R.id.ipAddress);
        assertEquals(wiFiConnection.getIpAddress(), ipAddressView.getText().toString());
        TextView linkSpeedView = view.findViewById(R.id.linkSpeed);
        assertEquals(View.VISIBLE, linkSpeedView.getVisibility());
        assertEquals(wiFiConnection.getLinkSpeed() + WifiInfo.LINK_SPEED_UNITS, linkSpeedView.getText().toString());
    }

    @Test
    public void testConnectionWithInvalidLinkSpeed() throws Exception {
        // setup
        WiFiConnection wiFiConnection = new WiFiConnection(SSID, BSSID, IP_ADDRESS, WiFiConnection.LINK_SPEED_INVALID);
        WiFiDetail connection = withConnection(new WiFiAdditional(StringUtils.EMPTY, wiFiConnection));
        when(settings.getConnectionViewType()).thenReturn(ConnectionViewType.COMPLETE);
        withConnectionInformation(connection);
        withAccessPointDetailView(connection, ConnectionViewType.COMPLETE.getAccessPointViewType());
        // execute
        fixture.update(wiFiData);
        // validate
        View view = mainActivity.findViewById(R.id.connection);
        TextView linkSpeedView = view.findViewById(R.id.linkSpeed);
        assertEquals(View.GONE, linkSpeedView.getVisibility());
    }

    @Test
    public void testNoDataIsVisibleWithNoWiFiDetails() throws Exception {
        // setup
        when(settings.getConnectionViewType()).thenReturn(ConnectionViewType.COMPLETE);
        when(wiFiData.getConnection()).thenReturn(withConnection(WiFiAdditional.EMPTY));
        // execute
        fixture.update(wiFiData);
        // validate
        assertEquals(View.VISIBLE, mainActivity.findViewById(R.id.nodata).getVisibility());
        verify(wiFiData).getWiFiDetails();
    }

    @Test
    public void testNoDataIsGoneWithWiFiDetails() throws Exception {
        // setup
        WiFiDetail wiFiDetail = withConnection(WiFiAdditional.EMPTY);
        when(settings.getConnectionViewType()).thenReturn(ConnectionViewType.COMPLETE);
        when(wiFiData.getConnection()).thenReturn(wiFiDetail);
        when(wiFiData.getWiFiDetails()).thenReturn(Collections.singletonList(wiFiDetail));
        // execute
        fixture.update(wiFiData);
        // validate
        assertEquals(View.GONE, mainActivity.findViewById(R.id.nodata).getVisibility());
        verify(wiFiData).getWiFiDetails();
    }

    @Test
    public void testNoDataIsGoneWithNavigationMenuThatDoesNotHaveOptionMenu() throws Exception {
        // setup
        mainActivity.getNavigationMenuView().setCurrentNavigationMenu(NavigationMenu.VENDOR_LIST);
        when(settings.getConnectionViewType()).thenReturn(ConnectionViewType.COMPLETE);
        when(wiFiData.getConnection()).thenReturn(withConnection(WiFiAdditional.EMPTY));
        // execute
        fixture.update(wiFiData);
        // validate
        assertEquals(View.GONE, mainActivity.findViewById(R.id.nodata).getVisibility());
        verify(wiFiData, never()).getWiFiDetails();
    }

    @Test
    public void testViewCompactAddsPopup() throws Exception {
        // setup
        WiFiDetail connection = withConnection(withWiFiAdditional());
        when(settings.getConnectionViewType()).thenReturn(ConnectionViewType.COMPACT);
        withConnectionInformation(connection);
        View view = withAccessPointDetailView(connection, ConnectionViewType.COMPACT.getAccessPointViewType());
        // execute
        fixture.update(wiFiData);
        // validate
        verify(accessPointPopup).attach(view.findViewById(R.id.attachPopup), connection);
        verify(accessPointPopup).attach(view.findViewById(R.id.ssid), connection);
    }

    private WiFiDetail withConnection(@NonNull WiFiAdditional wiFiAdditional) {
        return new WiFiDetail(SSID, BSSID, StringUtils.EMPTY,
            new WiFiSignal(2435, 2435, WiFiWidth.MHZ_20, -55), wiFiAdditional);
    }

    private WiFiAdditional withWiFiAdditional() {
        WiFiConnection wiFiConnection = new WiFiConnection(SSID, BSSID, IP_ADDRESS, 11);
        return new WiFiAdditional(StringUtils.EMPTY, wiFiConnection);
    }

    private View withAccessPointDetailView(@NonNull WiFiDetail connection, @NonNull AccessPointViewType accessPointViewType) {
        ViewGroup parent = mainActivity.findViewById(R.id.connection).findViewById(R.id.connectionDetail);
        View view = mainActivity.getLayoutInflater().inflate(accessPointViewType.getLayout(), parent, false);
        when(accessPointDetail.makeView(null, parent, connection, false, accessPointViewType)).thenReturn(view);
        when(accessPointDetail.makeView(parent.getChildAt(0), parent, connection, false, accessPointViewType)).thenReturn(view);
        return view;
    }

    private void withConnectionInformation(@NonNull WiFiDetail connection) {
        when(wiFiData.getConnection()).thenReturn(connection);
    }

    private void verifyConnectionInformation() {
        verify(wiFiData).getConnection();
    }

}