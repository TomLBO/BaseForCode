package com.bob.baseforcode.multiType;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bob.baseforcode.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by bob
 * on 2018/7/4.
 */
public class CategoryViewBinder extends ItemViewBinder<Category, CategoryViewBinder.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Category item) {
        holder.mTvName.setText(item.getName());
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvName;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
