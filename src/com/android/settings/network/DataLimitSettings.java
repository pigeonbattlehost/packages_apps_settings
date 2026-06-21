package com.android.settings.network;

import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.preference.PreferenceFragmentCompat;

import com.android.settings.R;

public class DataLimitSettings extends PreferenceFragmentCompat {

    private EditText input;
    private RadioGroup unitGroup;
    private Button saveButton;

    private static final long MIN_LIMIT = 20L * 1024 * 1024; // 20MB

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.data_limit_settings, container, false);

        input = view.findViewById(R.id.limit_value);
        unitGroup = view.findViewById(R.id.unit_group);
        saveButton = view.findViewById(R.id.save_button);

        input.setInputType(InputType.TYPE_CLASS_NUMBER);

        saveButton.setOnClickListener(v -> saveLimit());

        return view;
    }

    private void saveLimit() {

        if (getContext() == null) return;

        String raw = input.getText().toString().trim();

        if (raw.isEmpty()) {
            Toast.makeText(getContext(),
                    getString(R.string.enter_limit_ofdata),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        long value;

        try {
            value = Long.parseLong(raw);
        } catch (Exception e) {
            Toast.makeText(getContext(),
                    getString(R.string.enter_limit_ofdata),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int selected = unitGroup.getCheckedRadioButtonId();
        boolean isGb = selected == R.id.unit_gb;

        long bytes = isGb
                ? value * 1024L * 1024L * 1024L
                : value * 1024L * 1024L;

        // 
        if (bytes < MIN_LIMIT) {
            Toast.makeText(getContext(),
                    "Minimal data limit is 20MB!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Settings.Global.putLong(
                getContext().getContentResolver(),
                "mobile_data_limit_bytes",
                bytes
        );

        Toast.makeText(getContext(),
                getString(R.string.wifi_add_app_single_network_saved_summary),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // empty because custom layout
    }
}