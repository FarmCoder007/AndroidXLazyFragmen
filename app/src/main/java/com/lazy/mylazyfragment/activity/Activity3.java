package com.lazy.mylazyfragment.activity;

import android.os.Bundle;
import android.view.View;

import com.lazy.mylazyfragment.R;
import com.lazy.mylazyfragment.fragment.TraditionTestAFragment;
import com.lazy.mylazyfragment.fragment.TraditionTestBFragment;
import com.lazy.mylazyfragment.fragment.TraditionTestCFragment;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * add  hide  show  方式
 * 1 首次加载的时候 同级fragment的生命周期 都会走onAttach -> onCreate -> onCreatedView -> onActivityCreated -> onStart -> onResume
 * 2 当hide  show 时 只会走 onHiddenChanged   隐藏的返回false   显示的返回true
 * <p>
 * 完整的生命周期
 * onAttach -> onCreate -> onCreatedView -> onActivityCreated -> onStart -> onResume -> onPause -> onStop -> onDestroyView -> onDestroy -> onDetach
 */
public class Activity3 extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);

        // 如果是继承  OldLazyFragment   可演示  add  hide  show  旧的懒加载方案
        fragments = new ArrayList<>();
        fragments.add(new TraditionTestAFragment());
        fragments.add(new TraditionTestBFragment());
        fragments.add(new TraditionTestCFragment());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            fragmentTransaction.add(R.id.fl_container, fragments.get(i), fragments.get(i).getClass().getSimpleName());
            if (i != 0) {
                fragmentTransaction.hide(fragments.get(i));
            }
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.btn_1:
                for (int i = 0; i < fragments.size(); i++) {
                    if (i == 0) {
                        fragmentTransaction.show(fragments.get(i));
                    } else {
                        fragmentTransaction.hide(fragments.get(i));
                    }
                }
                fragmentTransaction.commit();
                break;
            case R.id.btn_2:
                for (int i = 0; i < fragments.size(); i++) {
                    if (i == 1) {
                        fragmentTransaction.show(fragments.get(i));
                    } else {
                        fragmentTransaction.hide(fragments.get(i));
                    }
                }
                fragmentTransaction.commit();
                break;
            case R.id.btn_3:
                for (int i = 0; i < fragments.size(); i++) {
                    if (i == 2) {
                        fragmentTransaction.show(fragments.get(i));
                    } else {
                        fragmentTransaction.hide(fragments.get(i));
                    }
                }
                fragmentTransaction.commit();
                break;
            default:
                break;
        }
    }
}