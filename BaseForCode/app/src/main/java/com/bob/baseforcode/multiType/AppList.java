package com.bob.baseforcode.multiType;

import java.util.List;

/**
 * Created by bob
 * on 2018/7/4.
 */
public class AppList {
    private List<AppInfo> apps;

    public AppList(List<AppInfo> apps) {
        this.apps = apps;
    }

    public List<AppInfo> getApps() {
        return apps;
    }

    public void setApps(List<AppInfo> apps) {
        this.apps = apps;
    }
}
