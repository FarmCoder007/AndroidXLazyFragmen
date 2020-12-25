package com.lazy.mylazyfragment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lazy.mylazyfragment.R;
import com.lazy.mylazyfragment.adapter.FragmentLazyPagerAdapter;
import com.lazy.mylazyfragment.fragment.AndroidXTestOneFragment;
import com.lazy.mylazyfragment.fragment.AndroidXTestThreeFragment;
import com.lazy.mylazyfragment.fragment.AndroidXTestTwoFragment;
import com.lazy.mylazyfragment.fragment.DFragment;
import com.lazy.mylazyfragment.fragment.FourFragment;

import android.os.Bundle;

import java.util.ArrayList;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 *  复杂嵌套   首页是 vp+adapter
 */
public class Activity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        ViewPager viewPager = findViewById(R.id.view_pager);
        // 重点 在于 使用了 此参数   BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        FragmentLazyPagerAdapter fragmentLazyPagerAdapter = new FragmentLazyPagerAdapter(getSupportFragmentManager(),
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new AndroidXTestOneFragment());
        arrayList.add(new FourFragment());
        arrayList.add(new DFragment());
        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("AndroidXTestOneFragment");
        titleList.add("FourFragment");
        titleList.add("DFragment");
        fragmentLazyPagerAdapter.setFragmentArrayList(arrayList);
        fragmentLazyPagerAdapter.setTitleList(titleList);
        viewPager.setAdapter(fragmentLazyPagerAdapter);

        ((TabLayout)findViewById(R.id.tab_layout)).setupWithViewPager(viewPager);
    }
}