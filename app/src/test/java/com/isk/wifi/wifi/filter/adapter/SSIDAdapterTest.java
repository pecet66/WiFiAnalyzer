/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter.adapter;

import com.isk.wifi.settings.Settings;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SSIDAdapterTest {

    private static final Set<String> SSID_VALUES = new HashSet<>(Arrays.asList("value1", "value2", "value3"));

    @Mock
    private Settings settings;

    private SSIDAdapter fixture;

    @Before
    public void setUp() {
        fixture = new SSIDAdapter(SSID_VALUES);
    }

    @Test
    public void testGetValue() throws Exception {
        assertEquals(SSID_VALUES.size(), fixture.getValues().size());
        IterableUtils.forEach(SSID_VALUES, new ContainsClosure());
    }

    @Test
    public void testIsActive() throws Exception {
        assertTrue(fixture.isActive());
    }

    @Test
    public void testIsNotActiveWithEmptyValue() throws Exception {
        // execute
        fixture.setValues(Collections.<String>emptySet());
        // validate
        assertFalse(fixture.isActive());
        assertTrue(fixture.getValues().isEmpty());
    }

    @Test
    public void testIsNotActiveWithReset() throws Exception {
        // execute
        fixture.reset();
        // validate
        assertFalse(fixture.isActive());
        assertTrue(fixture.getValues().isEmpty());
    }

    @Test
    public void testSave() throws Exception {
        // execute
        fixture.save(settings);
        // execute
        verify(settings).saveSSIDs(SSID_VALUES);
    }

    @Test
    public void testSetValues() throws Exception {
        // setup
        Set<String> expected = new HashSet<>(Arrays.asList("ABC", "EDF", "123"));
        Set<String> values = new HashSet<>(Arrays.asList("", "ABC", "", "EDF", "  ", "123", ""));
        // execute
        fixture.setValues(values);
        // execute
        assertEquals(expected, fixture.getValues());
    }

    private class ContainsClosure implements Closure<String> {
        @Override
        public void execute(String input) {
            assertTrue(fixture.getValues().contains(input));
        }
    }
}