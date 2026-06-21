package com.android.settings.deviceinfo;

import android.content.Context;

import com.android.settings.core.BasePreferenceController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.RandomAccessFile;

public class CpuInfoPreferenceController extends BasePreferenceController {

    private static final String KEY = "cpu_info";

    public CpuInfoPreferenceController(Context context) {
        super(context, KEY);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        String cpu = getCpuModel();
        String arch = System.getProperty("os.arch");
        String freq = getCpuFreq();

        return cpu + "\n" + arch + " • " + freq;
    }

    private String getCpuModel() {
        try (BufferedReader br = new BufferedReader(new FileReader("/proc/cpuinfo"))) {
            String line;

            while ((line = br.readLine()) != null) {

                line = line.toLowerCase();

                if (line.contains("model name") || line.contains("hardware")) {
                    String[] parts = line.split(":");
                    if (parts.length > 1) {
                        return parts[1].trim();
                    }
                }
            }
        } catch (Exception ignored) {}

        return "Unknown CPU";
    }

    private String getCpuFreq() {
        try {
            String path = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq";

            RandomAccessFile raf = new RandomAccessFile(path, "r");
            String val = raf.readLine();
            raf.close();

            if (val == null) return "Unknown";

            long khz = Long.parseLong(val.trim());
            long mhz = khz / 1000;
            long ghz = mhz / 1000;

            if (ghz > 0) {
                return ghz + "." + (mhz % 1000) + " GHz";
            }

            return mhz + " MHz";

        } catch (Exception e) {
            return "Unknown";
        }
    }
}