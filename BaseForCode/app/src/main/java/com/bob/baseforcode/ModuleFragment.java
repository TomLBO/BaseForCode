package com.bob.baseforcode;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bob
 * on 2018/6/21.
 */
public class ModuleFragment extends Fragment {

    public static final String MODULE_NAME = "MODULE_NAME";

    private Toolbar mToolbar;
    private TextView mTvContent;
    private ProgressBar mProgressBar;
    private ExecutorService mThreadPool;

    public static ModuleFragment getInstance(String moduleName) {
        ModuleFragment fragment = new ModuleFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MODULE_NAME, moduleName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment, container, false);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mTvContent = (TextView) view.findViewById(R.id.tv_content);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            String title = arguments.getString(MODULE_NAME);
            mToolbar.setTitle(title);
            mTvContent.setText(title);
        }

        mThreadPool = Executors.newCachedThreadPool();
        mThreadPool.submit(this::showContent);

    }

    private void showContent() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runOnMainThread(() -> mTvContent.setVisibility(View.VISIBLE));
        mProgressBar.post(() -> mProgressBar.setVisibility(View.GONE));

        mTvContent.setOnClickListener(v -> mTvContent.setText(mTvContent.getText() + "2"));
    }

    private void runOnMainThread(Runnable r) {
        new Handler(Looper.getMainLooper()).post(r);
    }
}
