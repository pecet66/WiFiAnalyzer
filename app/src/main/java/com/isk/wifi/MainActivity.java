/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.isk.util.ConfigurationUtils;
import com.isk.util.EnumUtils;
import com.isk.wifi.navigation.NavigationMenu;
import com.isk.wifi.navigation.NavigationMenuView;
import com.isk.wifi.settings.Repository;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.accesspoint.ConnectionView;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.band.WiFiChannel;

import java.util.Locale;

import static android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;

public class MainActivity extends AppCompatActivity implements OnSharedPreferenceChangeListener, OnNavigationItemSelectedListener {
    private MainReload mainReload;
    private NavigationMenuView navigationMenuView;
    private NavigationMenu startNavigationMenu;
    //private OptionMenu optionMenu;
    private String currentCountryCode;

    @Override
    protected void attachBaseContext(Context newBase) {
        Locale newLocale = new Settings(new Repository(newBase)).getLanguageLocale();
        Context context = ConfigurationUtils.createContext(newBase, newLocale);
        super.attachBaseContext(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainContext mainContext = MainContext.INSTANCE;
        mainContext.initialize(this, isLargeScreen());

        Settings settings = mainContext.getSettings();
        settings.initializeDefaultValues();

        setTheme(settings.getThemeStyle().themeAppCompatStyle());
        setWiFiChannelPairs(mainContext);

        mainReload = new MainReload(settings);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        settings.registerOnSharedPreferenceChangeListener(this);

        //setOptionMenu(new OptionMenu());

        Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setOnClickListener(new WiFiBandToggle());
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        startNavigationMenu = settings.getStartMenu();
        navigationMenuView = new NavigationMenuView(this, startNavigationMenu);
        onNavigationItemSelected(navigationMenuView.getCurrentMenuItem());

        ConnectionView connectionView = new ConnectionView(this);
        mainContext.getScanner().register(connectionView);
    }

    private void setWiFiChannelPairs(MainContext mainContext) {
        Settings settings = mainContext.getSettings();
        String countryCode = settings.getCountryCode();
        if (!countryCode.equals(currentCountryCode)) {
            Pair<WiFiChannel, WiFiChannel> pair = WiFiBand.GHZ5.getWiFiChannels().getWiFiChannelPairFirst(countryCode);
            mainContext.getConfiguration().setWiFiChannelPair(pair);
            currentCountryCode = countryCode;
        }
    }

    private boolean isLargeScreen() {
        Configuration configuration = getResources().getConfiguration();
        int screenLayoutSize = configuration.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        return screenLayoutSize == Configuration.SCREENLAYOUT_SIZE_LARGE ||
            screenLayoutSize == Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        MainContext mainContext = MainContext.INSTANCE;
        if (mainReload.shouldReload(mainContext.getSettings())) {
            reloadActivity();
        } else {
            setWiFiChannelPairs(mainContext);
            update();
        }
    }

    public void update() {
        MainContext.INSTANCE.getScanner().update();
        updateActionBar();
    }

    private void reloadActivity() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP |
            Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (!closeDrawer()) {
            if (startNavigationMenu.equals(navigationMenuView.getCurrentNavigationMenu())) {
                super.onBackPressed();
            } else {
                navigationMenuView.setCurrentNavigationMenu(startNavigationMenu);
                onNavigationItemSelected(navigationMenuView.getCurrentMenuItem());
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        try {
            closeDrawer();
            NavigationMenu navigationMenu = EnumUtils.find(NavigationMenu.class, menuItem.getItemId(), NavigationMenu.ACCESS_POINTS);
            navigationMenu.activateNavigationMenu(this, menuItem);
        } catch (Exception e) {
            reloadActivity();
        }
        return true;
    }

    private boolean closeDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    @Override
    protected void onPause() {
        //optionMenu.pause();
        updateActionBar();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //optionMenu.resume();
        updateActionBar();
    }

    @Override
    protected void onStop() {
        MainContext.INSTANCE.getScanner().setWiFiOnExit();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //optionMenu.create(this, menu);
        updateActionBar();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //optionMenu.select(item);
        updateActionBar();
        return true;
    }

    public void updateActionBar() {
        navigationMenuView.getCurrentNavigationMenu().activateOptions(this);
    }

    public NavigationMenuView getNavigationMenuView() {
        return navigationMenuView;
    }

    /*public OptionMenu getOptionMenu() {
        return optionMenu;
    }*/

    /*void setOptionMenu(@NonNull OptionMenu optionMenu) {
        this.optionMenu = optionMenu;
    }*/

    /*private class WiFiBandToggle implements OnClickListener {
        @Override
        public void onClick(View view) {
            if (navigationMenuView.getCurrentNavigationMenu().isWiFiBandSwitchable()) {
                MainContext.INSTANCE.getSettings().toggleWiFiBand();
            }
        }
    }*/

}
