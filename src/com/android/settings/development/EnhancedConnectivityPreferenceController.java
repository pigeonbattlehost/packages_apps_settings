/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.development;

import android.content.Context;

import androidx.preference.Preference;
import androidx.preference.SwitchPreference;

import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;

/**
 * Preference controller for Enhanced Connectivity feature, stubbed out
 */
public class EnhancedConnectivityPreferenceController extends
        DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener,
        PreferenceControllerMixin {

    private static final String ENHANCED_CONNECTIVITY_KEY = "enhanced_connectivity";

    @VisibleForTesting
    static final int ENHANCED_CONNECTIVITY_ON = 1;
    @VisibleForTesting
    static final int ENHANCED_CONNECTIVITY_OFF = 0;

    public EnhancedConnectivityPreferenceController(Context context) {
        super(context);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        // Stub!
        if (mPreference instanceof SwitchPreference) {
            ((SwitchPreference) mPreference).setChecked((Boolean) o);
        }
        return true;
    }

    @Override
    public String getPreferenceKey() {
        return ENHANCED_CONNECTIVITY_KEY;
    }

    @Override
    public void updateState(Preference preference) {

        if (mPreference instanceof SwitchPreference) {
            ((SwitchPreference) mPreference).setChecked(false);
        }
    }

    @Override
    public boolean isAvailable() {
        // Stub!
        return false;
    }

    @Override
    protected void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        if (mPreference instanceof SwitchPreference) {
            ((SwitchPreference) mPreference).setChecked(false);
        }
    }
}