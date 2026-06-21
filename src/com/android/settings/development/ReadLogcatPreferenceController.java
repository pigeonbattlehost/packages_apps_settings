package com.android.settings.development;

import android.content.Context;

import androidx.preference.Preference;

import com.android.settings.core.BasePreferenceController;

public class ReadLogcatPreferenceController extends BasePreferenceController {

    public ReadLogcatPreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (getPreferenceKey().equals(preference.getKey())) {
            readLogcat();
            return true;
        }
        return false;
    }

    private void readLogcat() {
        try {
            Runtime.getRuntime().exec("logcat -d -f /sdcard/logcat.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
