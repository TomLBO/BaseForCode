package com.bob.baseforcode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bob
 * on 2018/6/25.
 */
public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private MyFragmentAdapter mAdapter;
    private MyFragmentStateAdapter mStateAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_pager);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mFragments = Arrays.asList(
                ModuleFragment.getInstance("module 1"),
                ModuleFragment.getInstance("module 2"),
                ModuleFragment.getInstance("module 3"),
                ModuleFragment.getInstance("module 4"));


        mAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
//        mStateAdapter = new MyFragmentStateAdapter(getSupportFragmentManager());
//        mViewPager.setAdapter(mStateAdapter);
    }

    class MyFragmentAdapter extends FragmentPagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    class MyFragmentStateAdapter extends FragmentStatePagerAdapter{

        public MyFragmentStateAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
