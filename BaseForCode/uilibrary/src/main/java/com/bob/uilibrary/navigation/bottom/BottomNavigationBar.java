package com.bob.uilibrary.navigation.bottom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.bob.uilibrary.R;

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

    private int titleSize;

    private int iconTitleGap;

    private int mCurrentPos;


    public BottomNavigationBar(Context context) {
        this(context, null);
    }

    public BottomNavigationBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.BottomNavigationBar, defStyleAttr, 0);
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
                        mCurrentPos = pos;
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
            mItemViews.get(i).setCheck(i == pos);
        }
    }

    public void setItemList(List<NavigationEntity> list) {
        mCurrentPos = -1;
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

    public void setChecked(int pos) {
        if (pos > mItems.size()) {
            throw new RuntimeException("");
        }
        if (mItemClickListener != null) {
            setItemPress(pos);
            mItemClickListener.onItemClick(pos);
        }
    }

}
