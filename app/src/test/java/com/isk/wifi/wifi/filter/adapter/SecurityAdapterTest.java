/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter.adapter;

import com.isk.util.EnumUtils;
import com.isk.wifi.R;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.model.Security;

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
public class SecurityAdapterTest {
    @Mock
    private Settings settings;

    private SecurityAdapter fixture;

    @Before
    public void setUp() {
        fixture = new SecurityAdapter(EnumUtils.values(Security.class));
    }

    @Test
    public void testIsActive() throws Exception {
        assertFalse(fixture.isActive());
    }

    @Test
    public void testIsActivateWithChanges() throws Exception {
        // setup
        fixture.toggle(Security.WPA);
        // execute & validate
        assertTrue(fixture.isActive());
    }

    @Test
    public void testContains() throws Exception {
        IterableUtils.forEach(EnumUtils.values(Security.class), new ContainsClosure());
    }

    @Test
    public void testToggleRemoves() throws Exception {
        // execute
        boolean actual = fixture.toggle(Security.WEP);
        // validate
        assertTrue(actual);
        assertFalse(fixture.contains(Security.WEP));
    }

    @Test
    public void testToggleAdds() throws Exception {
        // setup
        fixture.toggle(Security.WPA);
        // execute
        boolean actual = fixture.toggle(Security.WPA);
        // validate
        assertTrue(actual);
        assertTrue(fixture.contains(Security.WPA));
    }

    @Test
    public void testRemovingAllWillNotRemoveLast() throws Exception {
        // setup
        Set<Security> values = EnumUtils.values(Security.class);
        // execute
        IterableUtils.forEach(values, new ToggleClosure());
        // validate
        IterableUtils.forEachButLast(values, new RemovedClosure());
        assertTrue(fixture.contains(IterableUtils.get(values, values.size() - 1)));
    }

    @Test
    public void testGetColorWithExisting() throws Exception {
        // execute & validate
        assertEquals(R.color.connected, fixture.getColor(Security.WPA));
    }

    @Test
    public void testGetColorWithNonExisting() throws Exception {
        // setup
        fixture.toggle(Security.WPA);
        // execute & validate
        assertEquals(R.color.icons_color, fixture.getColor(Security.WPA));
    }

    @Test
    public void testSave() throws Exception {
        // setup
        Set<Security> expected = fixture.getValues();
        // execute
        fixture.save(settings);
        // execute
        verify(settings).saveSecurities(expected);
    }

    private class ContainsClosure implements Closure<Security> {
        @Override
        public void execute(Security security) {
            assertTrue(fixture.contains(security));
        }
    }

    private class ToggleClosure implements Closure<Security> {
        @Override
        public void execute(Security input) {
            fixture.toggle(input);
        }
    }

    private class RemovedClosure implements Closure<Security> {
        @Override
        public void execute(Security input) {
            assertFalse(fixture.contains(input));
        }
    }
}