/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.vendor.model;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import com.isk.wifi.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class VendorDB {
    private static final int SIZE = 6;
    private final Resources resources;
    private Map<String, List<String>> vendors;
    private Map<String, String> macs;

    VendorDB(@NonNull Resources resources) {
        this.resources = resources;
    }

    String findVendorName(String address) {
        load();
        return macs.get(address);
    }

    List<String> findMacAddresses(String vendorName) {
        load();
        return vendors.get(vendorName);
    }

    Map<String, String> getMacs() {
        load();
        return macs;
    }

    Map<String, List<String>> getVendors() {
        load();
        return vendors;
    }

    private String[] readFile(@NonNull Resources resources, @RawRes int id) {
        InputStream inputStream = null;
        try {
            inputStream = resources.openRawResource(id);
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            int count = inputStream.read(bytes);
            if (count != size) {
                return new String[]{};
            }
            return new String(bytes).split("\n");
        } catch (Exception e) {
            // file is corrupted
            return new String[]{};
        } finally {
            close(inputStream);
        }
    }

    private void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
                // do nothing
            }
        }
    }

    private void load() {
        if (vendors != null) {
            return;
        }
        vendors = new HashMap<>();
        macs = new HashMap<>();
        String[] lines = readFile(resources, R.raw.data);
        for (String data : lines) {
            if (data != null) {
                String[] parts = data.split("\\|");
                if (parts.length == 2) {
                    List<String> addresses = new ArrayList<>();
                    String name = parts[0];
                    vendors.put(name, addresses);
                    int length = parts[1].length();
                    for (int i = 0; i < length; i += SIZE) {
                        String mac = parts[1].substring(i, i + SIZE);
                        addresses.add(mac);
                        macs.put(mac, name);
                    }
                }
            }
        }
    }

}
