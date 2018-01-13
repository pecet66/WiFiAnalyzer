/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.wifi.wifi.accesspoint;

import android.support.annotation.NonNull;
import android.widget.ExpandableListView;

import com.isk.wifi.MainContext;
import com.isk.wifi.settings.Settings;
import com.isk.wifi.wifi.model.WiFiData;
import com.isk.wifi.wifi.model.WiFiDetail;
import com.isk.wifi.wifi.predicate.FilterPredicate;

import org.apache.commons.collections4.Predicate;

import java.util.Collections;
import java.util.List;

class AccessPointsAdapterData {
    private AccessPointsAdapterGroup accessPointsAdapterGroup;
    private List<WiFiDetail> wiFiDetails;

    AccessPointsAdapterData() {
        wiFiDetails = Collections.emptyList();
        setAccessPointsAdapterGroup(new AccessPointsAdapterGroup());
    }

    void update(@NonNull WiFiData wiFiData, ExpandableListView expandableListView) {
        Settings settings = MainContext.INSTANCE.getSettings();
        Predicate<WiFiDetail> predicate = FilterPredicate.makeAccessPointsPredicate(settings);
        wiFiDetails = wiFiData.getWiFiDetails(predicate, settings.getSortBy(), settings.getGroupBy());
        accessPointsAdapterGroup.update(wiFiDetails, expandableListView);
    }

    int parentsCount() {
        return wiFiDetails.size();
    }

    private boolean validParentIndex(int index) {
        return index >= 0 && index < parentsCount();
    }

    private boolean validChildrenIndex(int indexParent, int indexChild) {
        return validParentIndex(indexParent) && indexChild >= 0 && indexChild < childrenCount(indexParent);
    }

    WiFiDetail parent(int index) {
        return validParentIndex(index) ? wiFiDetails.get(index) : WiFiDetail.EMPTY;
    }

    int childrenCount(int index) {
        return validParentIndex(index) ? wiFiDetails.get(index).getChildren().size() : 0;
    }

    WiFiDetail child(int indexParent, int indexChild) {
        return validChildrenIndex(indexParent, indexChild) ? wiFiDetails.get(indexParent).getChildren().get(indexChild) : WiFiDetail.EMPTY;
    }

    void onGroupCollapsed(int groupPosition) {
        accessPointsAdapterGroup.onGroupCollapsed(wiFiDetails, groupPosition);
    }

    void onGroupExpanded(int groupPosition) {
        accessPointsAdapterGroup.onGroupExpanded(wiFiDetails, groupPosition);
    }

    void setAccessPointsAdapterGroup(@NonNull AccessPointsAdapterGroup accessPointsAdapterGroup) {
        this.accessPointsAdapterGroup = accessPointsAdapterGroup;
    }

    List<WiFiDetail> getWiFiDetails() {
        return wiFiDetails;
    }
}
