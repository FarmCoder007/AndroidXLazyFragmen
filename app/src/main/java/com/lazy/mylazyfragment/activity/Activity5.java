package com.lazy.mylazyfragment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import com.lazy.mylazyfragment.R;
import com.lazy.mylazyfragment.fragment.TraditionTestAFragment;
import com.lazy.mylazyfragment.fragment.DFragment;
import com.lazy.mylazyfragment.fragment.FourFragment;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

/**
 * 复杂嵌套fragment   首页是  add  hide show  方案
 */
public class Activity5 extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Fragment> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        findViewById(R.id.btn_a).setOnClickListener(this);
        findViewById(R.id.btn_b).setOnClickListener(this);
        findViewById(R.id.btn_c).setOnClickListener(this);
        arrayList = new ArrayList<>();
        arrayList.add(new TraditionTestAFragment());
        arrayList.add(new FourFragment());
        arrayList.add(new DFragment());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
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
            case R.id.btn_a:
                showFragmenByIndex(0);
                break;
            case R.id.btn_b:
                showFragmenByIndex(1);
                break;
            case R.id.btn_c:
                showFragmenByIndex(2);
                break;
            default:
                break;
        }
    }

    public void showFragmenByIndex(int index) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < arrayList.size(); i++) {
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