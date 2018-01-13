/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter;

import com.isk.util.EnumUtils;
import com.isk.wifi.wifi.model.Strength;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StrengthFilterTest {

    @Test
    public void testMapping() throws Exception {
        Set<Strength> strengths = EnumUtils.values(Strength.class);
        assertEquals(strengths.size(), StrengthFilter.ids.size());
        IterableUtils.forEach(strengths, new MappingClosure());
    }

    private static class MappingClosure implements Closure<Strength> {
        @Override
        public void execute(Strength strength) {
            assertNotNull(StrengthFilter.ids.get(strength));
        }
    }
}