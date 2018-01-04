package com.genius.wasylews.device.preferences;


import android.content.SharedPreferences;

import java.util.Date;

import javax.inject.Inject;

public class PreferencesManager {

    private SharedPreferences mPreferences;

    @Inject
    public PreferencesManager(SharedPreferences preferences) {
        mPreferences = preferences;
    }

    public void putDate(String key, Date date) {
        mPreferences.edit()
                .putLong(key, date.getTime())
                .apply();
    }

    public Date getDate(String key) {
        long timestamp = mPreferences.getLong(key, 0);
        return new Date(timestamp);
    }
}
