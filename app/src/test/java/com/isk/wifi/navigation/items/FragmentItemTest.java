/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.items;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.isk.wifi.MainActivity;
import com.isk.wifi.R;
import com.isk.wifi.navigation.NavigationMenu;
import com.isk.wifi.navigation.NavigationMenuView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FragmentItemTest {

    @Mock
    private Fragment fragment;
    @Mock
    private MainActivity mainActivity;
    @Mock
    private MenuItem menuItem;
    @Mock
    private NavigationMenuView navigationMenuView;
    @Mock
    private FragmentManager fragmentManager;
    @Mock
    private FragmentTransaction fragmentTransaction;

    @Test
    public void testActivate() throws Exception {
        // setup
        FragmentItem fixture = new FragmentItem(fragment);
        String title = "title";
        NavigationMenu navigationMenu = NavigationMenu.ACCESS_POINTS;
        withFragmentTransaction();
        when(mainActivity.getNavigationMenuView()).thenReturn(navigationMenuView);
        when(menuItem.getTitle()).thenReturn(title);
        // execute
        fixture.activate(mainActivity, menuItem, navigationMenu);
        // validate
        verifyFragmentTransaction();
        verify(navigationMenuView).setCurrentNavigationMenu(navigationMenu);
        verify(mainActivity).setTitle(title);
        verify(mainActivity).updateActionBar();
    }

    @Test
    public void testIsRegisteredFalse() throws Exception {
        // setup
        FragmentItem fixture = new FragmentItem(fragment);
        // execute & validate
        assertFalse(fixture.isRegistered());
    }

    @Test
    public void testIsRegisteredTrue() throws Exception {
        // setup
        FragmentItem fixture = new FragmentItem(fragment, true);
        // execute & validate
        assertTrue(fixture.isRegistered());
    }

    private void withFragmentTransaction() {
        when(mainActivity.getSupportFragmentManager()).thenReturn(fragmentManager);
        when(fragmentManager.beginTransaction()).thenReturn(fragmentTransaction);
        when(fragmentTransaction.replace(R.id.main_fragment, fragment)).thenReturn(fragmentTransaction);
    }

    private void verifyFragmentTransaction() {
        verify(mainActivity).getSupportFragmentManager();
        verify(fragmentManager).beginTransaction();
        verify(fragmentTransaction).replace(R.id.main_fragment, fragment);
        verify(fragmentTransaction).commit();
    }
}