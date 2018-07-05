package com.bob.baseforcode.multiType;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bob.baseforcode.R;

import java.util.List;

/**
 * Created by bob
 * on 2018/7/4.
 */
public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    private List<AppInfo> mAppList;

    public void setData(List<AppInfo> appList) {
        mAppList = appList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo info = mAppList.get(position);
        holder.mTvName.setText(info.getName());
    }

    @Override
    public int getItemCount() {
        return mAppList == null ? 0 : mAppList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvName;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
