package com.lazy.mylazyfragment.adapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * author : xu
 * date : 2020/12/24 14:50
 * description :
 */
public class FragmentLazyStatePageAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragmentArrayList;
    private ArrayList<String> titleList;

    public FragmentLazyStatePageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public FragmentLazyStatePageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public ArrayList<Fragment> getFragmentArrayList() {
        return fragmentArrayList;
    }

    public void setFragmentArrayList(ArrayList<Fragment> fragmentArrayList) {
        this.fragmentArrayList = fragmentArrayList;
    }

    public ArrayList<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(ArrayList<String> titleList) {
        this.titleList = titleList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
