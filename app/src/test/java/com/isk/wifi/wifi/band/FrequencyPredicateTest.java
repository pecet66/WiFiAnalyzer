/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.band;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FrequencyPredicateTest {

    @Test
    public void testWiFiBandPredicate() throws Exception {
        assertFalse(new FrequencyPredicate(2399).evaluate(WiFiBand.GHZ2));
        assertTrue(new FrequencyPredicate(2400).evaluate(WiFiBand.GHZ2));
        assertTrue(new FrequencyPredicate(2499).evaluate(WiFiBand.GHZ2));
        assertFalse(new FrequencyPredicate(2500).evaluate(WiFiBand.GHZ2));

        assertFalse(new FrequencyPredicate(4899).evaluate(WiFiBand.GHZ5));
        assertTrue(new FrequencyPredicate(4900).evaluate(WiFiBand.GHZ5));
        assertTrue(new FrequencyPredicate(5899).evaluate(WiFiBand.GHZ5));
        assertFalse(new FrequencyPredicate(5900).evaluate(WiFiBand.GHZ5));
    }

}