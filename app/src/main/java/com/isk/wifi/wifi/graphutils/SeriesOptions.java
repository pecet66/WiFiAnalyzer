/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.graphutils;

import android.support.annotation.NonNull;

import com.jjoe64.graphview.series.BaseSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.TitleLineGraphSeries;

class SeriesOptions {
    private GraphColors graphColors;

    SeriesOptions() {
        setGraphColors(new GraphColors());
    }

    void setGraphColors(@NonNull GraphColors graphColors) {
        this.graphColors = graphColors;
    }

    void highlightConnected(@NonNull BaseSeries<DataPoint> series, boolean connected) {
        if (series instanceof LineGraphSeries) {
            ((LineGraphSeries<DataPoint>) series).setThickness(
                connected ? GraphConstants.THICKNESS_CONNECTED : GraphConstants.THICKNESS_REGULAR);
        } else if (series instanceof TitleLineGraphSeries) {
            ((TitleLineGraphSeries<DataPoint>) series).setThickness(connected
                ? GraphConstants.THICKNESS_CONNECTED : GraphConstants.THICKNESS_REGULAR);
            ((TitleLineGraphSeries<DataPoint>) series).setTextBold(connected);
        }
    }

    void setSeriesColor(@NonNull BaseSeries<DataPoint> series) {
        GraphColor graphColor = graphColors.getColor();
        series.setColor((int) graphColor.getPrimary());
        if (series instanceof LineGraphSeries) {
            ((LineGraphSeries<DataPoint>) series).setBackgroundColor((int) graphColor.getBackground());
        } else if (series instanceof TitleLineGraphSeries) {
            ((TitleLineGraphSeries<DataPoint>) series).setBackgroundColor((int) graphColor.getBackground());
        }
    }

    void drawBackground(@NonNull BaseSeries<DataPoint> series, boolean drawBackground) {
        if (series instanceof LineGraphSeries) {
            ((LineGraphSeries<DataPoint>) series).setDrawBackground(drawBackground);
        }
    }

    void removeSeriesColor(@NonNull BaseSeries<DataPoint> series) {
        graphColors.addColor(series.getColor());
    }

}
