package com.lazy.mylazyfragment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.lazy.mylazyfragment.R;
import com.lazy.mylazyfragment.adapter.FragmentLazyPagerAdapter;
import com.lazy.mylazyfragment.fragment.AndroidXTestOneFragment;
import com.lazy.mylazyfragment.fragment.AndroidXTestThreeFragment;
import com.lazy.mylazyfragment.fragment.AndroidXTestTwoFragment;
import java.util.ArrayList;
import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 *  验证  viewpage  + FragmentPagerAdapter  Androidx 下的懒加载方案
 *  AndroidX  下 新的懒加载方案  会控制 未显示的fragment  最大的生命周期
 *  2020-12-25 15:00:36.828 31276-31276/com.lazy.mylazyfragment D/LogFragment: onAttach:OneFragment
 * 2020-12-25 15:00:36.828 31276-31276/com.lazy.mylazyfragment D/LogFragment: onCreate:OneFragment
 * 2020-12-25 15:00:36.828 31276-31276/com.lazy.mylazyfragment D/LogFragment: onAttach:TwoFragment
 * 2020-12-25 15:00:36.828 31276-31276/com.lazy.mylazyfragment D/LogFragment: onCreate:TwoFragment
 * 2020-12-25 15:00:36.828 31276-31276/com.lazy.mylazyfragment D/LogFragment: onCreateView:OneFragment
 * 2020-12-25 15:00:36.830 31276-31276/com.lazy.mylazyfragment D/LogFragment: onActivityCreated:OneFragment
 * 2020-12-25 15:00:36.831 31276-31276/com.lazy.mylazyfragment D/LogFragment: onStart:OneFragment
 * 2020-12-25 15:00:36.831 31276-31276/com.lazy.mylazyfragment D/LogFragment: onCreateView:TwoFragment
 * 2020-12-25 15:00:36.832 31276-31276/com.lazy.mylazyfragment D/LogFragment: onActivityCreated:TwoFragment
 * 2020-12-25 15:00:36.832 31276-31276/com.lazy.mylazyfragment D/LogFragment: onStart:TwoFragment
 * 2020-12-25 15:00:36.832 31276-31276/com.lazy.mylazyfragment D/LogFragment: onResume:OneFragment
 * 2020-12-25 15:00:36.832 31276-31276/com.lazy.mylazyfragment D/LogFragment: lazyInit:!!!!!!!OneFragment
 */
public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        ViewPager viewPager = findViewById(R.id.view_pager);
        // 重点 在于 使用了 此参数   BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        FragmentLazyPagerAdapter fragmentLazyPagerAdapter = new FragmentLazyPagerAdapter(getSupportFragmentManager(),
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new AndroidXTestOneFragment());
        arrayList.add(new AndroidXTestTwoFragment());
        arrayList.add(new AndroidXTestThreeFragment());
        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("OneFragment");
        titleList.add("TwoFragment");
        titleList.add("ThreeFragment");
        fragmentLazyPagerAdapter.setFragmentArrayList(arrayList);
        fragmentLazyPagerAdapter.setTitleList(titleList);
        viewPager.setAdapter(fragmentLazyPagerAdapter);

        ((TabLayout)findViewById(R.id.tab_layout)).setupWithViewPager(viewPager);
    }
}