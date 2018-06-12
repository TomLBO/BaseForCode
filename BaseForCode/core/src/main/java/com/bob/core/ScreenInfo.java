package com.bob.core;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

/**
 * Created by bob
 * on 2018/6/12.
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
        float widthPixels = activity.getResources().getDisplayMetrics().widthPixels;
        float heightPixels = activity.getResources().getDisplayMetrics().heightPixels;
        float scaledDensity = activity.getResources().getDisplayMetrics().scaledDensity;


        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(mDisplayMetrics);
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        float density = mDisplayMetrics.density;
        int densityDpi = mDisplayMetrics.densityDpi;



        String dpi = getDpi(densityDpi);
        StringBuilder builder = new StringBuilder();
        builder.append("屏幕信息").append("\n")
                .append("宽度:").append(widthPixels).append("\n")
                .append("长度:").append(heightPixels).append("\n")
                .append("densityDpi:").append(densityDpi).append("\n")
                .append("density:").append(density).append("\n")
                .append("scaledDensity:").append(scaledDensity).append("\n")
                .append("width:").append(width).append("\n")
                .append("height:").append(height).append("\n")
                .append("文件夹:").append(dpi).append("\n");
        Log.d(TAG, builder.toString());
    }

    private static String getDpi(float xdpi) {
        String dpi = "";
        if (xdpi >= 0 && xdpi < 120) {
            dpi = "ldpi";
        } else if (xdpi >= 120 && xdpi < 160) {
            dpi = "mdpi";
        } else if (xdpi >= 160 && xdpi < 240) {
            dpi = "hdpi";
        } else if (xdpi >= 240 && xdpi < 320) {
            dpi = "xhdpi";
        } else if (xdpi >= 320 && xdpi < 480) {
            dpi = "xxhdpi";
        } else if (xdpi >= 480 && xdpi < 640) {
            dpi = "xxxhdpi";
        }
        return dpi;
    }
}
