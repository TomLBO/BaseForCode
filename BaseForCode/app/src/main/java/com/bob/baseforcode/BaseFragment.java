package com.bob.baseforcode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

/**
 * Created by bob
 * on 2018/6/21.
 */
public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    /**
     * https://www.jianshu.com/p/c12a98a36b2b
     */
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            Log.d(TAG, this + "  isSupportHidden: " + isSupportHidden);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: " + this + " isHidden: " + isHidden());
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

}
