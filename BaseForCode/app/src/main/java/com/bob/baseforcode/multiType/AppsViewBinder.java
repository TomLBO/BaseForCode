package com.bob.baseforcode.multiType;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bob.baseforcode.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by bob
 * on 2018/7/4.
 */
public class AppsViewBinder extends ItemViewBinder<AppList, AppsViewBinder.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_apps, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AppList item) {
        holder.setData(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final AppAdapter mAdapter;
        RecyclerView mRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.rv_apps);
            mRecyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), 4));
            mRecyclerView.addItemDecoration(new GridItemDecoration(itemView.getContext()));
            mAdapter = new AppAdapter();
            mRecyclerView.setAdapter(mAdapter);
        }

        public void setData(AppList appList) {
            mAdapter.setData(appList.getApps());
        }
    }
}
