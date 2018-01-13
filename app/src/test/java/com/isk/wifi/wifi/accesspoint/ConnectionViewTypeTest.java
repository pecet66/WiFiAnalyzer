/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.accesspoint;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ConnectionViewTypeTest {
    @Test
    public void testConnectionViewTypeCount() throws Exception {
        assertEquals(3, ConnectionViewType.values().length);
    }

    @Test
    public void testGetLayout() throws Exception {
        assertEquals(AccessPointViewType.COMPLETE, ConnectionViewType.COMPLETE.getAccessPointViewType());
        assertEquals(AccessPointViewType.COMPACT, ConnectionViewType.COMPACT.getAccessPointViewType());
        assertNull(ConnectionViewType.HIDE.getAccessPointViewType());
    }

    @Test
    public void testIsHide() throws Exception {
        assertFalse(ConnectionViewType.COMPLETE.isHide());
        assertFalse(ConnectionViewType.COMPACT.isHide());
        assertTrue(ConnectionViewType.HIDE.isHide());
    }

}