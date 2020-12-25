package com.lazy.mylazyfragment.adapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * author : xu
 * date : 2020/12/24 14:45
 * description :  搭配Viewpage2  使用
 */
public class FragmentLazyStateAdapter extends FragmentStateAdapter {
    private ArrayList<Fragment> fragmentArrayList;

    public FragmentLazyStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ArrayList<Fragment> getFragmentArrayList() {
        return fragmentArrayList;
    }

    public void setFragmentArrayList(ArrayList<Fragment> fragmentArrayList) {
        this.fragmentArrayList = fragmentArrayList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentArrayList.size();
    }
}
