/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import android.view.Menu;
import android.view.MenuItem;

import com.isk.wifi.MainActivity;
import com.isk.wifi.MainContextHelper;
import com.isk.wifi.R;
import com.isk.wifi.menu.OptionMenu;
import com.isk.wifi.wifi.scanner.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScannerSwitchOnTest {

    @Mock
    private MainActivity mainActivity;
    @Mock
    private OptionMenu optionMenu;
    @Mock
    private Menu menu;
    @Mock
    private MenuItem menuItem;

    private Scanner scanner;
    private ScannerSwitchOn fixture;

    @Before
    public void setUp() {
        scanner = MainContextHelper.INSTANCE.getScanner();
        fixture = new ScannerSwitchOn();
    }

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
    }


    @Test
    public void testApplySetMenuItemVisibleTrue() throws Exception {
        // setup
        withMenuItem();
        // execute
        fixture.apply(mainActivity);
        // validate
        verifyMenuItem();
        verify(menuItem).setVisible(true);
    }

    @Test
    public void testApplyWithScannerRunningUpdateMenuItemIconAndTitle() throws Exception {
        // setup
        when(scanner.isRunning()).thenReturn(true);
        withMenuItem();
        // execute
        fixture.apply(mainActivity);
        // validate
        verifyMenuItem();
        verify(scanner).isRunning();
        verify(menuItem).setTitle(R.string.action_pause);
        verify(menuItem).setIcon(R.drawable.ic_pause_grey_500_48dp);
    }

    @Test
    public void testApplyWithScannerNotRunningUpdateMenuItemIconAndTitle() throws Exception {
        // setup
        when(scanner.isRunning()).thenReturn(false);
        withMenuItem();
        // execute
        fixture.apply(mainActivity);
        // validate
        verifyMenuItem();
        verify(scanner).isRunning();
        verify(menuItem).setTitle(R.string.action_resume);
        verify(menuItem).setIcon(R.drawable.ic_play_arrow_grey_500_48dp);
    }

    private void verifyMenuItem() {
        verify(mainActivity).getOptionMenu();
        verify(optionMenu).getMenu();
        verify(menu).findItem(R.id.action_scanner);
    }

    private void withMenuItem() {
        when(mainActivity.getOptionMenu()).thenReturn(optionMenu);
        when(optionMenu.getMenu()).thenReturn(menu);
        when(menu.findItem(R.id.action_scanner)).thenReturn(menuItem);
    }

    @Test
    public void testApplyWithNoMenuDoesNotSetVisibleTrue() throws Exception {
        // setup
        when(mainActivity.getOptionMenu()).thenReturn(optionMenu);
        when(optionMenu.getMenu()).thenReturn(null);
        // execute
        fixture.apply(mainActivity);
        // validate
        verify(mainActivity).getOptionMenu();
        verify(optionMenu).getMenu();
        verify(menu, never()).findItem(R.id.action_scanner);
        verify(menuItem, never()).setVisible(true);
    }

}