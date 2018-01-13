/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationUtilsTest {

    @Mock
    private Context context;
    @Mock
    private ContextWrapper contextWrapper;
    @Mock
    private Resources resources;
    @Mock
    private Configuration configuration;
    @Mock
    private DisplayMetrics displayMetrics;

    private Locale newLocale;

    @Before
    public void setUp() {
        newLocale = Locale.US;
    }

    @Test
    public void testCreateContext() throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            validateCreateContextWithNougat();
        } else {
            validateCreateContextWithLegacy();
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void validateCreateContextWithNougat() throws Exception {
        // setup
        when(context.getResources()).thenReturn(resources);
        when(resources.getConfiguration()).thenReturn(configuration);
        when(context.createConfigurationContext(configuration)).thenReturn(contextWrapper);
        // execute
        Context actual = ConfigurationUtils.createContext(context, newLocale);
        // validate
        assertEquals(contextWrapper, actual);
        assertEquals(context, ((ContextWrapper) actual).getBaseContext());
        verify(configuration).setLocale(newLocale);
        verify(context).createConfigurationContext(configuration);
        verify(context).getResources();
        verify(resources).getConfiguration();
    }

    @SuppressWarnings("deprecation")
    @Test
    public void validateCreateContextWithLegacy() throws Exception {
        // setup
        when(context.getResources()).thenReturn(resources);
        when(resources.getConfiguration()).thenReturn(configuration);
        when(resources.getDisplayMetrics()).thenReturn(displayMetrics);
        // execute
        Context actual = ConfigurationUtils.createContext(context, newLocale);
        // validate
        assertEquals(context, actual);
        assertEquals(newLocale, configuration.locale);
        verify(resources).getDisplayMetrics();
        verify(resources).updateConfiguration(configuration, displayMetrics);
        verify(context).getResources();
        verify(resources).getConfiguration();
    }

}