package com.bob.uilibrary.navigation.bottom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.bob.uilibrary.R;
import com.bob.uilibrary.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 底部导航栏
 */
public class BottomNavigationBar extends LinearLayout {
    private static final String TAG = "BottomNavigationView";
    private List<NavigationEntity> mItems;
    private List<BottomNavigationItemView> mItemViews;
    private OnItemClickListener mItemClickListener;

    private int iconSize;
    private int iconSizeDefault;

    private int titleSize;
    private int titleSizeDefault;

    private int iconTitleGap;


    public BottomNavigationBar(Context context) {
        this(context, null);
    }

    public BottomNavigationBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray ta = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.BottomNavigationBar, defStyleAttr, defStyleRes);
        int length = ta.length();
        for (int i = 0; i < length; i++) {
            int attr = ta.getIndex(i);
            if (attr == R.styleable.BottomNavigationBar_navigation_title_size) {
                titleSize = ta.getDimensionPixelSize(i, -1);
            } else if (attr == R.styleable.BottomNavigationBar_navigation_icon_size) {
                iconSize = ta.getDimensionPixelSize(i, -1);
            } else if (attr == R.styleable.BottomNavigationBar_navigation_icon_title_gap) {
                iconTitleGap = ta.getDimensionPixelSize(i, 10);
            }
        }

        init();
    }

    private void init() {
        iconSizeDefault = DensityUtil.dip2px(getContext(), 20);
        iconSize = iconSize < 0 ? iconSizeDefault : iconSize;

        titleSizeDefault = DensityUtil.dip2px(getContext(), 10);
        titleSize = titleSize < 0 ? titleSizeDefault : titleSize;

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        addChild();
    }

    private void addChild() {
        if (mItemViews == null || mItemViews.size() <= 0) {
            return;
        }

        if (getChildCount() > 0) {
            removeAllViews();
        }

        int len = mItemViews.size();
        for (int i = 0; i < len; i++) {
            final BottomNavigationItemView itemView = mItemViews.get(i);
            final int pos = i;
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        setItemPress(pos);
                        mItemClickListener.onItemClick(pos);
                    }
                }
            });
            addView(itemView);
        }
    }

    private void setItemPress(int pos) {
        if (mItemViews == null || mItemViews.size() <= 0) {
            return;
        }
        int len = mItemViews.size();
        for (int i = 0; i < len; i++) {
            mItemViews.get(i).setPress(i == pos);
        }
    }

    public void setItemList(List<NavigationEntity> list) {
        mItems = list;
        if (mItems == null || mItems.size() <= 0) {
            return;
        }
        if (mItemViews == null) {
            mItemViews = new ArrayList<>();
        } else {
            mItemViews.clear();
        }
        for (NavigationEntity item : mItems) {
            final BottomNavigationItemView view = new BottomNavigationItemView(getContext());
            LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
            view.setLayoutParams(params);
            view.setIconSize(iconSize);
            view.setTitleSize(titleSize);
            view.setIconTitleGap(iconTitleGap);
            view.setData(item);
            mItemViews.add(view);
        }
        addChild();
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
