package com.lazy.mylazyfragment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import android.os.Bundle;
import android.view.View;

import com.lazy.mylazyfragment.R;
import com.lazy.mylazyfragment.fragment.AndroidXTestOneFragment;
import com.lazy.mylazyfragment.fragment.AndroidXTestThreeFragment;
import com.lazy.mylazyfragment.fragment.AndroidXTestTwoFragment;
import com.lazy.mylazyfragment.fragment.TraditionTestAFragment;
import com.lazy.mylazyfragment.fragment.TraditionTestBFragment;
import com.lazy.mylazyfragment.fragment.TraditionTestCFragment;

import java.util.ArrayList;

/**
 * Add + hide + show   Android x 懒加载
 * 1 在 add / show  后设置  fragmentTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
 * 2 在 hide        后设置  fragmentTransaction.setMaxLifecycle(fragment, Lifecycle.State.STARTED);
 */
public class Activity3Lazy extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);

        fragments = new ArrayList<>();
        fragments.add(new AndroidXTestOneFragment());
        fragments.add(new AndroidXTestTwoFragment());
        fragments.add(new AndroidXTestThreeFragment());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            fragmentTransaction.add(R.id.fl_container, fragment, fragment.getClass().getSimpleName());
            if (i == 0) {
                fragmentTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
            } else {
                fragmentTransaction.hide(fragment);
                fragmentTransaction.setMaxLifecycle(fragment, Lifecycle.State.STARTED);
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                showFragmenByIndex(0);
                break;
            case R.id.btn_2:
                showFragmenByIndex(1);
                break;
            case R.id.btn_3:
                showFragmenByIndex(2);
                break;
            default:
                break;
        }
    }

    public void showFragmenByIndex(int index) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if (i == index) {
                fragmentTransaction.show(fragment);
                fragmentTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
            } else {
                fragmentTransaction.hide(fragment);
                fragmentTransaction.setMaxLifecycle(fragment, Lifecycle.State.STARTED);
            }
        }
        fragmentTransaction.commit();
    }
}