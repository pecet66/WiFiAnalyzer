/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.model;

import com.isk.wifi.R;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SecurityTest {

    @Test
    public void testSecurity() throws Exception {
        assertEquals(5, Security.values().length);
    }

    @Test
    public void testGetImageResource() throws Exception {
        assertEquals(R.drawable.ic_lock_open_black_18dp, Security.NONE.getImageResource());
        assertEquals(R.drawable.ic_lock_outline_black_18dp, Security.WPS.getImageResource());
        assertEquals(R.drawable.ic_lock_outline_black_18dp, Security.WEP.getImageResource());
        assertEquals(R.drawable.ic_lock_black_18dp, Security.WPA.getImageResource());
        assertEquals(R.drawable.ic_lock_black_18dp, Security.WPA2.getImageResource());
    }

    @Test
    public void testFindAll() throws Exception {
        List<Security> expected = Arrays.asList(Security.WPS, Security.WEP, Security.WPA, Security.WPA2);
        assertEquals(expected, Security.findAll("WPA-WPA2-WPA-WEP-WPS-WPA2"));
    }

    @Test
    public void testFindOne() throws Exception {
        assertEquals(Security.NONE, Security.findOne("xyz"));
        assertEquals(Security.NONE, Security.findOne(Security.NONE.name()));
        assertEquals(Security.WPS, Security.findOne(Security.WPS.name()));
        assertEquals(Security.WEP, Security.findOne(Security.WEP.name()));
        assertEquals(Security.WPA, Security.findOne(Security.WPA.name()));
        assertEquals(Security.WPA2, Security.findOne(Security.WPA2.name()));
    }

    @Test
    public void testOrder() throws Exception {
        Security[] expected = {Security.NONE, Security.WPS, Security.WEP, Security.WPA, Security.WPA2};
        assertArrayEquals(expected, Security.values());
    }
}