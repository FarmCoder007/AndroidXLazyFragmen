package com.lazy.mylazyfragment.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazy.mylazyfragment.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * author : xu
 * date : 2020/12/24 11:05
 * description :  记录fragment的所有生命周期
 * onAttach -> onCreate -> onCreatedView -> onActivityCreated -> onStart -> onResume ->
 * onPause -> onStop -> onDestroyView -> onDestroy -> onDetach
 */
public abstract class LogFragment extends Fragment {
    public static final String TAG = "LogFragment";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach:" + this.getClass().getSimpleName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate:" + this.getClass().getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView:" + this.getClass().getSimpleName());
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        TextView view1 = view.findViewById(R.id.tv_test);
        view1.setText(this.getClass().getSimpleName());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated:" + this.getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart:" + this.getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume:" + this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause:" + this.getClass().getSimpleName());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop:" + this.getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:" + this.getClass().getSimpleName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView:" + this.getClass().getSimpleName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach:" + this.getClass().getSimpleName());
    }

    /**
     *   只有 add  hide  show 控制fragment 时  才会走此方法 ：此时旧法实现懒加载用   onHiddenChanged
     *   此时不走setUserVisibleHint
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, "onHiddenChanged:" + this.getClass().getSimpleName() + "--:" + hidden);
    }

    /**
     *  只有viewPage  +  fragmentPageAdapter  控制fragment 时    fragment 显示隐藏才会走此方法
     *  此时因为没有用hide fragment   不会走onHiddenChanged 方法
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint:" + this.getClass().getSimpleName() + "--:" + isVisibleToUser);
    }
}
