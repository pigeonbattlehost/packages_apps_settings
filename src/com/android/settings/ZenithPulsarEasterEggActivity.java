package com.android.settings.deviceinfo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.settings.R;

public class ZenithPulsarEasterEggActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zenith_pulsar_egg);

        ImageView img = findViewById(R.id.pulsar);

        ObjectAnimator spin = ObjectAnimator.ofFloat(img, "rotation", 0f, 7200f);
        spin.setDuration(1200);
        spin.setInterpolator(new AccelerateInterpolator());

        spin.start();

        spin.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {

                img.animate()
                        .scaleX(3f)
                        .scaleY(3f)
                        .alpha(0f)
                        .setDuration(300)
                        .start();

                findViewById(R.id.cosmos_root).setAlpha(1f);
            }
        });
    }
}
