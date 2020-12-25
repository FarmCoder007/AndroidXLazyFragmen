package com.lazy.mylazyfragment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import com.lazy.mylazyfragment.R;
import com.lazy.mylazyfragment.fragment.FourFragment;

import android.os.Bundle;

/**
 * add hide  show  方式 嵌套 fragment 懒加载  Androidx  下的实现方式 和 不嵌套的一样
 */
public class Activity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        FourFragment fourFragment = new FourFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_container, fourFragment)
                .setMaxLifecycle(fourFragment, Lifecycle.State.RESUMED)
                .commit();
    }
}