package com.lazy.mylazyfragment.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.lazy.mylazyfragment.R;
import com.lazy.mylazyfragment.adapter.FragmentLazyStatePageAdapter;
import com.lazy.mylazyfragment.fragment.base.AndroidXLazyFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 * author : xu
 * date : 2020/12/25 17:06
 * description :
 */
public class DFragment extends AndroidXLazyFragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView:" + this.getClass().getSimpleName());
        view = inflater.inflate(R.layout.fragment_d, container, false);

        return view;
    }

    @Override
    public void lazyInit() {
        FragmentLazyStatePageAdapter fragmentLazyStatePageAdapter = new FragmentLazyStatePageAdapter(getChildFragmentManager(),
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
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

        ((TabLayout) view.findViewById(R.id.tab_layout)).setupWithViewPager(viewPager);
    }
}
