/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.items;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.isk.wifi.MainActivity;
import com.isk.wifi.about.AboutActivity;
import com.isk.wifi.navigation.NavigationMenu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ActivityItemTest {

    @Mock
    private MainActivity mockMainActivity;
    @Mock
    private MenuItem mockMenuItem;
    @Mock
    private Intent mockIntent;

    @Test
    public void testActivate() throws Exception {
        // setup
        ActivityItem fixture = new TestActivityItem();
        // execute
        fixture.activate(mockMainActivity, mockMenuItem, NavigationMenu.ABOUT);
        // validate
        verify(mockMainActivity).startActivity(mockIntent);
    }

    @Test
    public void testIsRegistered() throws Exception {
        // setup
        ActivityItem fixture = new ActivityItem(AboutActivity.class);
        // execute & validate
        assertFalse(fixture.isRegistered());
    }

    private class TestActivityItem extends ActivityItem {
        private TestActivityItem() {
            super(AboutActivity.class);
        }

        @Override
        Intent createIntent(@NonNull MainActivity mainActivity) {
            assertEquals(mockMainActivity, mainActivity);
            return mockIntent;
        }
    }
}