/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.scanner;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.isk.wifi.settings.Settings;

class PeriodicScan implements Runnable {
    static final int DELAY_INITIAL = 1;
    static final int DELAY_INTERVAL = 1000;

    private final Scanner scanner;
    private final Handler handler;
    private final Settings settings;
    private boolean running;

    PeriodicScan(@NonNull Scanner scanner, @NonNull Handler handler, @NonNull Settings settings) {
        this.scanner = scanner;
        this.handler = handler;
        this.settings = settings;
        start();
    }

    void stop() {
        handler.removeCallbacks(this);
        running = false;
    }

    void start() {
        nextRun(DELAY_INITIAL);
    }

    private void nextRun(int delayInitial) {
        stop();
        handler.postDelayed(this, delayInitial);
        running = true;
    }

    @Override
    public void run() {
        scanner.update();
        nextRun(settings.getScanInterval() * DELAY_INTERVAL);
    }

    boolean isRunning() {
        return running;
    }
}
