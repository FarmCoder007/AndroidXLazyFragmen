package com.lazy.mylazyfragment.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazy.mylazyfragment.R;
import com.lazy.mylazyfragment.fragment.base.AndroidXLazyFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

/**
 * author : xu
 * date : 2020/12/24 15:36
 * description :  基本嵌套fragment     懒加载
 */
public class FourFragment extends AndroidXLazyFragment implements View.OnClickListener {
    ArrayList<Fragment> arrayList;

    public FourFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView:" + this.getClass().getSimpleName());
        View view = inflater.inflate(R.layout.fragment_four, container, false);

        view.findViewById(R.id.btn_1).setOnClickListener(this);
        view.findViewById(R.id.btn_2).setOnClickListener(this);
        view.findViewById(R.id.btn_3).setOnClickListener(this);

        return view;
    }

    /**
     *  复杂fragment 嵌套问题
     *
     */
    @Override
    public void lazyInit() {
        arrayList = new ArrayList<>();
        arrayList.add(new EFragment());
        arrayList.add(new FFragment());
        arrayList.add(new GFragment());
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        for (int i = 0; i < arrayList.size(); i++) {
            fragmentTransaction.add(R.id.fl_container, arrayList.get(i), arrayList.get(i).getClass().getSimpleName());
            if (i == 0) {
                fragmentTransaction.setMaxLifecycle(arrayList.get(i), Lifecycle.State.RESUMED);
            } else {
                fragmentTransaction.hide(arrayList.get(i));
                fragmentTransaction.setMaxLifecycle(arrayList.get(i), Lifecycle.State.STARTED);
            }
        }
        fragmentTransaction.commit();
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
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        for (int i = 0; i <  arrayList.size(); i++) {
            Fragment fragment = arrayList.get(i);
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
