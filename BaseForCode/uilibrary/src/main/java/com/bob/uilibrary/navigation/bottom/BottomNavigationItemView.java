package com.bob.uilibrary.navigation.bottom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.uilibrary.utils.DensityUtil;

/**
 * item view
 */
public class BottomNavigationItemView extends LinearLayout {

    private static final int DEFAULT_ICON_SIZE_DP = 20;
    private static final int DEFAULT_TITLE_SIZE_SP = 12;
    private static final int DEFAULT_ICON_TITLE_GAP_DP = 2;
    private static final int DEFAULT_PADDING_DP = 2;

    private ImageView mIvIcon;
    private TextView mTvTitle;
    private NavigationEntity mNavigation;

    private int mItemPadding;

    private int iconSize;
    private int titleSize;
    private int iconTitleGap;

    private boolean isChecked;
    private boolean mLastCheckStatus;


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
        mItemPadding = DensityUtil.dip2px(getContext(), DEFAULT_PADDING_DP);
        setPadding(mItemPadding, mItemPadding, mItemPadding, mItemPadding);

        iconSize = iconSize < 0 ? DensityUtil.dip2px(getContext(), DEFAULT_ICON_SIZE_DP) : iconSize;
        titleSize = titleSize < 0 ? DensityUtil.sp2px(getContext(), DEFAULT_TITLE_SIZE_SP) : titleSize;
        iconTitleGap = iconTitleGap < 0 ? DensityUtil.dip2px(getContext(), DEFAULT_ICON_TITLE_GAP_DP) : iconTitleGap;
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
        LayoutParams ivParams = new LayoutParams(iconSize, iconSize);
        iconView.setLayoutParams(ivParams);
        iconView.setImageDrawable(getResources().getDrawable(mNavigation.getIconNormal()));
        return iconView;
    }

    private TextView getTitleView() {
        TextView titleView = new TextView(getContext());
        LayoutParams tvParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        titleView.setGravity(Gravity.CENTER);
        tvParams.topMargin = iconTitleGap;
        titleView.setLayoutParams(tvParams);
        titleView.setTextColor(getResources().getColor(mNavigation.getTitleColorNormal()));
        titleView.setText(getResources().getText(mNavigation.getTitle()));
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
        return titleView;
    }

    public void setData(NavigationEntity entity) {
        mNavigation = entity;


        addChild();
    }

    public NavigationEntity getData() {
        return mNavigation;
    }

    public void setCheck(boolean check) {
        if (mNavigation == null) {
            return;
        }
        isChecked = check;
        mIvIcon.setImageDrawable(getResources()
                .getDrawable(check ? mNavigation.getIconPress() : mNavigation.getIconNormal()));
        mTvTitle.setTextColor(getResources()
                .getColor(check ? mNavigation.getTitleColorPress() : mNavigation.getTitleColorNormal()));
    }

    public int getIconSize() {
        return iconSize;
    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public int getIconTitleGap() {
        return iconTitleGap;
    }

    public void setIconTitleGap(int iconTitleGap) {
        this.iconTitleGap = iconTitleGap;
    }


    public boolean pointInView(float localX, float localY, float slop) {
        return localX >= -slop && localY >= -slop && localX < ((getRight() - getLeft()) + slop) &&
                localY < ((getBottom() - getTop()) + slop);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mLastCheckStatus = isChecked;
            setCheck(true);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

        } else if (event.getAction() == MotionEvent.ACTION_UP) {

            if (pointInView(x, y, 0)) {
                performClick();
            } else {
                if (!mLastCheckStatus) {
                    setCheck(false);
                }
            }
        }
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
