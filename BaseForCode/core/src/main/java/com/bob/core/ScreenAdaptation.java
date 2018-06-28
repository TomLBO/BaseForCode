package com.bob.core;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * Created by bob
 * on 2018/6/22.
 */
public class ScreenAdaptation {

    private static float sNonCompatDensity;
    private static float sNonCompatScaledDensity;

    /**
     * px = density * dp;
     * density = dpi / 160;
     * px = dp * (dpi / 160);
     * dp = px / (dpi / 160)
     */
    public static void setCustomDensity(Activity activity, final Application application) {
        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if (sNonCompatDensity == 0) {
            sNonCompatDensity = appDisplayMetrics.density;
            sNonCompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration configuration) {
                    if (configuration != null && configuration.fontScale > 0) {
                        sNonCompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        float targetDensity = appDisplayMetrics.widthPixels / 360;
        float targetScaledDensity = targetDensity * (sNonCompatScaledDensity / sNonCompatDensity);
        int targetDensityDpi = (int) (160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;

    }
}
