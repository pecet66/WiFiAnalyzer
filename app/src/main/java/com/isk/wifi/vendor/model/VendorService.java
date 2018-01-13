/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.vendor.model;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class VendorService {
    private static final int MAX_LEN = 6;

    private final Set<String> cache;
    private VendorDB vendorDB;

    public VendorService(@NonNull Resources resources) {
        this.cache = new TreeSet<>();
        this.vendorDB = new VendorDB(resources);
    }

    public String findVendorName(@NonNull String macAddress) {
        String vendorName = vendorDB.findVendorName(clean(macAddress));
        if (vendorName != null) {
            cache.add(vendorName);
            return vendorName;
        }
        return StringUtils.EMPTY;
    }

    public List<String> findVendors() {
        return new ArrayList<>(cache);
    }

    public List<String> findMacAddresses(String vendorName) {
        List<String> macAddresses = vendorDB.findMacAddresses(vendorName);
        return macAddresses == null ? Collections.<String>emptyList() : macAddresses;
    }

    String clean(@NonNull String macAddress) {
        String result = macAddress.replace(":", "");
        return result.substring(0, Math.min(result.length(), MAX_LEN)).toUpperCase();
    }

    void setVendorDB(VendorDB vendorDB) {
        this.vendorDB = vendorDB;
    }
}
