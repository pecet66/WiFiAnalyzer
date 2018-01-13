/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.model;

import android.support.annotation.NonNull;

import com.isk.util.EnumUtils;
import com.isk.wifi.R;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public enum Security {
    NONE(R.drawable.ic_lock_open_black_18dp),
    WPS(R.drawable.ic_lock_outline_black_18dp),
    WEP(R.drawable.ic_lock_outline_black_18dp),
    WPA(R.drawable.ic_lock_black_18dp),
    WPA2(R.drawable.ic_lock_black_18dp);

    private final int imageResource;

    Security(int imageResource) {
        this.imageResource = imageResource;
    }

    public static List<Security> findAll(String capabilities) {
        Set<Security> results = new TreeSet<>();
        if (capabilities != null) {
            String[] values = capabilities.toUpperCase()
                .replace("][", "-").replace("]", "").replace("[", "").split("-");
            for (String value : values) {
                try {
                    results.add(Security.valueOf(value));
                } catch (Exception e) {
                    // skip getCapabilities that are not getSecurity
                }
            }
        }
        return new ArrayList<>(results);
    }

    public static Security findOne(String capabilities) {
        Security result = IterableUtils.find(EnumUtils.values(Security.class), new SecurityPredicate(findAll(capabilities)));
        return result == null ? Security.NONE : result;
    }

    public int getImageResource() {
        return imageResource;
    }

    private static class SecurityPredicate implements Predicate<Security> {
        private final List<Security> securities;

        private SecurityPredicate(@NonNull List<Security> securities) {
            this.securities = securities;
        }

        @Override
        public boolean evaluate(Security security) {
            return securities.contains(security);
        }
    }

}
