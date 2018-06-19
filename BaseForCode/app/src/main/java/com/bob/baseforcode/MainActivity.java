package com.bob.baseforcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bob.core.ScreenInfo;
import com.bob.uilibrary.navigation.bottom.BottomNavigationView;
import com.bob.uilibrary.navigation.bottom.NavigationEntity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = findViewById(R.id.bottom_navigation);

        ScreenInfo.printScreenInfo(this);

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

        mNavigationView.setItemList(list);

        mNavigationView.setOnItemClickListener(new BottomNavigationView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onItemClick: " + position);
            }
        });

    }
}
