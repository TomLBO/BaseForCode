package com.bob.baseforcode;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bob.core.ScreenAdaptation;
import com.bob.core.ScreenInfo;
import com.bob.uilibrary.navigation.bottom.BottomNavigationBar;
import com.bob.uilibrary.navigation.bottom.NavigationEntity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    BottomNavigationBar mNavigationView;

    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenAdaptation.setCustomDensity(this, getApplication());
        ScreenInfo.printScreenInfo(this);

        setContentView(R.layout.activity_main);
        mNavigationView = (BottomNavigationBar) findViewById(R.id.bottom_navigation);

        List<NavigationEntity> list = Arrays.asList(
                NavigationEntity.getInstance(R.string.navigation_home,
                        R.color.navigation_title_normal, R.color.navigation_title_pressed,
                        R.drawable.home_normal, R.drawable.home_selected),
                NavigationEntity.getInstance(R.string.navigation_news,
                        R.color.navigation_title_normal, R.color.navigation_title_pressed,
                        R.drawable.communications_normal, R.drawable.communications_selected),
                NavigationEntity.getInstance(R.string.navigation_app,
                        R.color.navigation_title_normal, R.color.navigation_title_pressed,
                        R.drawable.apps_normal, R.drawable.apps_selected),
                NavigationEntity.getInstance(R.string.navigation_settings,
                        R.color.navigation_title_normal, R.color.navigation_title_pressed,
                        R.drawable.settings_normal, R.drawable.settings_selected));


        if (savedInstanceState == null) {
            mFragments = Arrays.asList(
                    ModuleFragment.getInstance("module 1"),
                    ModuleFragment.getInstance("module 2"),
                    ModuleFragment.getInstance("module 3"),
                    ModuleFragment.getInstance("module 4")
            );
            Log.d(TAG, "first: " + mFragments);
            initFragment();
        } else {
            mFragments = getSupportFragmentManager().getFragments();
            Log.d(TAG, "second: " + mFragments);
        }

        mNavigationView.setItemList(list);

        mNavigationView.setOnItemClickListener(this::showFragment);

        mNavigationView.setChecked(0);
    }

    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (Fragment mFragment : mFragments) {
            transaction.add(R.id.fragment_container, mFragment, mFragment.toString());
            transaction.hide(mFragment);
        }
        transaction.commit();
    }

    private void showFragment(int position) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

//        manager.back
//        transaction.addToBackStack()

//        Log.d(TAG, "fragment size: " + manager.getFragments().size());
        int size = mFragments.size();
        for (int i = 0; i < size; i++) {
            Fragment fragment = mFragments.get(i);
            if (i == position) {
                transaction.show(fragment);
            } else {
                transaction.hide(fragment);
            }
//            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

}
