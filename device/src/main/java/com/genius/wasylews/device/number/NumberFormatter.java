package com.genius.wasylews.device.number;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberFormatter {

    private static final int NUMBER_SIZE = 10;

    public static String format(String number) {
        if (TextUtils.isEmpty(number) || number.length() != NUMBER_SIZE) {
            return number;
        }

        Pattern pattern = Pattern.compile("(\\d{3})(\\d{2})(\\d{3})(\\d{2})");
        Matcher matcher = pattern.matcher(number);
        List<String> parts = new ArrayList<>();

        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                parts.add(matcher.group(i));
            }
        }
        return TextUtils.join(" ", parts);
    }
}
