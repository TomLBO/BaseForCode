package com.bob.uilibrary.navigation.bottom;

/**
 * Created by bob
 * on 2018/6/19.
 */
public class NavigationEntity {

    private int title;

    private int titleColorNormal;

    private int titleColorPress;

    private int iconNormal;

    private int iconPress;

    public static NavigationEntity getInstance(int title, int titleColorNormal,
                                               int titleColorPress, int iconNormal, int iconPress) {
        return new NavigationEntity(title, titleColorNormal, titleColorPress, iconNormal, iconPress);
    }

    public NavigationEntity(int title, int iconNormal, int iconPress) {
        this.title = title;
        this.iconNormal = iconNormal;
        this.iconPress = iconPress;
    }

    public NavigationEntity(int title, int titleColorNormal, int titleColorPress, int iconNormal, int iconPress) {
        this.title = title;
        this.titleColorNormal = titleColorNormal;
        this.titleColorPress = titleColorPress;
        this.iconNormal = iconNormal;
        this.iconPress = iconPress;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIconNormal() {
        return iconNormal;
    }

    public void setIconNormal(int iconNormal) {
        this.iconNormal = iconNormal;
    }

    public int getIconPress() {
        return iconPress;
    }

    public void setIconPress(int iconPress) {
        this.iconPress = iconPress;
    }

    public int getTitleColorNormal() {
        return titleColorNormal;
    }

    public void setTitleColorNormal(int titleColorNormal) {
        this.titleColorNormal = titleColorNormal;
    }

    public int getTitleColorPress() {
        return titleColorPress;
    }

    public void setTitleColorPress(int titleColorPress) {
        this.titleColorPress = titleColorPress;
    }

}
