package com.bob.baseforcode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by bob
 * on 2018/6/28.
 */
public class BackStackActivity extends AppCompatActivity {

    static int step = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_stack);

        findViewById(R.id.btn_previous).setOnClickListener(this::removeFragment);
        findViewById(R.id.btn_next).setOnClickListener(this::addFragment);
        addFragment(null);
    }

    public void addFragment(View view) {
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment_container, ModuleFragment.getInstance("module " + step++), "");

        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void removeFragment(View view) {
        getSupportFragmentManager().popBackStack();
    }
}
