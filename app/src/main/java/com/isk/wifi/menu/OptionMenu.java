/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.menu;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import com.isk.wifi.MainContext;
import com.isk.wifi.R;
import com.isk.wifi.wifi.filter.Filter;

public class OptionMenu {
    private Menu menu;

    public void create(@NonNull Activity activity, Menu menu) {
        activity.getMenuInflater().inflate(R.menu.optionmenu, menu);
        this.menu = menu;
    }

    public void select(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_scanner:
                if (MainContext.INSTANCE.getScanner().isRunning()) {
                    pause();
                } else {
                    resume();
                }
                break;
            case R.id.action_filter:
                Filter.build().show();
                break;
            default:
                // do nothing
                break;
        }
    }

    public void pause() {
        MainContext.INSTANCE.getScanner().pause();
    }

    public void resume() {
        MainContext.INSTANCE.getScanner().resume();
    }

    public Menu getMenu() {
        return menu;
    }

}
