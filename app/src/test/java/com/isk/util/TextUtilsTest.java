/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.util;

import org.junit.Assert;
import org.junit.Test;

public class TextUtilsTest {

    @Test
    public void testTextToHtmlSmall() throws Exception {
        // setup
        int color = 10;
        String text = "ThisIsText";
        String expected = "<font color='" + color + "'><small>" + text + "</small></font>";
        // execute
        String actual = TextUtils.textToHtml(text, color, true);
        // validate
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testTextToHtml() throws Exception {
        // setup
        int color = 10;
        String text = "ThisIsText";
        String expected = "<font color='" + color + "'><strong>" + text + "</strong></font>";
        // execute
        String actual = TextUtils.textToHtml(text, color, false);
        // validate
        Assert.assertEquals(expected, actual);
    }

}