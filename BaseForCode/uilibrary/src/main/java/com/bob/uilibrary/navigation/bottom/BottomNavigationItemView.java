package com.bob.uilibrary.navigation.bottom;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.uilibrary.utils.DensityUtil;

/**
 * item view
 */
public class BottomNavigationItemView extends LinearLayout {

    private ImageView mIvIcon;
    private TextView mTvTitle;
    private NavigationEntity mNavigation;

    private int mItemPadding;
    private int mIconPadding;
    private int mTitlePadding;

    public BottomNavigationItemView(Context context) {
        this(context, null);
    }

    public BottomNavigationItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        mItemPadding = DensityUtil.dip2px(getContext(), 2);
        setPadding(mItemPadding, mItemPadding, mItemPadding, mItemPadding);

        mIconPadding = DensityUtil.dip2px(getContext(), 1);
        mTitlePadding = DensityUtil.dip2px(getContext(), 1);
    }

    private void addChild() {
        if (mNavigation == null) {
            return;
        }
        //icon
        mIvIcon = getIconView();
        //title
        mTvTitle = getTitleView();

        addView(mIvIcon);
        addView(mTvTitle);
    }

    private ImageView getIconView() {
        ImageView iconView = new ImageView(getContext());
        LayoutParams ivParams = new LayoutParams(LayoutParams.MATCH_PARENT, (int) mNavigation.getIconSize(), 1);
        iconView.setLayoutParams(ivParams);
        iconView.setPadding(mIconPadding, mIconPadding, mIconPadding, mIconPadding);
        iconView.setImageDrawable(getResources().getDrawable(mNavigation.getIconNormal()));

        return iconView;
    }

    private TextView getTitleView() {
        TextView titleView = new TextView(getContext());
        LayoutParams tvParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        titleView.setGravity(Gravity.CENTER);
        titleView.setLayoutParams(tvParams);
        titleView.setPadding(mTitlePadding, mTitlePadding, mTitlePadding, mTitlePadding);
        titleView.setTextColor(getResources().getColor(mNavigation.getTitleColorNormal()));
        titleView.setText(getResources().getText(mNavigation.getTitle()));
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mNavigation.getTitleSize());
        return titleView;
    }

    public void setData(NavigationEntity entity) {
        mNavigation = entity;


        addChild();
    }

    public NavigationEntity getData() {
        return mNavigation;
    }

    public void setPress(boolean press) {
        if (mNavigation == null) {
            return;
        }
        mIvIcon.setImageDrawable(getResources()
                .getDrawable(press ? mNavigation.getIconPress() : mNavigation.getIconNormal()));
        mTvTitle.setTextColor(getResources()
                .getColor(press ? mNavigation.getTitleColorPress() : mNavigation.getTitleColorNormal()));
    }
}
