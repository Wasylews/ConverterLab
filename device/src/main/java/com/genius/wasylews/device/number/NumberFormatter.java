package com.genius.wasylews.device.number;

import android.text.TextUtils;

/**
 * Simple util class for phone numbers formatting
 * Format string can be like:
 * xxx xx xxx xx
 * (xxx) xx-xx-xx
 * (xxx) xxx-xx
 * or any other, just specify x where goes digit and other chars will be appended to result string
 */
public class NumberFormatter {

    private static final char DIGIT_CHAR = 'x';

    public static String format(String format, String number) throws IllegalArgumentException {
        if (TextUtils.isEmpty(format)) {
            throw new IllegalArgumentException("Specify number format string");
        }

        if (TextUtils.isEmpty(number)) {
            throw new IllegalArgumentException("Invalid number string");
        }

        StringBuilder builder = new StringBuilder();

        int numberDigitIndex = 0;
        for (char ch: format.toCharArray()) {
            if (ch == DIGIT_CHAR) {
                // get digit
                try {
                    char digit = number.charAt(numberDigitIndex);
                    numberDigitIndex++;
                    builder.append(digit);
                } catch (IndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Number string is too short", e);
                }
            } else {
                // just append to result
                builder.append(ch);
            }
        }

        return builder.toString();
    }
}
