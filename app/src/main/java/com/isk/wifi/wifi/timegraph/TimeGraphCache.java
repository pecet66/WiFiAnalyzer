/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.timegraph;

import android.support.annotation.NonNull;

import com.isk.wifi.wifi.graphutils.GraphConstants;
import com.isk.wifi.wifi.model.WiFiDetail;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class TimeGraphCache {
    private final Map<WiFiDetail, Integer> notSeen;

    TimeGraphCache() {
        this.notSeen = new HashMap<>();
    }

    Set<WiFiDetail> active() {
        return new HashSet<>(CollectionUtils.select(notSeen.keySet(), new SeenPredicate()));
    }

    void clear() {
        IterableUtils.forEach(CollectionUtils.select(notSeen.keySet(), new NotSeenPredicate()), new RemoveClosure());
    }

    void add(@NonNull WiFiDetail wiFiDetail) {
        Integer currentCount = notSeen.get(wiFiDetail);
        if (currentCount == null) {
            currentCount = 0;
        }
        currentCount++;
        notSeen.put(wiFiDetail, currentCount);
    }

    void reset(@NonNull WiFiDetail wiFiDetail) {
        Integer currentCount = notSeen.get(wiFiDetail);
        if (currentCount != null) {
            notSeen.put(wiFiDetail, 0);
        }
    }

    Set<WiFiDetail> getWiFiDetails() {
        return notSeen.keySet();
    }

    private class RemoveClosure implements Closure<WiFiDetail> {
        @Override
        public void execute(WiFiDetail wiFiDetail) {
            notSeen.remove(wiFiDetail);
        }
    }

    private class SeenPredicate implements Predicate<WiFiDetail> {
        @Override
        public boolean evaluate(WiFiDetail object) {
            return notSeen.get(object) <= GraphConstants.MAX_NOTSEEN_COUNT;
        }
    }

    private class NotSeenPredicate implements Predicate<WiFiDetail> {
        @Override
        public boolean evaluate(WiFiDetail object) {
            return notSeen.get(object) > GraphConstants.MAX_NOTSEEN_COUNT;
        }
    }

}
