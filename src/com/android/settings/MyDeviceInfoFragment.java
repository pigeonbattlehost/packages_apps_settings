package com.android.settings.deviceinfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.android.settings.R;

public class MyDeviceInfoFragment extends PreferenceFragmentCompat {

    private static final String KEY_ZENITH_OS_VERSION = "zenith_os_version";

    private int mTapCount = 0;
    private long mLastTapTime = 0;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.my_device_info, rootKey);

        Preference versionPref = findPreference(KEY_ZENITH_OS_VERSION);

        if (versionPref != null) {
            versionPref.setOnPreferenceClickListener(pref -> {

                long now = SystemClock.elapsedRealtime();

                // reset if tapping is too slow
                if (now - mLastTapTime > 1500) {
                    mTapCount = 0;
                }

                mLastTapTime = now;
                mTapCount++;

                if (mTapCount == 5) {
                    mTapCount = 0;
                    launchZenithEasterEgg();
                }

                return true;
            });
        }
    }

    private void launchZenithEasterEgg() {
        Intent intent = new Intent(getContext(), ZenithPulsarEasterEggActivity.class);
        startActivity(intent);
    }
}
