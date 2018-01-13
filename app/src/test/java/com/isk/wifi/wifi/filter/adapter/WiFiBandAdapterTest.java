/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter.adapter;

import com.isk.util.EnumUtils;
import com.isk.wifi.R;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.band.WiFiBand;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WiFiBandAdapterTest {
    @Mock
    private Settings settings;

    private WiFiBandAdapter fixture;

    @Before
    public void setUp() {
        fixture = new WiFiBandAdapter(EnumUtils.values(WiFiBand.class));
    }

    @Test
    public void testIsActive() throws Exception {
        assertFalse(fixture.isActive());
    }

    @Test
    public void testIsActivateWithChanges() throws Exception {
        // setup
        fixture.toggle(WiFiBand.GHZ2);
        // execute & validate
        assertTrue(fixture.isActive());
    }

    @Test
    public void testContains() throws Exception {
        IterableUtils.forEach(EnumUtils.values(WiFiBand.class), new ContainsClosure());
    }

    @Test
    public void testToggleRemoves() throws Exception {
        // execute
        boolean actual = fixture.toggle(WiFiBand.GHZ2);
        // validate
        assertTrue(actual);
        assertFalse(fixture.contains(WiFiBand.GHZ2));
    }

    @Test
    public void testToggleAdds() throws Exception {
        // setup
        fixture.toggle(WiFiBand.GHZ5);
        // execute
        boolean actual = fixture.toggle(WiFiBand.GHZ5);
        // validate
        assertTrue(actual);
        assertTrue(fixture.contains(WiFiBand.GHZ5));
    }

    @Test
    public void testRemovingAllWillNotRemoveLast() throws Exception {
        // setup
        Set<WiFiBand> values = EnumUtils.values(WiFiBand.class);
        // execute
        IterableUtils.forEach(values, new ToggleClosure());
        // validate
        IterableUtils.forEachButLast(values, new RemovedClosure());
        assertTrue(fixture.contains(IterableUtils.get(values, values.size() - 1)));
    }

    @Test
    public void testGetColorWithExisting() throws Exception {
        // execute & validate
        assertEquals(R.color.connected, fixture.getColor(WiFiBand.GHZ2));
    }

    @Test
    public void testGetColorWithNonExisting() throws Exception {
        // setup
        fixture.toggle(WiFiBand.GHZ2);
        // execute & validate
        assertEquals(R.color.icons_color, fixture.getColor(WiFiBand.GHZ2));
    }

    @Test
    public void testSave() throws Exception {
        // setup
        Set<WiFiBand> expected = fixture.getValues();
        // execute
        fixture.save(settings);
        // execute
        verify(settings).saveWiFiBands(expected);
    }

    private class ContainsClosure implements Closure<WiFiBand> {
        @Override
        public void execute(WiFiBand wiFiBand) {
            assertTrue(fixture.contains(wiFiBand));
        }
    }

    private class ToggleClosure implements Closure<WiFiBand> {
        @Override
        public void execute(WiFiBand input) {
            fixture.toggle(input);
        }
    }

    private class RemovedClosure implements Closure<WiFiBand> {
        @Override
        public void execute(WiFiBand input) {
            assertFalse(fixture.contains(input));
        }
    }
}