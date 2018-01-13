/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.navigation.options;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FilterOnTest {
/*

    @Mock
    private MainActivity mainActivity;
    @Mock
    private OptionMenu optionMenu;
    @Mock
    private Menu menu;
    @Mock
    private MenuItem menuItem;

    private FilterAdapter filterAdapter;
    private FilterOn fixture;

    @Before
    public void setUp() {
        filterAdapter = MainContextHelper.INSTANCE.getFilterAdapter();
        fixture = new FilterOn();
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
    public void testApplyWithFilterInactive() throws Exception {
        // setup
        when(filterAdapter.isActive()).thenReturn(false);
        withMenuItem();
        // execute
        fixture.apply(mainActivity);
        // validate
        verifyMenuItem();
        verify(filterAdapter).isActive();
        verify(menuItem).setIcon(R.drawable.ic_filter_list_grey_500_48dp);
    }

    @Test
    public void testApplyWithFilterActive() throws Exception {
        // setup
        when(filterAdapter.isActive()).thenReturn(true);
        withMenuItem();
        // execute
        fixture.apply(mainActivity);
        // validate
        verifyMenuItem();
        verify(filterAdapter).isActive();
        verify(menuItem).setIcon(R.drawable.ic_filter_list_blue_500_48dp);
    }

    private void verifyMenuItem() {
        verify(mainActivity).getOptionMenu();
        verify(optionMenu).getMenu();
        verify(menu).findItem(R.id.action_filter);
    }

    private void withMenuItem() {
        when(mainActivity.getOptionMenu()).thenReturn(optionMenu);
        when(optionMenu.getMenu()).thenReturn(menu);
        when(menu.findItem(R.id.action_filter)).thenReturn(menuItem);
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
        verify(menu, never()).findItem(R.id.action_filter);
        verify(menuItem, never()).setVisible(true);
    }
*/

}