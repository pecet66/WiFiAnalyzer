/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter;

import com.isk.util.EnumUtils;
import com.isk.wifi.wifi.model.Security;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SecurityFilterTest {

    @Test
    public void testMapping() throws Exception {
        Set<Security> securities = EnumUtils.values(Security.class);
        assertEquals(securities.size(), SecurityFilter.ids.size());
        IterableUtils.forEach(securities, new MappingClosure());
    }

    private static class MappingClosure implements Closure<Security> {
        @Override
        public void execute(Security security) {
            assertNotNull(SecurityFilter.ids.get(security));
        }
    }
}