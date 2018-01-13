/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.channelrating;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.isk.wifi.MainActivity;
import com.isk.wifi.MainContextHelper;
import com.isk.wifi.R;
import com.isk.wifi.RobolectricUtil;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.band.WiFiBand;
import com.isk.wifi.wifi.band.WiFiChannel;
import com.isk.wifi.wifi.model.ChannelAPCount;
import com.isk.wifi.wifi.model.ChannelRating;
import com.isk.wifi.wifi.model.SortBy;
import com.isk.wifi.wifi.model.Strength;
import com.isk.wifi.wifi.model.WiFiConnection;
import com.isk.wifi.wifi.model.WiFiData;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.predicate.WiFiBandPredicate;

import org.apache.commons.collections4.Predicate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class ChannelRatingAdapterTest {

    private ChannelRatingAdapter fixture;
    private Settings settings;
    private ChannelRating channelRating;
    private TextView bestChannels;
    private MainActivity mainActivity;

    @Before
    public void setUp() {
        mainActivity = RobolectricUtil.INSTANCE.getActivity();

        channelRating = mock(ChannelRating.class);
        bestChannels = new TextView(mainActivity);
        settings = MainContextHelper.INSTANCE.getSettings();

        fixture = new ChannelRatingAdapter(mainActivity, bestChannels);
        fixture.setChannelRating(channelRating);
    }

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testGetView() throws Exception {
        // setup
        int expectedSize = Strength.values().length;
        Strength expectedStrength = Strength.reverse(Strength.FOUR);
        WiFiChannel wiFiChannel = new WiFiChannel(1, 2);
        fixture.add(wiFiChannel);
        when(channelRating.getCount(wiFiChannel)).thenReturn(5);
        when(channelRating.getStrength(wiFiChannel)).thenReturn(Strength.FOUR);
        ViewGroup viewGroup = mainActivity.findViewById(android.R.id.content);
        // execute
        View actual = fixture.getView(0, null, viewGroup);
        // validate
        assertNotNull(actual);

        assertEquals("1", ((TextView) actual.findViewById(R.id.channelNumber)).getText());
        assertEquals("5", ((TextView) actual.findViewById(R.id.accessPointCount)).getText());

        RatingBar ratingBar = actual.findViewById(R.id.channelRating);
        assertEquals(expectedSize, ratingBar.getMax());
        assertEquals(expectedSize, ratingBar.getNumStars());
        assertEquals(expectedStrength.ordinal() + 1, (int) ratingBar.getRating());

        assertEquals("", bestChannels.getText());

        verify(channelRating).getCount(wiFiChannel);
        verify(channelRating).getStrength(wiFiChannel);
    }

    @Test
    public void testUpdate() throws Exception {
        // setup
        String expected = mainActivity.getResources().getText(R.string.channel_rating_best_none).toString();
        WiFiData wiFiData = new WiFiData(Collections.<WiFiDetail>emptyList(), WiFiConnection.EMPTY, Collections.<String>emptyList());
        Predicate<WiFiDetail> predicate = new WiFiBandPredicate(WiFiBand.GHZ5);
        List<WiFiDetail> wiFiDetails = wiFiData.getWiFiDetails(predicate, SortBy.STRENGTH);
        when(settings.getWiFiBand()).thenReturn(WiFiBand.GHZ5);
        when(settings.getCountryCode()).thenReturn(Locale.US.getCountry());
        // execute
        fixture.update(wiFiData);
        // validate
        assertEquals(expected, bestChannels.getText());
        verify(channelRating).setWiFiDetails(wiFiDetails);
        verify(settings).getWiFiBand();
        verify(settings).getCountryCode();
    }

    @Test
    public void testBestChannelsGHZ2ErrorMessage() throws Exception {
        // setup
        Resources resources = mainActivity.getResources();
        String expected = resources.getText(R.string.channel_rating_best_none).toString()
            + resources.getText(R.string.channel_rating_best_alternative)
            + " " + resources.getString(WiFiBand.GHZ5.getTextResource());
        List<WiFiChannel> wiFiChannels = Collections.emptyList();
        List<ChannelAPCount> channelAPCounts = Collections.emptyList();
        when(channelRating.getBestChannels(wiFiChannels)).thenReturn(channelAPCounts);
        // execute
        fixture.bestChannels(WiFiBand.GHZ2, wiFiChannels);
        // validate
        assertEquals(expected, bestChannels.getText());
        verify(channelRating).getBestChannels(wiFiChannels);
    }

    @Test
    public void testBestChannelsGHZ5WithErrorMessage() throws Exception {
        // setup
        String expected = mainActivity.getResources().getText(R.string.channel_rating_best_none).toString();
        List<WiFiChannel> wiFiChannels = Collections.emptyList();
        List<ChannelAPCount> channelAPCounts = Collections.emptyList();
        when(channelRating.getBestChannels(wiFiChannels)).thenReturn(channelAPCounts);
        // execute
        fixture.bestChannels(WiFiBand.GHZ5, wiFiChannels);
        // validate
        assertEquals(expected, bestChannels.getText());
        verify(channelRating).getBestChannels(wiFiChannels);
    }

    @Test
    public void testBestChannelsGHZ5WithChannels() throws Exception {
        // setup
        String expected = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11...";
        List<WiFiChannel> wiFiChannels = Collections.emptyList();
        List<ChannelAPCount> channelAPCounts = withChannelAPCounts();
        when(channelRating.getBestChannels(wiFiChannels)).thenReturn(channelAPCounts);
        // execute
        fixture.bestChannels(WiFiBand.GHZ5, wiFiChannels);
        // validate
        assertEquals(expected, bestChannels.getText());
        verify(channelRating).getBestChannels(wiFiChannels);
    }

    @NonNull
    private List<ChannelAPCount> withChannelAPCounts() {
        List<ChannelAPCount> channelAPCounts = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            channelAPCounts.add(new ChannelAPCount(new WiFiChannel(i + 1, i + 100), 0));
        }
        return channelAPCounts;
    }
}