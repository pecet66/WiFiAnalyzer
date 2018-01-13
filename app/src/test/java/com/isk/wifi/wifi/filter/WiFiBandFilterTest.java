/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter;

import com.isk.util.EnumUtils;
import com.isk.wifi.wifi.band.WiFiBand;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WiFiBandFilterTest {

    @Test
    public void testMapping() throws Exception {
        Set<WiFiBand> wiFiBands = EnumUtils.values(WiFiBand.class);
        assertEquals(wiFiBands.size(), WiFiBandFilter.ids.size());
        IterableUtils.forEach(wiFiBands, new MappingClosure());
    }

    private static class MappingClosure implements Closure<WiFiBand> {
        @Override
        public void execute(WiFiBand wiFiBand) {
            assertNotNull(WiFiBandFilter.ids.get(wiFiBand));
        }
    }
}