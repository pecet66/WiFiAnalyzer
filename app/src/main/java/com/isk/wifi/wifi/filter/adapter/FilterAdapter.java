/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.filter.adapter;

import android.support.annotation.NonNull;

import com.isk.wifi.MainContext;
import com.isk.wifi.navigation.NavigationMenu;
import com.isk.wifi.settings.Settings;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class FilterAdapter {
    private final Settings settings;
    private SSIDAdapter ssidAdapter;
    private WiFiBandAdapter wiFiBandAdapter;
    private StrengthAdapter strengthAdapter;
    private SecurityAdapter securityAdapter;

    public FilterAdapter(@NonNull Settings settings) {
        this.settings = settings;
        reload();
    }

    public boolean isActive() {
        return IterableUtils.find(getFilterAdapters(isAccessPoints()), new ActivePredicate()) != null;
    }

    public void reset() {
        IterableUtils.forEach(getFilterAdapters(isAccessPoints()), new ResetClosure());
    }

    public void save() {
        IterableUtils.forEach(getFilterAdapters(isAccessPoints()), new SaveClosure());
    }

    List<? extends BasicFilterAdapter<? extends Serializable>> getFilterAdapters(boolean accessPoints) {
        if (accessPoints) {
            return Arrays.asList(ssidAdapter, strengthAdapter, securityAdapter, wiFiBandAdapter);
        }
        return Arrays.asList(ssidAdapter, strengthAdapter, securityAdapter);
    }

    public SSIDAdapter getSSIDAdapter() {
        return ssidAdapter;
    }

    public WiFiBandAdapter getWiFiBandAdapter() {
        return wiFiBandAdapter;
    }

    public StrengthAdapter getStrengthAdapter() {
        return strengthAdapter;
    }

    public SecurityAdapter getSecurityAdapter() {
        return securityAdapter;
    }

    public void reload() {
        this.ssidAdapter = new SSIDAdapter(settings.getSSIDs());
        this.wiFiBandAdapter = new WiFiBandAdapter(settings.getWiFiBands());
        this.strengthAdapter = new StrengthAdapter(settings.getStrengths());
        this.securityAdapter = new SecurityAdapter(settings.getSecurities());
    }

    private boolean isAccessPoints() {
        return NavigationMenu.ACCESS_POINTS.equals(MainContext.INSTANCE.getMainActivity().getNavigationMenuView().getCurrentNavigationMenu());
    }

    private class ActivePredicate implements Predicate<BasicFilterAdapter<? extends Serializable>> {
        @Override
        public boolean evaluate(BasicFilterAdapter<? extends Serializable> adapter) {
            return adapter.isActive();
        }
    }

    private class ResetClosure implements Closure<BasicFilterAdapter<? extends Serializable>> {
        @Override
        public void execute(BasicFilterAdapter<? extends Serializable> adapter) {
            adapter.reset();
            adapter.save(settings);
        }
    }

    private class SaveClosure implements Closure<BasicFilterAdapter<? extends Serializable>> {
        @Override
        public void execute(BasicFilterAdapter<? extends Serializable> adapter) {
            adapter.save(settings);
        }
    }

}
