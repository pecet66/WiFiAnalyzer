/*
 * Copyright (c) ISK Pawel Czarnik Kamil Drag 2017
 */

package com.isk.util;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;

public class TextUtils {
    private TextUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String textToHtml(@NonNull String text, int color, boolean small) {
        return "<font color='" + color + "'><" + (small ? "small" : "strong") +
            ">" + text + "</" + (small ? "small" : "strong") + "></font>";
    }

    @SuppressWarnings("deprecation")
    @NonNull
    public static Spanned fromHtml(@NonNull String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        }
        return Html.fromHtml(text);
    }


}
