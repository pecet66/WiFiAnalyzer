/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import android.view.Menu;
import android.view.MenuItem;

import com.isk.wifi.MainActivity;
import com.isk.wifi.R;
import com.isk.wifi.menu.OptionMenu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilterOffTest {

    @Mock
    private MainActivity mainActivity;
    @Mock
    private OptionMenu optionMenu;
    @Mock
    private Menu menu;
    @Mock
    private MenuItem menuItem;

    private FilterOff fixture;

    @Before
    public void setUp() {
        fixture = new FilterOff();
    }

    @Test
    public void testApplySetVisibleFalse() throws Exception {
        // setup
        when(mainActivity.getOptionMenu()).thenReturn(optionMenu);
        when(optionMenu.getMenu()).thenReturn(menu);
        when(menu.findItem(R.id.action_filter)).thenReturn(menuItem);
        // execute
        fixture.apply(mainActivity);
        // validate
        verify(mainActivity).getOptionMenu();
        verify(optionMenu).getMenu();
        verify(menu).findItem(R.id.action_filter);
        verify(menuItem).setVisible(false);
    }

    @Test
    public void testApplyWithNoMenuDoesNotSetVisibleFalse() throws Exception {
        // setup
        when(mainActivity.getOptionMenu()).thenReturn(optionMenu);
        when(optionMenu.getMenu()).thenReturn(null);
        // execute
        fixture.apply(mainActivity);
        // validate
        verify(mainActivity).getOptionMenu();
        verify(optionMenu).getMenu();
        verify(menu, never()).findItem(R.id.action_filter);
        verify(menuItem, never()).setVisible(false);
    }

}