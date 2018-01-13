/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.about;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.isk.wifi.BuildConfig;
import com.isk.wifi.Configuration;
import com.isk.wifi.MainContextHelper;
import com.isk.wifi.R;
import com.isk.wifi.RobolectricUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class AboutActivityTest {

    private AboutActivity fixture;
    private Configuration configuration;

    @Before
    public void setUp() {
        RobolectricUtil.INSTANCE.getActivity();
        configuration = MainContextHelper.INSTANCE.getConfiguration();
    }

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testTitle() throws Exception {
        // setup
        fixture = Robolectric.setupActivity(AboutActivity.class);
        String expected = fixture.getResources().getString(R.string.action_about);
        // execute
        ActionBar actual = fixture.getSupportActionBar();
        // validate
        assertNotNull(actual);
        assertEquals(expected, actual.getTitle());
        assertNull(fixture.getActionBar());
    }

    @Test
    public void testOnOptionsItemSelectedWithHome() throws Exception {
        // setup
        MenuItem menuItem = mock(MenuItem.class);
        when(menuItem.getItemId()).thenReturn(android.R.id.home);
        fixture = Robolectric.setupActivity(AboutActivity.class);
        // execute
        boolean actual = fixture.onOptionsItemSelected(menuItem);
        // validate
        assertTrue(actual);
        verify(menuItem).getItemId();
    }

    @Test
    public void testOnOptionsItemSelected() throws Exception {
        // setup
        MenuItem menuItem = mock(MenuItem.class);
        fixture = Robolectric.setupActivity(AboutActivity.class);
        // execute
        boolean actual = fixture.onOptionsItemSelected(menuItem);
        // validate
        assertFalse(actual);
    }

    @Test
    public void testVersionNumber() throws Exception {
        // setup
        String expected = BuildConfig.VERSION_NAME + " - " + BuildConfig.VERSION_CODE
            + " (" + Build.VERSION.RELEASE + "-" + Build.VERSION.SDK_INT + ")";
        when(configuration.isSizeAvailable()).thenReturn(false);
        when(configuration.isLargeScreen()).thenReturn(false);
        fixture = Robolectric.setupActivity(AboutActivity.class);
        // execute
        TextView actual = fixture.findViewById(R.id.about_version_info);
        // validate
        assertNotNull(actual);
        assertEquals(expected, actual.getText());
    }

    @Test
    public void testVersionNumberWithConfiguration() throws Exception {
        // setup
        String expected = BuildConfig.VERSION_NAME + " - " + BuildConfig.VERSION_CODE + "SL"
            + " (" + Build.VERSION.RELEASE + "-" + Build.VERSION.SDK_INT + ")";
        when(configuration.isSizeAvailable()).thenReturn(true);
        when(configuration.isLargeScreen()).thenReturn(true);
        fixture = Robolectric.setupActivity(AboutActivity.class);
        // execute
        TextView actual = fixture.findViewById(R.id.about_version_info);
        // validate
        assertNotNull(actual);
        assertEquals(expected, actual.getText());
    }

    @Test
    public void testPackageName() throws Exception {
        // setup
        fixture = Robolectric.setupActivity(AboutActivity.class);
        // execute
        TextView actual = fixture.findViewById(R.id.about_package_name);
        // validate
        assertNotNull(actual);
        assertEquals(BuildConfig.APPLICATION_ID, actual.getText());
    }

    @Test
    public void testApplicationName() throws Exception {
        // setup
        fixture = Robolectric.setupActivity(AboutActivity.class);
        String expectedName = fixture.getString(R.string.about_application_name);
        // execute
        TextView actual = fixture.findViewById(R.id.about_application_name);
        // validate
        assertNotNull(actual);
        assertEquals(expectedName, actual.getText());
    }

    @Test
    public void testWriteReview() throws Exception {
        // setup
        fixture = Robolectric.setupActivity(AboutActivity.class);
        View view = fixture.findViewById(R.id.writeReview);
        // execute
        fixture.writeReview(view);
        // validate
    }
}