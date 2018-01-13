/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter;

import android.app.Dialog;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.isk.wifi.R;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.wifi.filter.adapter.SSIDAdapter;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(RobolectricTestRunner.class)
public class SSIDFilterTest {

    private Dialog dialog;
    private EditText editText;
    private View view;
    private SSIDAdapter ssidAdapter;
    private Editable editable;

    @Before
    public void setUp() throws Exception {
        RobolectricUtil.INSTANCE.getActivity();

        dialog = mock(Dialog.class);
        editText = mock(EditText.class);
        view = mock(View.class);
        ssidAdapter = mock(SSIDAdapter.class);
        editable = mock(Editable.class);

        when(dialog.findViewById(R.id.filterSSIDtext)).thenReturn(editText);
        when(dialog.findViewById(R.id.filterSSID)).thenReturn(view);
    }

    @Test
    public void testSSIDFilterWithValues() throws Exception {
        // setup
        HashSet<String> values = new HashSet<>(Arrays.asList("", " ", "ABC", " JDS "));
        when(ssidAdapter.getValues()).thenReturn(values);
        String expected = StringUtils.join(values, " ");
        // execute
        new SSIDFilter(ssidAdapter, dialog);
        // verify
        verify(editText).setText(expected);
        verify(dialog).findViewById(R.id.filterSSIDtext);
        verify(dialog).findViewById(R.id.filterSSID);
        verify(view).setVisibility(View.VISIBLE);
        verify(editText).addTextChangedListener(any(SSIDFilter.OnChange.class));
    }

    @Test
    public void testOnChangeAfterTextChangedWithNull() throws Exception {
        // setup
        SSIDFilter.OnChange onChange = new SSIDFilter.OnChange(ssidAdapter);
        // execute
        onChange.afterTextChanged(null);
        // verify
        verify(ssidAdapter).setValues(new HashSet<String>());
    }

    @Test
    public void testOnChangeAfterTextChangedWithValues() throws Exception {
        // setup
        String value = " ABS ADF ";
        SSIDFilter.OnChange onChange = new SSIDFilter.OnChange(ssidAdapter);
        when(editable.toString()).thenReturn(value);
        Set<String> expected = new HashSet<>(Arrays.asList(value.split(" ")));
        // execute
        onChange.afterTextChanged(editable);
        // verify
        verify(ssidAdapter).setValues(expected);
    }
}