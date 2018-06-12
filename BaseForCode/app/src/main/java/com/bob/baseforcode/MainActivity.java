package com.bob.baseforcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bob.core.ScreenInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScreenInfo.printScreenInfo(this);
    }
}
