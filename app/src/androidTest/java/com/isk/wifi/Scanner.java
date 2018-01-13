/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi;


import android.support.test.espresso.ViewInteraction;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.isk.wifi.InstrumentedUtils.ChildAtPosition;
import static com.isk.wifi.InstrumentedUtils.pauseLong;
import static com.isk.wifi.InstrumentedUtils.pauseShort;
import static org.hamcrest.Matchers.allOf;

class Scanner implements Runnable {
    private static final int SCANNER_BUTTON = 2;
    private static final int SCANNER_ACTION = 1;
    private static final String SCANNER_PAUSE_TAG = "Pause";
    private static final String SCANNER_RESUME_TAG = "Resume";

    @Override
    public void run() {
        scannerAction(SCANNER_PAUSE_TAG);
        pauseLong();
        scannerAction(SCANNER_RESUME_TAG);
    }

    private void scannerAction(String tag) {
        pauseShort();
        ViewInteraction actionMenuItemView = onView(
            allOf(withId(R.id.action_scanner), withContentDescription(tag),
                new ChildAtPosition(new ChildAtPosition(withId(R.id.toolbar), SCANNER_BUTTON),
                    SCANNER_ACTION),
                isDisplayed()));
        actionMenuItemView.perform(click());
    }

}
