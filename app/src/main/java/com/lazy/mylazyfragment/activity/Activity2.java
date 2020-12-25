package com.lazy.mylazyfragment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lazy.mylazyfragment.R;
import com.lazy.mylazyfragment.adapter.FragmentLazyStatePageAdapter;
import com.lazy.mylazyfragment.fragment.AndroidXTestOneFragment;
import com.lazy.mylazyfragment.fragment.AndroidXTestThreeFragment;
import com.lazy.mylazyfragment.fragment.AndroidXTestTwoFragment;

import android.os.Bundle;

import java.util.ArrayList;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 * AndroidX 下  FragmentStatePagerAdapter  Androidx懒加载  和  Activity1一样 集成带BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT 的
 *                                         旧模式的懒加载  也和 FragmentPageAdapter一样
 */
public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        ViewPager viewPager = findViewById(R.id.view_pager);
        // 重点 在于 使用了 此参数   BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        FragmentLazyStatePageAdapter fragmentLazyStatePageAdapter = new FragmentLazyStatePageAdapter(getSupportFragmentManager(),
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

//        FragmentLazyStatePageAdapter fragmentLazyStatePageAdapter = new FragmentLazyStatePageAdapter(getSupportFragmentManager());

        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new AndroidXTestOneFragment());
        arrayList.add(new AndroidXTestTwoFragment());
        arrayList.add(new AndroidXTestThreeFragment());
        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("OneFragment");
        titleList.add("TwoFragment");
        titleList.add("ThreeFragment");
        fragmentLazyStatePageAdapter.setFragmentArrayList(arrayList);
        fragmentLazyStatePageAdapter.setTitleList(titleList);
        viewPager.setAdapter(fragmentLazyStatePageAdapter);

        ((TabLayout) findViewById(R.id.tab_layout)).setupWithViewPager(viewPager);

    }
}