/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.accesspoint;

import com.isk.wifi.R;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccessPointViewTypeTest {
    @Test
    public void testAccessPointViewCount() throws Exception {
        assertEquals(2, AccessPointViewType.values().length);
    }

    @Test
    public void testGetLayout() throws Exception {
        assertEquals(R.layout.access_point_view_complete, AccessPointViewType.COMPLETE.getLayout());
        assertEquals(R.layout.access_point_view_compact, AccessPointViewType.COMPACT.getLayout());
    }

    @Test
    public void testIsFull() throws Exception {
        assertTrue(AccessPointViewType.COMPLETE.isFull());
        assertFalse(AccessPointViewType.COMPACT.isFull());
    }

    @Test
    public void testIsCompact() throws Exception {
        assertFalse(AccessPointViewType.COMPLETE.isCompact());
        assertTrue(AccessPointViewType.COMPACT.isCompact());
    }

}