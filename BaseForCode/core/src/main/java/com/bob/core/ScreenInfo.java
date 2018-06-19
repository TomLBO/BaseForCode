package com.bob.core;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * 屏幕信息工具类
 * <p>
 * px = dp * (dpi / 160)
 * <p>
 * dp = px / dpi * 160
 */
public class ScreenInfo {
    private static final String TAG = "ScreenInfo";
    private static ScreenInfo INSTANCE;

    private ScreenInfo() {
    }

    public static ScreenInfo getIntance() {
        if (INSTANCE == null) {
            INSTANCE = new ScreenInfo();
        }
        return INSTANCE;
    }

    public static void printScreenInfo(Activity activity) {
//        float densityDpi = activity.getResources().getDisplayMetrics().densityDpi;
//        float widthPixels = activity.getResources().getDisplayMetrics().widthPixels;
//        float heightPixels = activity.getResources().getDisplayMetrics().heightPixels;
//        float scaledDensity = activity.getResources().getDisplayMetrics().scaledDensity;

        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(mDisplayMetrics);
        float xdpi = mDisplayMetrics.xdpi;
        float ydpi = mDisplayMetrics.ydpi;

        int widthPixels = mDisplayMetrics.widthPixels;
        int heightPixels = mDisplayMetrics.heightPixels;
//        mDisplayMetrics.
        float density = mDisplayMetrics.density;
        int densityDpi = mDisplayMetrics.densityDpi;

        //dp = px / dpi * 160
        float widthDp = widthPixels * 160 / densityDpi;
        float heightDp = heightPixels * 160 / densityDpi;

        StringBuilder builder = new StringBuilder();
        builder.append("\n屏幕信息").append("\n")
                .append("屏幕像素:\t").append(widthPixels).append(" * ").append(heightPixels).append("\n")
                .append("屏幕像素密度dpi:\t").append(densityDpi).append("\n")
                .append("屏幕dpi:\t").append(xdpi).append(" * ").append(ydpi).append("\n")
                .append("屏幕dp: \t").append(widthDp).append(" * ").append(heightDp).append("\n")
                .append("density:\t").append(density).append("\n")
                .append("文件夹: \t").append(getDpi(densityDpi)).append("\n");
        Log.d(TAG, builder.toString());

        Log.d(TAG, "value: " + activity.getResources().getString(R.string.dpi));
    }

    private static String getDpi(float xdpi) {
        String dpi = "no";
        if (xdpi <= 120) {
            dpi = "ldpi";
        } else if (xdpi <= 160) {
            dpi = "mdpi";
        } else if (xdpi <= 240) {
            dpi = "hdpi";
        } else if (xdpi <= 320) {
            dpi = "xhdpi";
        } else if (xdpi <= 480) {
            dpi = "xxhdpi";
        } else {
            //xdpi >= 480 && xdpi < 640
            dpi = "xxxhdpi";
        }
        return dpi;
    }
}
