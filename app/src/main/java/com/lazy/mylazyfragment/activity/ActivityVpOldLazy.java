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
import com.lazy.mylazyfragment.fragment.TraditionTestAFragment;
import com.lazy.mylazyfragment.fragment.TraditionTestBFragment;
import com.lazy.mylazyfragment.fragment.TraditionTestCFragment;

import java.util.ArrayList;

/**
 * viewPage + fragmentPageAdapter  时的 旧懒加载方案
 * 【如果没有调用 setOffscreenPageLimit 方法去设置 ViewPager 预缓存的 Fragment 个数。默认情况下 ViewPager 预缓存 Fragment 的个数为 1】
 * 首先  viewpager 预缓存前2个 fragment   这是首次加载log  前2个fragment  即使未显示也会走到onresume
 * 2020-12-25 14:46:44.173 30263-30263/com.lazy.mylazyfragment D/LogFragment: setUserVisibleHint:OneFragment--:false
 * 2020-12-25 14:46:44.173 30263-30263/com.lazy.mylazyfragment D/LogFragment: setUserVisibleHint:TwoFragment--:false
 * 2020-12-25 14:46:44.173 30263-30263/com.lazy.mylazyfragment D/LogFragment: setUserVisibleHint:OneFragment--:true
 * 2020-12-25 14:46:44.175 30263-30263/com.lazy.mylazyfragment D/LogFragment: onAttach:OneFragment
 * 2020-12-25 14:46:44.175 30263-30263/com.lazy.mylazyfragment D/LogFragment: onCreate:OneFragment
 * 2020-12-25 14:46:44.175 30263-30263/com.lazy.mylazyfragment D/LogFragment: onAttach:TwoFragment
 * 2020-12-25 14:46:44.176 30263-30263/com.lazy.mylazyfragment D/LogFragment: onCreate:TwoFragment
 * 2020-12-25 14:46:44.176 30263-30263/com.lazy.mylazyfragment D/LogFragment: onCreateView:OneFragment
 * 2020-12-25 14:46:44.188 30263-30263/com.lazy.mylazyfragment D/LogFragment: onActivityCreated:OneFragment
 * 2020-12-25 14:46:44.188 30263-30263/com.lazy.mylazyfragment D/LogFragment: onStart:OneFragment
 * 2020-12-25 14:46:44.189 30263-30263/com.lazy.mylazyfragment D/LogFragment: onResume:OneFragment
 * 2020-12-25 14:46:44.189 30263-30263/com.lazy.mylazyfragment D/LogFragment: lazyInit:!!!!!!!OneFragment
 * 2020-12-25 14:46:44.189 30263-30263/com.lazy.mylazyfragment D/LogFragment: onCreateView:TwoFragment
 * 2020-12-25 14:46:44.190 30263-30263/com.lazy.mylazyfragment D/LogFragment: onActivityCreated:TwoFragment
 * 2020-12-25 14:46:44.190 30263-30263/com.lazy.mylazyfragment D/LogFragment: onStart:TwoFragment
 * 2020-12-25 14:46:44.190 30263-30263/com.lazy.mylazyfragment D/LogFragment: onResume:TwoFragment
 * 2020-12-25 14:46:44.190 30263-30263/com.lazy.mylazyfragment D/LogFragment: lazyInit:!!!!!!!TwoFragment
 *
 * 则 旧懒加载方案  结合 setUserVisibleHint 此方法  OldVpLazyFragment里实现懒加载
 *
 * 但是会走 未展示出来fragment的生命周期
 *
 */
public class ActivityVpOldLazy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        ViewPager viewPager = findViewById(R.id.view_pager);
        FragmentLazyPagerAdapter fragmentLazyPagerAdapter = new FragmentLazyPagerAdapter(getSupportFragmentManager());
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new TraditionTestAFragment());
        arrayList.add(new TraditionTestBFragment());
        arrayList.add(new TraditionTestCFragment());
        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("AFragment");
        titleList.add("BFragment");
        titleList.add("CFragment");
        fragmentLazyPagerAdapter.setFragmentArrayList(arrayList);
        fragmentLazyPagerAdapter.setTitleList(titleList);
        viewPager.setAdapter(fragmentLazyPagerAdapter);

        ((TabLayout) findViewById(R.id.tab_layout)).setupWithViewPager(viewPager);
    }
}