package com.lazy.mylazyfragment.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lazy.mylazyfragment.R;
import com.lazy.mylazyfragment.adapter.FragmentLazyPagerAdapter;
import com.lazy.mylazyfragment.adapter.FragmentLazyStateAdapter;
import com.lazy.mylazyfragment.fragment.AndroidXTestOneFragment;
import com.lazy.mylazyfragment.fragment.AndroidXTestThreeFragment;
import com.lazy.mylazyfragment.fragment.AndroidXTestTwoFragment;

import android.os.Bundle;

import java.util.ArrayList;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class Activity7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);

        ViewPager2 viewPager = findViewById(R.id.view_pager2);
        FragmentLazyStateAdapter fragmentLazyPagerAdapter = new FragmentLazyStateAdapter(this);
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new AndroidXTestOneFragment());
        arrayList.add(new AndroidXTestTwoFragment());
        arrayList.add(new AndroidXTestThreeFragment());
        fragmentLazyPagerAdapter.setFragmentArrayList(arrayList);
        viewPager.setAdapter(fragmentLazyPagerAdapter);
        TabLayout tabLayout = ((TabLayout) findViewById(R.id.tab_layout));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("Tab" + position);
            }
        });
        tabLayoutMediator.attach();
    }
}