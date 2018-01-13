/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.isk.wifi.R;
import com.isk.wifi.wifi.filter.adapter.SSIDAdapter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class SSIDFilter {
    private static final String SEPARATOR = " ";

    SSIDFilter(@NonNull SSIDAdapter ssidAdapter, @NonNull Dialog dialog) {
        String value = TextUtils.join(SEPARATOR, ssidAdapter.getValues().toArray());

        EditText editText = dialog.findViewById(R.id.filterSSIDtext);
        editText.setText(value);
        editText.addTextChangedListener(new OnChange(ssidAdapter));

        dialog.findViewById(R.id.filterSSID).setVisibility(View.VISIBLE);
    }

    static class OnChange implements TextWatcher {
        private final SSIDAdapter ssidAdapter;

        OnChange(@NonNull SSIDAdapter ssidAdapter) {
            this.ssidAdapter = ssidAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Do nothing
        }

        @Override
        public void afterTextChanged(Editable s) {
            Set<String> values = (s == null)
                ? new HashSet<String>()
                : new HashSet<>(Arrays.asList(s.toString().split(SEPARATOR)));
            ssidAdapter.setValues(values);
        }
    }
}
