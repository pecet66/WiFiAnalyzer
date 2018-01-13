/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.model;

import com.isk.wifi.R;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StrengthTest {

    @Test
    public void testStrength() throws Exception {
        assertEquals(5, Strength.values().length);
    }

    @Test
    public void testImageResource() throws Exception {
        assertEquals(R.drawable.ic_signal_wifi_0_bar_black_36dp, Strength.ZERO.imageResource());
        assertEquals(R.drawable.ic_signal_wifi_1_bar_black_36dp, Strength.ONE.imageResource());
        assertEquals(R.drawable.ic_signal_wifi_2_bar_black_36dp, Strength.TWO.imageResource());
        assertEquals(R.drawable.ic_signal_wifi_3_bar_black_36dp, Strength.THREE.imageResource());
        assertEquals(R.drawable.ic_signal_wifi_4_bar_black_36dp, Strength.FOUR.imageResource());
    }

    @Test
    public void testColorResource() throws Exception {
        assertEquals(R.color.error_color, Strength.ZERO.colorResource());
        assertEquals(R.color.warning_color, Strength.ONE.colorResource());
        assertEquals(R.color.warning_color, Strength.TWO.colorResource());
        assertEquals(R.color.success_color, Strength.THREE.colorResource());
        assertEquals(R.color.success_color, Strength.FOUR.colorResource());
    }

    @Test
    public void testColorResourceDefault() throws Exception {
        assertEquals(R.color.icons_color, Strength.ZERO.colorResourceDefault());
        assertEquals(R.color.icons_color, Strength.ONE.colorResourceDefault());
        assertEquals(R.color.icons_color, Strength.TWO.colorResourceDefault());
        assertEquals(R.color.icons_color, Strength.THREE.colorResourceDefault());
        assertEquals(R.color.icons_color, Strength.FOUR.colorResourceDefault());
    }

    @Test
    public void testWeak() throws Exception {
        assertTrue(Strength.ZERO.weak());
        assertFalse(Strength.ONE.weak());
        assertFalse(Strength.TWO.weak());
        assertFalse(Strength.THREE.weak());
        assertFalse(Strength.FOUR.weak());
    }

    @Test
    public void testCalculate() throws Exception {
        assertEquals(Strength.ZERO, Strength.calculate(-89));

        assertEquals(Strength.ONE, Strength.calculate(-88));
        assertEquals(Strength.ONE, Strength.calculate(-78));

        assertEquals(Strength.TWO, Strength.calculate(-77));
        assertEquals(Strength.TWO, Strength.calculate(-67));

        assertEquals(Strength.THREE, Strength.calculate(-66));
        assertEquals(Strength.THREE, Strength.calculate(-56));

        assertEquals(Strength.FOUR, Strength.calculate(-55));
        assertEquals(Strength.FOUR, Strength.calculate(0));
    }

    @Test
    public void testReverse() throws Exception {
        assertEquals(Strength.FOUR, Strength.reverse(Strength.ZERO));
        assertEquals(Strength.THREE, Strength.reverse(Strength.ONE));
        assertEquals(Strength.TWO, Strength.reverse(Strength.TWO));
        assertEquals(Strength.ONE, Strength.reverse(Strength.THREE));
        assertEquals(Strength.ZERO, Strength.reverse(Strength.FOUR));
    }

}