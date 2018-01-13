/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.band;

import org.apache.commons.collections4.Predicate;

public class FrequencyPredicate implements Predicate<WiFiBand> {
    private final int frequency;

    public FrequencyPredicate(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean evaluate(WiFiBand object) {
        return object.getWiFiChannels().isInRange(frequency);
    }

}
