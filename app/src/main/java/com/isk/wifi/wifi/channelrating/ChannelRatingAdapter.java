/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.channelrating;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.isk.wifi.MainContext;
import com.isk.wifi.R;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.band.WiFiChannel;
import com.isk.wifi.wifi.model.ChannelAPCount;
import com.isk.wifi.wifi.model.ChannelRating;
import com.isk.wifi.wifi.model.SortBy;
import com.isk.wifi.wifi.model.Strength;
import com.isk.wifi.wifi.model.WiFiData;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.predicate.WiFiBandPredicate;
import com.isk.wifi.wifi.scanner.UpdateNotifier;

import org.apache.commons.collections4.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class ChannelRatingAdapter extends ArrayAdapter<WiFiChannel> implements UpdateNotifier {
    private static final int MAX_CHANNELS_TO_DISPLAY = 10;

    private final TextView bestChannels;
    private ChannelRating channelRating;

    ChannelRatingAdapter(@NonNull Context context, @NonNull TextView bestChannels) {
        super(context, R.layout.channel_rating_details, new ArrayList<WiFiChannel>());
        this.bestChannels = bestChannels;
        setChannelRating(new ChannelRating());
    }

    void setChannelRating(@NonNull ChannelRating channelRating) {
        this.channelRating = channelRating;
    }

    @Override
    public void update(@NonNull WiFiData wiFiData) {
        Settings settings = MainContext.INSTANCE.getSettings();
        WiFiBand wiFiBand = settings.getWiFiBand();
        List<WiFiChannel> wiFiChannels = setWiFiChannels(wiFiBand);
        Predicate<WiFiDetail> predicate = new WiFiBandPredicate(wiFiBand);
        List<WiFiDetail> wiFiDetails = wiFiData.getWiFiDetails(predicate, SortBy.STRENGTH);
        channelRating.setWiFiDetails(wiFiDetails);
        bestChannels(wiFiBand, wiFiChannels);
        notifyDataSetChanged();
    }

    private List<WiFiChannel> setWiFiChannels(WiFiBand wiFiBand) {
        Settings settings = MainContext.INSTANCE.getSettings();
        String countryCode = settings.getCountryCode();
        List<WiFiChannel> wiFiChannels = wiFiBand.getWiFiChannels().getAvailableChannels(countryCode);
        clear();
        addAll(wiFiChannels);
        return wiFiChannels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = MainContext.INSTANCE.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.channel_rating_details, parent, false);
        }

        WiFiChannel wiFiChannel = getItem(position);
        if (wiFiChannel == null) {
            return view;
        }

        ((TextView) view.findViewById(R.id.channelNumber))
            .setText(String.format(Locale.ENGLISH, "%d", wiFiChannel.getChannel()));
        ((TextView) view.findViewById(R.id.accessPointCount))
            .setText(String.format(Locale.ENGLISH, "%d", channelRating.getCount(wiFiChannel)));
        Strength strength = Strength.reverse(channelRating.getStrength(wiFiChannel));
        //RatingBar ratingBar = view.findViewById(R.id.channelRating);
        //int size = Strength.values().length;
        //ratingBar.setMax(size);
        //ratingBar.setNumStars(size);
        //ratingBar.setRating(strength.ordinal() + 1);
        //int color = ContextCompat.getColor(getContext(), strength.colorResource());
        //if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        //    setRatingBarColor(ratingBar.getProgressDrawable(), color);
        //} else {
        //    ratingBar.setProgressTintList(ColorStateList.valueOf(color));
        //}

        return view;
    }

    private void setRatingBarColor(Drawable drawable, int color) {
        try {
            int background = ContextCompat.getColor(getContext(), R.color.connected_background);
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            layerDrawable.getDrawable(0).setColorFilter(background, PorterDuff.Mode.SRC_ATOP);
            layerDrawable.getDrawable(1).setColorFilter(background, PorterDuff.Mode.SRC_ATOP);
            layerDrawable.getDrawable(2).setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        } catch (Exception e) {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }
    }

    void bestChannels(@NonNull WiFiBand wiFiBand, @NonNull List<WiFiChannel> wiFiChannels) {
        List<ChannelAPCount> channelAPCounts = channelRating.getBestChannels(wiFiChannels);
        int channelCount = 0;
        StringBuilder result = new StringBuilder();
        for (ChannelAPCount channelAPCount : channelAPCounts) {
            if (channelCount > MAX_CHANNELS_TO_DISPLAY) {
                result.append("...");
                break;
            }
            if (result.length() > 0) {
                result.append(", ");
            }
            result.append(channelAPCount.getWiFiChannel().getChannel());
            channelCount++;
        }
        if (result.length() > 0) {
            bestChannels.setText(result.toString());
            bestChannels.setTextColor(ContextCompat.getColor(getContext(), R.color.success_color));
        } else {
            Resources resources = getContext().getResources();
            StringBuilder message = new StringBuilder(resources.getText(R.string.channel_rating_best_none));
            if (WiFiBand.GHZ2.equals(wiFiBand)) {
                message.append(resources.getText(R.string.channel_rating_best_alternative));
                message.append(" ");
                message.append(getContext().getResources().getString(WiFiBand.GHZ5.getTextResource()));
            }
            bestChannels.setText(message);
            bestChannels.setTextColor(ContextCompat.getColor(getContext(), R.color.error_color));
        }
    }

}
