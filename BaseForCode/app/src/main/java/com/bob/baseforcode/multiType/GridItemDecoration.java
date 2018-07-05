package com.bob.baseforcode.multiType;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by bob
 * on 2018/7/5.
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private final Drawable mDivider;
    private int color = Color.parseColor("#DDDDDD");
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public GridItemDecoration(Context context) {
        TypedArray array = context.obtainStyledAttributes(ATTRS);
        mDivider = array.getDrawable(0);
        array.recycle();

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {

            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int rLeft = child.getRight() + params.rightMargin;
            int rRight = rLeft + mDivider.getIntrinsicWidth();

            int top = child.getTop() - params.topMargin - mDivider.getIntrinsicHeight();
            int bottom = child.getBottom() + params.bottomMargin + mDivider.getIntrinsicHeight();

            drawDecoration(c, rLeft, rRight, top, bottom);

            if ((i % getSpanCount(parent)) == 0) {
                //第一列
                int lRight = child.getLeft() - params.leftMargin;
                int lLeft = lRight - mDivider.getIntrinsicWidth();
                drawDecoration(c, lLeft, lRight, top, bottom);
            }
        }
    }

    private void drawDecoration(Canvas c, int rLeft, int rRight, int top, int bottom) {
        mDivider.setBounds(rLeft, top, rRight, bottom);
        mDivider.draw(c);
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {

            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int left = child.getLeft() - params.leftMargin - mDivider.getIntrinsicWidth();
            int right = child.getRight() + params.rightMargin /*+ mDivider.getIntrinsicWidth()*/;
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            drawDecoration(c, left, right, top, bottom);

            if (i < getSpanCount(parent)) {
                //绘制顶部
                int b = child.getTop() - params.topMargin;
                int t = b - mDivider.getIntrinsicHeight();
                drawDecoration(c, left, right, t, b);
            }
        }
    }

    private int getSpanCount(RecyclerView parent) {
        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();
        return manager.getSpanCount();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int width = mDivider.getIntrinsicWidth();
        int height = mDivider.getIntrinsicHeight();
        outRect.set(width, height, width, height);
    }
}
